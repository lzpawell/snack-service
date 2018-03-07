package goods.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import goods.entities.GoodsEntity;
import goods.entities.GoodsGroupEntity;
import goods.entities.LabelEntity;
import goods.entities.TypeEntity;
import goods.repo.GoodsGroupRepo;
import goods.repo.GoodsRepo;
import goods.repo.LabelRepo;
import goods.repo.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import goods.service.GoodsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @RequestMapping(value = "/getAllGoodsGroup", method = RequestMethod.GET)
    public JSONObject getAllGoodsGroup(){
        JSONObject object = new JSONObject();
        object.put("data", goodsService.getAllGoodsGroup());
        return object;
    }

    @RequestMapping(value = "/getAllLabels", method = RequestMethod.GET)
    public JSONObject getAllLabels(){
        JSONObject object = new JSONObject();
        object.put("data", goodsService.getAllLabels());
        return object;
    }

    @RequestMapping(value = "/getAllKinds", method = RequestMethod.GET)
    public JSONObject getAllKinds(){
        JSONObject object = new JSONObject();
        object.put("data", goodsService.getAllTypes());
        return object;
    }


    @Autowired
    private LabelRepo labelRepo;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String aowu(){
        System.out.println("balala");
        List<GoodsGroupEntity> list = goodsService.getAllGoodsGroup();
        System.out.println("balala");
        LabelEntity labelEntity1 = labelRepo.findByLabel("特惠");
        LabelEntity labelEntity2 = labelRepo.findByLabel("上新");
        LabelEntity labelEntity3 = labelRepo.findByLabel("限时特优");
        System.out.println("balala");


        int i = 0;

        int j = 0;
        for(GoodsGroupEntity groupEntity : list){
            i++;
            j++;
            if(i %3 != 0 )
                break;

            j %= 3;

            switch (j){
                case 0:
                    groupEntity.addLabel(labelEntity1);
                    break;
                case 1:
                    groupEntity.addLabel(labelEntity2);
                    break;
                case 2:
                    groupEntity.addLabel(labelEntity3);
                    break;
            }

            if(i % 5 == 0){
                groupEntity.addLabel(labelEntity2);
            }

            goodsGroupRepo.save(groupEntity);
        }

        return "ae";
    }


    @RequestMapping(value = "/updateGoodsInfo", method = RequestMethod.POST)
    public JSONObject updateGoodsInfo(){
        return null;
    }



    @Autowired
    private GoodsGroupRepo goodsGroupRepo;


    @Value("${app.external.goods-res-pos}")
    private String externFolderPos;

    private ArrayList<String> getTopicList(Long id){
        return new ArrayList<>(Arrays.asList("/goods-res/" + id + "/" + "topic-1.jpg",
                "/goods-res/"  + id +"/" + "topic-2.jpg",
                "/goods-res/"  + id + "/" +"topic-3.jpg"));
    }

    private ArrayList<String> getDetailsList(Long id){
        return new ArrayList<>(Arrays.asList("/goods-res/"+ id + "/" +  "details-1.jpg",
                "/goods-res/"  + id + "/" + "details-2.jpg",
                "/goods-res/"  + id +  "/" +"details-3.jpg",
                "/goods-res/"  + id + "/" +"details-4.jpg",
                "/goods-res/"  + id + "/" +"details-5.jpg"));
    }




}
