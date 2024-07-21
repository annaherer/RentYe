package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Apartment;

public interface ApartmentRepository extends JpaRepository <Apartment, Long> {
}