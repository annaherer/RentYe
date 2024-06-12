package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.ScheduledPayment;

public interface ScheduledPaymentRepository extends JpaRepository<ScheduledPayment, Long>  {
}