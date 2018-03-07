package goods.controller;

import goods.entities.GoodsGroupEntity;
import goods.entities.LabelEntity;
import goods.service.CacheSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCacheController {

    @Autowired
    private CacheSercice cacheSercice;


    @GetMapping("/labels")
    public List<LabelEntity> getAllLabels(){
        return cacheSercice.getAllLabels();
    }

    @GetMapping("/cleanLabels")
    public String cleanLabels(){
        return cacheSercice.cleanLabels();
    }

    @GetMapping("/cleanGoods/{id}")
    public String cleanGoods(@PathVariable("id") Long id){
        return cacheSercice.cleanGoods(id);
    }

    @GetMapping("/getGoods/{id}")
    public GoodsGroupEntity getGoods(@PathVariable("id") Long id){
        return cacheSercice.getGoodsGroup(id);
    }


    @GetMapping("/cleanAllGoods")
    public String cleanAllGoods(){
        return cacheSercice.cleanGoods();
    }
}
