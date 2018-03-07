package goods.service;

import goods.entities.LabelEntity;
import goods.repo.LabelRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LabelService {

    Logger logger = Logger.getLogger(LabelService.class);

    @Autowired
    private LabelRepo repo;

    private static final String GOODS_LABELS_CACHE = "goods-labels";

    @Cacheable(value = GOODS_LABELS_CACHE, key = "'goods-labels-labelsList'")
    public List<LabelEntity> getAllLabels(){
        return repo.findAll();
    }


    /*
    *
    * */
    @Transactional(rollbackOn = Exception.class)
    @CachePut(value = GOODS_LABELS_CACHE, key = "'goods-labels-labelsList'")
    public List<LabelEntity> addLabels(String... labels){
        try {
            List<LabelEntity> labelEntities = repo.findAll();
            for(String label : labels){
                if(findLabelEntity(label, labelEntities) == null){
                    LabelEntity labelEntity = new LabelEntity();
                    labelEntity.setLabel(label);
                    repo.save(labelEntity);
                    logger.info("labels: " + labelEntity.getLabel() + "  add");
                }
            }

            return repo.findAll();
        }catch (Exception e){
            logger.error("labels: " + " add error!" + e.getMessage());
            return null;
        }
    }


    /*
     *
     * */
    @Transactional(rollbackOn = Exception.class)
    @CachePut(value = GOODS_LABELS_CACHE, key = "'goods-labels-labelsList'")
    public List<LabelEntity> removeLabels(String... labels){
        try {
            List<LabelEntity> labelEntities = repo.findAll();
            for(String label : labels){
                LabelEntity labelEntity = findLabelEntity(label, labelEntities);
                if(labelEntity != null){
                    repo.delete(labelEntity);
                    logger.info("labels: " + labelEntity.getLabel() + "  removed");
                }
            }

            return repo.findAll();
        }catch (Exception e){
            logger.error("labels: " + " removed error!" + e.getMessage());
            return  null;
        }
    }


    /*
    *
    * return entity by the label
    * null for not found
    * */
    private LabelEntity findLabelEntity(String label, List<LabelEntity> list){
        for(LabelEntity entity : list){
            if(entity.getLabel().equals(label)){
                return entity;
            }
        }

        return null;
    }
}
