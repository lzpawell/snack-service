package awell.xin.controller;

import awell.xin.entities.ShoppingCartItemEntity;
import awell.xin.entities.UserEntity;
import awell.xin.repositories.UserRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ShoppingCartController {

    @Autowired
    private UserRepository userRepository;
    /*
    getShoppingCart，get方法
    可用于获取购物车的物品属性和数量。
    request:String userId
    response:
    {
        exception:
        data:
        {
            goodsAndCounts[]:{
                Goods的属性
                int count
            }
        }
    }
     */
    @RequestMapping(name = "/getShoppingCart",method = RequestMethod.GET)
    public String getShoppingCart(String userId){
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
        ArrayList<ShoppingCartItemEntity> shoppingCartItemEntityList = user.getShoppingCartItemEntityList();
        //TODO:还需从GoodsService获得数据
        return result.toJSONString();
    }

    /*
    removeShoppingCartItems，get方法
    可用于移除购物车里面的商品。
    request:String userId,long[] goodsId
    response:
    {
        exception:
        data:
        {
            goodsAndCounts[]:{
                Goods的属性
                int count
            }
        }
    }
     */
    @RequestMapping(name = "/removeShoppingCartItems",method = RequestMethod.GET)
    public String removeShoppingCartItems(String userId,Long[] goodsId){
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
        ArrayList<ShoppingCartItemEntity> shoppingCartItemEntityList = user.getShoppingCartItemEntityList();
        //TODO:还需从GoodsService获得数据
        return result.toJSONString();
    }
}
