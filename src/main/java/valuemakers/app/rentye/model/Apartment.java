package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date acquisitionDate;
    private Date acceptanceToUseDate;
    private String country;
    private String city;
    private String zip;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;
    private Double usableArea;
    private String notarialActNumber;
    private String landMortgageRegisterNumber;
    private Date dateSold;
    private Boolean status;

    @OneToOne
    private Depreciation depreciation;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private List<Contractor> contractors;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private List<ContractorType> applicableUtilities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public Date getAcceptanceToUseDate() {
        return acceptanceToUseDate;
    }

    public void setAcceptanceToUseDate(Date acceptanceToUseDate) {
        this.acceptanceToUseDate = acceptanceToUseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Double getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(Double usableArea) {
        this.usableArea = usableArea;
    }

    public String getNotarialActNumber() {
        return notarialActNumber;
    }

    public void setNotarialActNumber(String notarialActNumber) {
        this.notarialActNumber = notarialActNumber;
    }

    public String getLandMortgageRegisterNumber() {
        return landMortgageRegisterNumber;
    }

    public void setLandMortgageRegisterNumber(String landMortgageRegisterNumber) {
        this.landMortgageRegisterNumber = landMortgageRegisterNumber;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Depreciation getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Depreciation depreciation) {
        this.depreciation = depreciation;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public List<ContractorType> getApplicableUtilities() {
        return applicableUtilities;
    }

    public void setApplicableUtilities(List<ContractorType> applicableUtilities) {
        this.applicableUtilities = applicableUtilities;
    }
}