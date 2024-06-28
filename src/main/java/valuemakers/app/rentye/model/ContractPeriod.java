package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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
    @Valid
    private Contract contract;

    @NotNull
    private Integer sequenceNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate signDate;
    @NotNull
    private Double rentAmount;
    @NotNull
    private Double serviceChargesAmount;
    @NotNull
    private Integer paymentDay;
    @NotNull
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public Double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(Double monthlyRentAmount) {
        this.rentAmount = monthlyRentAmount;
    }

    public Double getServiceChargesAmount() {
        return serviceChargesAmount;
    }

    public void setServiceChargesAmount(Double monthlyEstimatedServiceCharges) {
        this.serviceChargesAmount = monthlyEstimatedServiceCharges;
    }

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}