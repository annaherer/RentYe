package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.ContractPeriod;

import java.util.List;

public interface ContractPeriodRepository extends JpaRepository<ContractPeriod, Long>  {
    List<ContractPeriod> findByContractApartment (Apartment apartment);
}