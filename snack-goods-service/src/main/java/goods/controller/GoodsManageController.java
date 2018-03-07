package goods.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goodsGroup")
public class GoodsManageController {

    @PostMapping(value = "/create")
    public JSONObject createGoodsGroup(@RequestBody JSONObject para){
        Long kindId = para.getLong("kind_id");
        String goodsGroupName = para.getString("name");
        if(kindId != null && goodsGroupName != null){

        }

        return null;
    }


    @PostMapping(value = "/addGoods")
    public String addGoods(@RequestBody JSONObject para){
        Long groupId = para.getLong("group_id");
        Float price = para.getFloat("price");
        String taste = para.getString("taste");
        return null;
    }
}
