package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import valuemakers.app.rentye.util.TransactionSort;

@Entity
public class TransactionType {
    public static final Integer TRANSACTION_SORT_REVENUE = 0;
    public static final Integer TRANSACTION_SORT_COST = 1;
    public static final Integer TRANSACTION_SORT_PURCHASE = 2;
    public static final Integer TRANSACTION_SORT_EXCLUDED = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Description must be filled in.")
    private String description;
    @NotNull(message = "Default transaction sort must be filled in.")
    private TransactionSort defaultTransactionSort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionSort getDefaultTransactionSort() {
        return defaultTransactionSort;
    }

    public void setDefaultTransactionSort(TransactionSort defaultTransactionSort) {
        this.defaultTransactionSort = defaultTransactionSort;
    }
}