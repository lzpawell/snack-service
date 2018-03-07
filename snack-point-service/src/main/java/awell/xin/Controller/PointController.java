package awell.xin.Controller;

import awell.xin.Entities.PointUpdateItemEntity;
import awell.xin.Entities.UserPointEntity;
import awell.xin.respositories.PointUpdateItemRepository;
import awell.xin.respositories.UserPointRepository;
import awell.xin.service_provider.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ssx on 2018/1/18.
 */
@RestController
public class PointController {
    @Autowired
    private UserPointRepository userPointRepository;
    @Autowired
    private PointUpdateItemRepository pointUpdateItemRepository;
    @Autowired
    private UserService userService;
    /*
    getPoint，get方法
    可用于获取积分和连续签到天数。
    request:String userId
    response:
    {
        exception:
        data:{
                int point;
                int signInDay;
                long[] signInRecordList;
            }
        }
    }
     */
    @RequestMapping(value = "/getPoint",method = RequestMethod.GET)
    public String getPoint(String userId){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if(userId == null || userId.equals("")){
            result.put("excetion","Wrong userId!");
            return result.toJSONString();
        }
        UserPointEntity user = userPointRepository.findOne(userId);
        if (user == null){
            JSONObject userJson = JSONObject.parseObject(userService.getUserInfo(userId));
            if (userJson.containsKey("exception")){
                result.put("exception",userJson.getString("exception"));
                return result.toJSONString();
            }
            if (!userJson.containsKey("data")){
                result.put("exception","get nickName failed");
                return result.toJSONString();
            }
            JSONObject detail = userJson.getJSONObject("data");
            if (!detail.containsKey("nickName")){
                result.put("exception","get nickName failed");
                return result.toJSONString();
            }
            user = new UserPointEntity();
            user.setNickName(detail.getString("nickName"));
            user.setDay(0);
            user.setPoint(0);
            user.setUserId(userId);
            user.setSignInRecordList(new ArrayList<>());
            try {
                userPointRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("excetion","save Failed!");
                return result.toJSONString();
            }
        }
        data.put("signInDay",user.getDay());
        data.put("point",user.getPoint());
        data.put("signInRecordList",user.getSignInRecordList());
        result.put("data",data);
        return result.toJSONString();
    }
    /*
    signIn，get方法
    用于进行签到操作。
    request:String userId
    response:
    {
        exception:
        data:{
                int point;
            }
        }
    }
     */
    @Transactional
    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    public String signIn(String userId) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if(userId == null || userId.equals("")){
            result.put("excetion","Wrong userId!");
            return result.toJSONString();
        }
        Date nowDate = new Date();
        UserPointEntity user = userPointRepository.findOne(userId);
        PointUpdateItemEntity pointUpdateItemEntity = new PointUpdateItemEntity();
        if (user == null || user.getSignInRecordList().size() == 0){
            if (user == null) {
                JSONObject userJson = JSONObject.parseObject(userService.getUserInfo(userId));
                if (userJson.containsKey("exception")) {
                    result.put("exception", userJson.getString("exception"));
                    return result.toJSONString();
                }
                if (!userJson.containsKey("data")) {
                    result.put("exception", "get nickName failed");
                    return result.toJSONString();
                }
                JSONObject detail = userJson.getJSONObject("data");
                if (!detail.containsKey("nickName")) {
                    result.put("exception", "get nickName failed");
                    return result.toJSONString();
                }
                user = new UserPointEntity();
                user.setNickName(detail.getString("nickName"));
            }else{
                user = new UserPointEntity();
            }
            user.setDay(1);
            user.setPoint(5);
            user.setUserId(userId);
            user.setTotalDay(1);
            List<Date> signInRecordList = new ArrayList<>();
            signInRecordList.add(nowDate);
            user.setSignInRecordList(signInRecordList);
            pointUpdateItemEntity.setDescription("首次签到+10积分");
            pointUpdateItemEntity.setChangPoint(10);
        }else{
            List<Date> signInRecordList = user.getSignInRecordList();
            Date lastDate = signInRecordList.get(signInRecordList.size()-1);
            if (DateUtils.isSameDay(lastDate,nowDate)){
                result.put("exception","repeat sign in today!");
                return result.toJSONString();
            }
            if (DateUtils.isSameDay(DateUtils.addDays(nowDate,-1),lastDate)){
                user.setDay(user.getDay()+1);
            }else{
                user.setDay(1);
            }
            user.setPoint(user.getPoint()+5);
            signInRecordList.add(nowDate);
            user.setTotalDay(signInRecordList.size());
            user.setSignInRecordList(signInRecordList);
            pointUpdateItemEntity.setDescription("日常签到+5积分");
            pointUpdateItemEntity.setChangPoint(5);
        }
        pointUpdateItemEntity.setUpdateDate(nowDate);
        pointUpdateItemEntity.setUserId(userId);

        try {
            userPointRepository.save(user);
            pointUpdateItemRepository.save(pointUpdateItemEntity);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("excetion","save Failed!");
            return result.toJSONString();
        }
        return result.toJSONString();
    }
    /*
    reSignIn，get方法
    用于进行补签操作。
    request:String userId,int year,int month,int day
    response:
    {
        exception:
        data:{

            }
        }
    }
     */
    @Transactional
    @RequestMapping(value = "/reSignIn",method = RequestMethod.GET)
    public String signIn(String userId,int year,int month,int day) {
        JSONObject result = new JSONObject();
        if(userId == null || userId.equals("")){
            result.put("excetion","Wrong userId!");
            return result.toJSONString();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month - 1);
        cal.set(Calendar.DAY_OF_MONTH,day);
        Date date = cal.getTime();
        UserPointEntity user = userPointRepository.findOne(userId);
        if (user == null){
            JSONObject userJson = JSONObject.parseObject(userService.getUserInfo(userId));
            if (userJson.containsKey("exception")){
                result.put("exception",userJson.getString("exception"));
                return result.toJSONString();
            }
            if (!userJson.containsKey("data")){
                result.put("exception","get nickName failed");
                return result.toJSONString();
            }
            JSONObject detail = userJson.getJSONObject("data");
            if (!detail.containsKey("nickName")){
                result.put("exception","get nickName failed");
                return result.toJSONString();
            }
            user = new UserPointEntity();
            user.setNickName(detail.getString("nickName"));
            user.setDay(0);
            user.setPoint(5);
            user.setUserId(userId);
            user.setTotalDay(1);
            List<Date> signInRecordList = new ArrayList<>();
            signInRecordList.add(date);
            user.setSignInRecordList(signInRecordList);
        }else{
            List<Date> signInRecordList = user.getSignInRecordList();
            int i;
            if (signInRecordList.size() == 0){
                i = 0;
            }else {
                for (i = 0; i < signInRecordList.size(); i++) {
                    Date recordDate = signInRecordList.get(i);
                    if (DateUtils.isSameDay(recordDate, date)) {
                        result.put("exception", "repeat sign in!");
                        return result.toJSONString();
                    }
                    if (recordDate.after(date)) {
                        break;
                    }
                }
            }
            signInRecordList.add(i,date);
            user.setPoint(user.getPoint()+5);
            user.setTotalDay(signInRecordList.size());
            user.setSignInRecordList(signInRecordList);
        }

        PointUpdateItemEntity pointUpdateItemEntity = new PointUpdateItemEntity();
        pointUpdateItemEntity.setChangPoint(5);
        pointUpdateItemEntity.setDescription("补签+5积分");
        pointUpdateItemEntity.setUpdateDate(date);
        pointUpdateItemEntity.setUserId(userId);

        try {
            userPointRepository.save(user);
            pointUpdateItemRepository.save(pointUpdateItemEntity);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("excetion","save Failed!");
            return result.toJSONString();
        }
        return result.toJSONString();
    }
    /*
    changePoint，get方法
    用于进行积分获取和积分抵扣时，改变积分。
    request:String userId,int point,String description
    response:
    {
        exception:
        data:{

            }
        }
    }
     */
    @Transactional
    @RequestMapping(value = "/changePoint",method = RequestMethod.GET)
    public String signIn(String userId,int point,String description) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if(userId == null || userId.equals("")){
            result.put("excetion","Wrong userId!");
            return result.toJSONString();
        }
        Date nowDate = new Date();

        UserPointEntity user = userPointRepository.findOne(userId);
        if (user == null){
            if (point<0){
                result.put("excetion","point not enough!");
                return result.toJSONString();
            }
            JSONObject userJson = JSONObject.parseObject(userService.getUserInfo(userId));
            if (userJson.containsKey("exception")){
                result.put("exception",userJson.getString("exception"));
                return result.toJSONString();
            }
            if (!userJson.containsKey("data")){
                result.put("exception","get nickName failed");
                return result.toJSONString();
            }
            JSONObject detail = userJson.getJSONObject("data");
            if (!detail.containsKey("nickName")){
                result.put("exception","get nickName failed");
                return result.toJSONString();
            }
            user = new UserPointEntity();
            user.setNickName(detail.getString("nickName"));
            user.setDay(0);
            user.setPoint(point);
            user.setUserId(userId);
        }else{
            if (user.getPoint() + point<0){
                result.put("excetion","point not enough!");
                return result.toJSONString();
            }
            user.setPoint(user.getPoint() + point);
        }

        PointUpdateItemEntity pointUpdateItemEntity = new PointUpdateItemEntity();
        pointUpdateItemEntity.setChangPoint(point);
        pointUpdateItemEntity.setDescription(description);
        pointUpdateItemEntity.setUpdateDate(nowDate);
        pointUpdateItemEntity.setUserId(userId);

        try {
            userPointRepository.save(user);
            pointUpdateItemRepository.save(pointUpdateItemEntity);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("excetion","save Failed!");
            return result.toJSONString();
        }
        return result.toJSONString();
    }

    /*
    getSeriesSignInRank，get方法
    获取连续签到排名。
    request:
    response:
    {
        exception:
        data:{
                userAndDays[]:{  //已按降序排序
                    String userId;
                    int day;//连续签到天数
                    String nickName;
                }
            }
        }
    }
     */
    @RequestMapping(value = "/getSeriesSignInRank",method = RequestMethod.GET)
    public String getSeriesSignInRank() {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray userAndDays = new JSONArray();
        Sort sort = new Sort(Sort.Direction.DESC,"day");
        List<UserPointEntity> all = userPointRepository.findAll(sort);
        for (UserPointEntity userPointEntity : all){
            JSONObject userAndDay = new JSONObject();
            userAndDay.put("userId",userPointEntity.getUserId());
            userAndDay.put("day",userPointEntity.getDay());
            userAndDay.put("nickName",userPointEntity.getNickName());
            userAndDays.add(userAndDay);
        }
        data.put("userAndDays",userAndDays);
        result.put("data",data);
        return result.toJSONString();
    }
    /*
   getTotalSignInRank，get方法
   获取连续签到排名。
   request:
   response:
   {
       exception:
       data:{
               userAndDays[]:{  //已按降序排序
                   String userId;
                   int totalDay;//连续签到天数
                   String nickName;
               }
           }
       }
   }
    */
    @RequestMapping(value = "/getTotalSignInRank",method = RequestMethod.GET)
    public String getTotalSignInRank() {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray userAndDays = new JSONArray();
        Sort sort = new Sort(Sort.Direction.DESC,"totalDay");
        List<UserPointEntity> all = userPointRepository.findAll(sort);
        for (UserPointEntity userPointEntity : all){
            JSONObject userAndDay = new JSONObject();
            userAndDay.put("userId",userPointEntity.getUserId());
            userAndDay.put("totalDay",userPointEntity.getDay());
            userAndDay.put("nickName",userPointEntity.getNickName());
            userAndDays.add(userAndDay);
        }
        data.put("userAndDays",userAndDays);
        result.put("data",data);
        return result.toJSONString();
    }
}
