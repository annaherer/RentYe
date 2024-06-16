package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Apartment apartment;

    @OneToMany(mappedBy = "contract", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ContractPeriod> contractPeriods;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private List<Tenant> tenants;

    @NotNull
    private Boolean ownUseFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOwnUseFlag() {
        return ownUseFlag;
    }

    public void setOwnUseFlag(Boolean ownUseFlag) {
        this.ownUseFlag = ownUseFlag;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<ContractPeriod> getContractPeriods() {
        return contractPeriods;
    }

    public void setContractPeriods(List<ContractPeriod> contractPeriods) {
        this.contractPeriods = contractPeriods;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }
}