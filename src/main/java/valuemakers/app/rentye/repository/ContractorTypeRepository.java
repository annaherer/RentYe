package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.ContractorType;

public interface ContractorTypeRepository extends JpaRepository<ContractorType, Long>  {
}