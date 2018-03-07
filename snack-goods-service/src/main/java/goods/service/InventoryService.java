package goods.service;


import goods.entities.InventoryEntity;
import goods.repo.InventoryRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    Logger logger = Logger.getLogger(LabelService.class);

    @Autowired
    private InventoryRepo inventoryRepo;

    private static final String INVENTORY_CACHE = "INVENTORY_CACHE";

    @Cacheable(value = INVENTORY_CACHE, key = "#{'InventoryService'.CASE_INSENSITIVE_ORDER + '#p0'}")
    public InventoryEntity queryInventoryByGoodsId(Long goodsId){
        return inventoryRepo.findOne(goodsId);
    }

    @Cacheable(value = INVENTORY_CACHE, key = "#{'InventoryService'.CASE_INSENSITIVE_ORDER + '#p0'}")
    public InventoryEntity setInventoryByGoodsId(Long goodsId, int inventory){
        try{
            InventoryEntity entity = new InventoryEntity();
            entity.setGoodsId(goodsId);
            entity.setInventory(inventory);
            inventoryRepo.save(entity);
            return entity;
        }catch (Exception e ){
            logger.error("set inventory error for id: " + goodsId + " inventory: " + inventory, e);
            return null;
        }
    }
}
