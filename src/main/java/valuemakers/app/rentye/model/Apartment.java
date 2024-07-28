package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
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
    private String description;
    private String detailedDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate acquisitionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate acceptanceToUseDate;
    private String country;
    private String city;
    private String zip;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;
    private Double usableArea;
    private String notarialActNumber;
    private String landMortgageRegisterNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dateSold;
    private Boolean active;
}