package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "contractor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScheduledPayment> scheduledPayments;

    @NotNull
    @ManyToOne
    private ContractorType contractorType;

    @NotNull
    @OneToOne
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

    public List<ScheduledPayment> getScheduledPayments() {
        return scheduledPayments;
    }

    public void setScheduledPayments(List<ScheduledPayment> scheduledPayments) {
        this.scheduledPayments = scheduledPayments;
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