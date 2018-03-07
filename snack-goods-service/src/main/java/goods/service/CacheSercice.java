package goods.service;

import goods.entities.GoodsGroupEntity;
import goods.entities.LabelEntity;
import goods.repo.GoodsGroupRepo;
import goods.repo.LabelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/*
* 用来测试Cache的类， 无用*/
@Service
public class CacheSercice {
    @Autowired
    private LabelRepo labelRepo;

    @Autowired
    GoodsGroupRepo goodsGroupRepo;


    @Cacheable(value = "label", key = "'label'")
    public List<LabelEntity > getAllLabels(){
        return labelRepo.findAll();
    }

    @CacheEvict(value = "label")
    public String cleanLabels(){
        return "balala";
    }

    @Cacheable(value = "goodsGroup", key = "'label'")
    public GoodsGroupEntity getGoodsGroup(Long id){
        return goodsGroupRepo.findOne(id);
    }

/*    @Cacheable(value = "goodsGroup", key = "'goods-group-' + #p0")
    public GoodsGroupEntity getGoodsGroup(Long id){
        return goodsGroupRepo.findOne(id);
    }*/

    @CacheEvict(value = "goodsGroup", allEntries = false, key = "'goods-group-' + #p0")
    public String cleanGoods(Long id){
        return "clean goods";
    }

    @CacheEvict(value = "goodsGroup", allEntries = true)
    public String cleanGoods(){
        return "clean all goods";
    }
}
