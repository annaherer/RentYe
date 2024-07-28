package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TransactionSubtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private TransactionType transactionType;
    @Column(unique = true)
    @Pattern(regexp = "[A-Z]{3}", message = "Transaction code must be three uppercase characters")
    private String code;
    private String description;
    private Integer lastIndex=0;
}