package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}