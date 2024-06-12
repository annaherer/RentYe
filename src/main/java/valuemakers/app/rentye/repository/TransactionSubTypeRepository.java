package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.TransactionSubType;

public interface TransactionSubTypeRepository extends JpaRepository<TransactionSubType, Long>  {
}