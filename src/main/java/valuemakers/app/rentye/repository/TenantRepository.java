package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long>  {
}