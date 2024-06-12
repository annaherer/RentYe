package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.DepreciationTransaction;

public interface DepreciationTransactionRepository extends JpaRepository<DepreciationTransaction, Long> {
}