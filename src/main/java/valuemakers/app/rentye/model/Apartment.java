package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Depreciation depreciation;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<ApartmentContractor> apartmentContractors;

    @NotBlank(message = "Description is required")
    @Size(max=600, message="Description must be at most 600 characters.")
    private String description;
    private String detailedDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate acquisitionDate;
    //@NotBlank(message = "Acceptance to use date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate acceptanceToUseDate;
    //@NotBlank(message = "Country is required")
    private String country;
    //@NotBlank(message = "City is required")
    private String city;
    //@NotBlank(message = "Zip code is required")
    private String zip;
    //@NotBlank(message = "Street is required")
    private String street;
    //@NotBlank(message = "House number is required")
    private Integer houseNumber;
    private Integer flatNumber;
    //@NotBlank(message = "Usable area is required")
    private Double usableArea;
    private String notarialActNumber;
    private String landMortgageRegisterNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dateSold;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Depreciation getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Depreciation depreciation) {
        this.depreciation = depreciation;
    }

    public List<ApartmentContractor> getApartmentContractors() {
        return apartmentContractors;
    }

    public void setApartmentContractors(List<ApartmentContractor> apartmentContractors) {
        this.apartmentContractors = apartmentContractors;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LocalDate getAcceptanceToUseDate() {
        return acceptanceToUseDate;
    }

    public void setAcceptanceToUseDate(LocalDate acceptanceToUseDate) {
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

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}