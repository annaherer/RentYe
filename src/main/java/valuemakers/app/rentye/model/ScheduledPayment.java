package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class ScheduledPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private ApartmentContractor apartmentContractor;

    @NotNull(message = "Date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @DecimalMin(value = "0.01", message = "Amount is required.")
    private double amount;

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ApartmentContractor getAppartmentContractor() {
        return apartmentContractor;
    }

    public void setApartmentContractor(ApartmentContractor apartmentContractor) {
        this.apartmentContractor = apartmentContractor;
    }
}