package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class ContractPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Tenant mainTenant;

    @NotNull
    @ManyToOne
    private Contract contract;

    private Integer annexNumber;
    private Date dateSigned;
    private Date dateValid;
    private Date dateExpired;
    private Double monthlyRentAmount;
    private Double monthlyEstimatedServiceCharges;
    private Integer paymentDay;
    private Boolean petsAllowedFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnexNumber() {
        return annexNumber;
    }

    public void setAnnexNumber(Integer annexNumber) {
        this.annexNumber = annexNumber;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    public Date getDateValid() {
        return dateValid;
    }

    public void setDateValid(Date dateValid) {
        this.dateValid = dateValid;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Double getMonthlyRentAmount() {
        return monthlyRentAmount;
    }

    public void setMonthlyRentAmount(Double monthlyRentAmount) {
        this.monthlyRentAmount = monthlyRentAmount;
    }

    public Double getMonthlyEstimatedServiceCharges() {
        return monthlyEstimatedServiceCharges;
    }

    public void setMonthlyEstimatedServiceCharges(Double monthlyEstimatedServiceCharges) {
        this.monthlyEstimatedServiceCharges = monthlyEstimatedServiceCharges;
    }

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Boolean getPetsAllowedFlag() {
        return petsAllowedFlag;
    }

    public void setPetsAllowedFlag(Boolean petsAllowedFlag) {
        this.petsAllowedFlag = petsAllowedFlag;
    }

    public Tenant getMainTenant() {
        return mainTenant;
    }

    public void setMainTenant(Tenant mainTenant) {
        this.mainTenant = mainTenant;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}