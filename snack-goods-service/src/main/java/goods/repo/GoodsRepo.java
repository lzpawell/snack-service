package goods.repo;

import goods.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepo extends JpaRepository<GoodsEntity, Long> {

}
