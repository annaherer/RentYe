package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.FixedAssetType;

public interface FixedAssetTypeRepository extends JpaRepository<FixedAssetType, Long>  {
}