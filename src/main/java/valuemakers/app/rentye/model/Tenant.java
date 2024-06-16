package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.pl.PESEL;

import java.util.Date;

@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private TransactionParty transactionParty;

    @NotBlank(message = "PESEL is required")
    //@PESEL
    private String pesel;
    //@NotBlank(message = "Personal id number is required")
    @UniqueElements
    private String personalIdNumber;
    //@NotBlank(message = "Personal id validity is required")
    private Date personalIdValidity;
    //@NotBlank(message = "Passport number is required")
    @UniqueElements
    private String passportNumber;
    //@NotBlank(message = "Password validity is required")
    private Date passportValidity;
    //@NotBlank(message = "Citizenship is required")
    private String citizenship;
    @NotBlank(message = "Status is required")
    private Boolean status;

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

    public Date getPersonalIdValidity() {
        return personalIdValidity;
    }

    public void setPersonalIdValidity(Date personalIdValidity) {
        this.personalIdValidity = personalIdValidity;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Date getPassportValidity() {
        return passportValidity;
    }

    public void setPassportValidity(Date passportValidity) {
        this.passportValidity = passportValidity;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TransactionParty getTransactionParty() {
        return transactionParty;
    }

    public void setTransactionParty(TransactionParty transactionParty) {
        this.transactionParty = transactionParty;
    }
}