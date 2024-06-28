package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class ApartmentContractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Apartment apartment;

    @NotNull
    @ManyToOne
    private Contractor contractor;

    @NotNull
    Boolean settlePaymentsWithTenant;

    @OneToMany(mappedBy = "apartmentContractor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScheduledPayment> scheduledPayments;

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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Boolean getSettlePaymentsWithTenant() {
        return settlePaymentsWithTenant;
    }

    public void setSettlePaymentsWithTenant(Boolean settlePaymentsWithTenant) {
        this.settlePaymentsWithTenant = settlePaymentsWithTenant;
    }

    public List<ScheduledPayment> getScheduledPayments() {
        return scheduledPayments;
    }

    public void setScheduledPayments(List<ScheduledPayment> scheduledPayments) {
        this.scheduledPayments = scheduledPayments;
    }
}
