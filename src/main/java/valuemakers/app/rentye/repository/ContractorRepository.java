package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}