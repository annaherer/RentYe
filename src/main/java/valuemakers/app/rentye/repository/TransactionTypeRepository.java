package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long>  {
}