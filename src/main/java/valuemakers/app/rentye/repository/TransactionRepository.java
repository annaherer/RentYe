package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
}