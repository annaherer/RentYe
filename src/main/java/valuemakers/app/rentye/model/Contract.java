package valuemakers.app.rentye.model;

import jakarta.persistence.*;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private List<Tenant> tenants;

    @NotNull
    private Boolean active;

    @NotNull
    private Double deposit;

    @NotNull
    private Double depositHeld;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getDepositHeld() {
        return depositHeld;
    }

    public void setDepositHeld(Double depositHeld) {
        this.depositHeld = depositHeld;
    }
}