package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.model.Tenant;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByTenantsContainingAndActive(Tenant tenant, Boolean active);
    List<Contract> findByApartment(Apartment apartment);
    Contract findByApartmentAndActive(Apartment apartment, Boolean active);
}