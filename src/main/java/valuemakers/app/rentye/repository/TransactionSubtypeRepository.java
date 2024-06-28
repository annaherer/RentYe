package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.TransactionSubtype;

public interface TransactionSubtypeRepository extends JpaRepository<TransactionSubtype, Long>  {
}