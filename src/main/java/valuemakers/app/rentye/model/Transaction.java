package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import valuemakers.app.rentye.util.TransactionSort;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private LocalDate date;
    private String ownDocumentNumber;
    private String code;
    private Double amount;
    private String settlementType;
    private String description;
    private TransactionSort sort;
    @NotNull
    @ManyToOne
    private Apartment apartment;
    @OneToOne
    private FixedAsset fixedAsset;
    @NotNull
    @ManyToOne
    private Period period;
    @NotNull
    @ManyToOne
    private TransactionParty transactionParty;
    @ManyToOne
    private Depreciation depreciation;
}