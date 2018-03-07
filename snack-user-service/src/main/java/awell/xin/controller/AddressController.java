package awell.xin.controller;

import awell.xin.entities.AddressEntity;
import awell.xin.entities.UserEntity;
import awell.xin.repositories.AddressRepository;
import awell.xin.repositories.UserRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    /*
        createAddress，get方法
        可用于给用户创建地址。
        request:String userId,String name,String phoneNum,String location
        response:
        {
            exception:
            data:{
                long addressId
            }
        }
     */
    @RequestMapping(name = "/createAddress",method = RequestMethod.GET)
    public String createAddress(String userId,String name,String phoneNum,String location){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (userId == null || userId.equals("")){
            result.put("exception","Wrong userId!");
            return result.toJSONString();
        }

        UserEntity user = userRepository.findOne(userId);
        if (user == null){
            result.put("exception","userId Not Found");
            return result.toJSONString();
        }
        AddressEntity newAddress = new AddressEntity();
        newAddress.setLocation(location);
        newAddress.setName(name);
        newAddress.setPhoneNum(phoneNum);

        try {
            AddressEntity addressEntity = addressRepository.saveAndFlush(newAddress);
            user.getAddressList().add(addressEntity);
            userRepository.saveAndFlush(user);
            data.put("addressId",addressEntity.getAddressId());
            result.put("data",data);
            return result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            result.put("exception","save Failed");
            return result.toJSONString();
        }

    }

    /*
        setDefaultAddress，get方法
        可用于设置默认的地址。
        request:String userId,long AddressId
        response:
        {
            exception:
            data:
        }
     */
    @RequestMapping(name = "/setDefaultAddress",method = RequestMethod.GET)
    public String setDefaultAddress(String userId,long AddressId){
        JSONObject result = new JSONObject();
        if (userId == null || userId.equals("")){
            result.put("exception","Wrong userId!");
            return result.toJSONString();
        }

        UserEntity user = userRepository.findOne(userId);
        if (user == null){
            result.put("exception","userId Not Found");
            return result.toJSONString();
        }
        user.setDefaultAddressId(AddressId);
        try {
            userRepository.saveAndFlush(user);
        }catch (Exception e){
            e.printStackTrace();
            result.put("exception","save Failed");
            return result.toJSONString();
        }
        return result.toJSONString();
    }

    /*
    getAddressList，get方法
    可用于地址信息，其中defaultAddressId为默认地址Id。
    request:String userId
    response:
    {
        exception:
        data:{
            addressList[]:{
                long addressId;
                String name;//收货人姓名
                String phoneNum;
                String location;
            }
            long defaultAddressId;
        }
    }
     */
    @RequestMapping(name = "/getAddressList",method = RequestMethod.GET)
    public String getAddressList(String userId){
        JSONObject result = new JSONObject();
        if (userId == null || userId.equals("")){
            result.put("exception","Wrong userId!");
            return result.toJSONString();
        }

        UserEntity user = userRepository.findOne(userId);
        if (user == null){
            result.put("exception","userId Not Found");
            return result.toJSONString();
        }
        JSONObject data = new JSONObject();
        data.put("addressList",user.getHistoryEntityArrayList());
        data.put("defaultAddressId",user.getDefaultAddressId());
        result.put("data",data);
        return result.toJSONString();
    }
}
