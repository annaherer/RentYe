package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import valuemakers.app.rentye.util.TransactionSort;

@Getter
@Setter
@Entity
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Description must be filled in.")
    private String description;
    @NotNull(message = "Default transaction sort must be filled in.")
    private TransactionSort defaultTransactionSort;
}