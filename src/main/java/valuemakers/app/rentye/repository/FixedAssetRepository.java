package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.FixedAsset;

public interface FixedAssetRepository extends JpaRepository<FixedAsset, Long> {
}