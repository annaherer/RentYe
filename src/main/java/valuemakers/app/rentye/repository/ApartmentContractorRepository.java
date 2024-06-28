package valuemakers.app.rentye.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.ApartmentContractor;

public interface ApartmentContractorRepository extends JpaRepository <ApartmentContractor, Long> {
}