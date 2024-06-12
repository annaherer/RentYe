package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}