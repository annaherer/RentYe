package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Depreciation;

public interface DepreciationRepository extends JpaRepository<Depreciation, Long>  {
}