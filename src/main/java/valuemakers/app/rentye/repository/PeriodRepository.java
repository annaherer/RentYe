package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Period;

public interface PeriodRepository extends JpaRepository<Period, Long> {
}