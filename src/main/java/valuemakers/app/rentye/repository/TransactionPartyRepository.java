package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.TransactionParty;

public interface TransactionPartyRepository extends JpaRepository<TransactionParty, Long>  {
}