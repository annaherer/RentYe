package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.ContractPeriod;

public interface ContractPeriodRepository extends JpaRepository<ContractPeriod, Long>  {
}