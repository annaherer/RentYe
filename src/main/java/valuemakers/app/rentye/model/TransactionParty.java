package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TransactionParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String description;
    private String firstName;
    private String secondName;
    private String surname;
    private String country;
    private String city;
    private String zip;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;
    private String phoneNumber;
    private String email;
}