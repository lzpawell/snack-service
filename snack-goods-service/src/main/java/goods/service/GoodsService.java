package goods.service;

import goods.entities.GoodsEntity;
import goods.entities.GoodsGroupEntity;
import goods.entities.LabelEntity;
import goods.entities.TypeEntity;
import goods.repo.LabelRepo;
import goods.repo.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import goods.repo.GoodsGroupRepo;
import goods.repo.GoodsRepo;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsGroupRepo goodsGroupRepo;


    @Autowired
    private GoodsRepo goodsRepo;


    public List<GoodsGroupEntity> getAllGoodsGroup(){
        return goodsGroupRepo.findAll();
    }


    public List<GoodsEntity> getAllGoods(){
        return goodsRepo.findAll();
    }

    public GoodsEntity getGoodsById(Long id){
        return goodsRepo.findOne(id);
    }

    public GoodsGroupEntity getGoodsGroupById(Long id) {
        return goodsGroupRepo.findOne(id);
    }


    @Autowired
    private LabelRepo labelRepo;


    @Autowired
    private TypeRepo typeRepo;


    @Cacheable(value = "labels", key = "'labels'")
    public List<LabelEntity> getAllLabels(){
        return labelRepo.findAll();
    }


    public List<TypeEntity> getAllTypes(){
        return typeRepo.findAll();
    }
}
