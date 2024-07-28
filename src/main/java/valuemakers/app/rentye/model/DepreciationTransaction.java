package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DepreciationTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Depreciation amount is required.")
    private Double amount;
    @NotNull
    @ManyToOne
    private Period period;
    @NotNull
    @ManyToOne
    private Depreciation depreciation;
}