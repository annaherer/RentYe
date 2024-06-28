package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Valid
    private TransactionParty transactionParty;
    //@PESEL
    //Pesel can't be checked as flats could be rented to foreigners
    private String pesel;
    private String personalIdNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate personalIdValidity;
    private String passportNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate passportValidity;
    private String citizenship;
    @NotNull(message = "Status is required")
    private Boolean active=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public LocalDate getPersonalIdValidity() {
        return personalIdValidity;
    }

    public void setPersonalIdValidity(LocalDate personalIdValidity) {
        this.personalIdValidity = personalIdValidity;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getPassportValidity() {
        return passportValidity;
    }

    public void setPassportValidity(LocalDate passportValidity) {
        this.passportValidity = passportValidity;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean status) {
        this.active = status;
    }

    public TransactionParty getTransactionParty() {
        return transactionParty;
    }

    public void setTransactionParty(TransactionParty transactionParty) {
        this.transactionParty = transactionParty;
    }
}