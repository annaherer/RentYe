package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}
