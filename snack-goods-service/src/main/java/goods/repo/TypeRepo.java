package goods.repo;

import goods.entities.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo  extends JpaRepository<TypeEntity, Long> {

    TypeEntity findTypeEntityByType(String type);
}
