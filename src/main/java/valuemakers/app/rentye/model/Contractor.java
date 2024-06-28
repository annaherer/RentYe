package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private ContractorType contractorType;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Valid
    private TransactionParty transactionParty;

    private String contactPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public ContractorType getContractorType() {
        return contractorType;
    }

    public void setContractorType(ContractorType contractorType) {
        this.contractorType = contractorType;
    }

    public TransactionParty getTransactionParty() {
        return transactionParty;
    }

    public void setTransactionParty(TransactionParty transactionParty) {
        this.transactionParty = transactionParty;
    }
}