package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Depreciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "depreciation", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "depreciation", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<DepreciationTransaction> depreciationTransactions;
    @NotBlank(message = "Property price is required.")
    private Double propertyPrice;
    private Double purchaseCost;
    private Double plotArea;
    private Double plotEstimatedValueSquareMeter;
    private Double plotEstimatedValue;
    private Double purchaseCostApportionedToInitialValue;
    private Double initialValue;
    private Boolean status;
    private Double rate;
    private Double monthlyDepreciationAmount;
}