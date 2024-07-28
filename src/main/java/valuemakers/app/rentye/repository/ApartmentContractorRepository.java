package valuemakers.app.rentye.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.ApartmentContractor;

import java.util.Optional;

public interface ApartmentContractorRepository extends JpaRepository <ApartmentContractor, Long> {
    Optional<ApartmentContractor> findByApartmentIdAndContractorId(Long apartmentId, Long contractorId);
}