package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
public class Depreciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "depreciation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "depreciation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DepreciationTransaction> depreciationTransactions;

    @NotBlank(message = "Property price is required.")
    private Double propertyPrice;
    private Double purchaseCost;
    private Double plotArea;
    private Double plotEstimatedValueSquareMeter;
    private Double plotEstimatedValue;
    private Double purchaseCostApportionedToInitialValue;
    private Double initialValue;
    private Boolean status;
    private Date rate;
    private Double monthlyDepreciationAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(Double propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public Double getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(Double plotArea) {
        this.plotArea = plotArea;
    }

    public Double getPlotEstimatedValueSquareMeter() {
        return plotEstimatedValueSquareMeter;
    }

    public void setPlotEstimatedValueSquareMeter(Double plotEstimatedValueSquareMeter) {
        this.plotEstimatedValueSquareMeter = plotEstimatedValueSquareMeter;
    }

    public Double getPlotEstimatedValue() {
        return plotEstimatedValue;
    }

    public void setPlotEstimatedValue(Double plotEstimatedValue) {
        this.plotEstimatedValue = plotEstimatedValue;
    }

    public Double getPurchaseCostApportionedToInitialValue() {
        return purchaseCostApportionedToInitialValue;
    }

    public void setPurchaseCostApportionedToInitialValue(Double purchaseCostApportionedToInitialValue) {
        this.purchaseCostApportionedToInitialValue = purchaseCostApportionedToInitialValue;
    }

    public Double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Double initialValue) {
        this.initialValue = initialValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getRate() {
        return rate;
    }

    public void setRate(Date rate) {
        this.rate = rate;
    }

    public Double getMonthlyDepreciationAmount() {
        return monthlyDepreciationAmount;
    }

    public void setMonthlyDepreciationAmount(Double monthlyDepreciationAmount) {
        this.monthlyDepreciationAmount = monthlyDepreciationAmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<DepreciationTransaction> getDepreciationTransactions() {
        return depreciationTransactions;
    }

    public void setDepreciationTransactions(List<DepreciationTransaction> depreciationTransactions) {
        this.depreciationTransactions = depreciationTransactions;
    }
}