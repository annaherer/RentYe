package valuemakers.app.rentye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.ScheduledPayment;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ScheduledPaymentRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class TestController {
    ContractorRepository contractorRepository;
    ScheduledPaymentRepository scheduledPaymentRepository;
    ApartmentRepository apartmentRepository;

    TestController(ContractorRepository contractorRepository, ScheduledPaymentRepository scheduledPaymentRepository, ApartmentRepository apartmentRepository) {
        this.contractorRepository = contractorRepository;
        this.scheduledPaymentRepository = scheduledPaymentRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping("/test")
    public String test() {
        Contractor contractor = contractorRepository.findById(1L).get();
        Apartment apartment = apartmentRepository.findById(1L).get();

        ScheduledPayment sp1 = new ScheduledPayment();
        sp1.setApartment(apartment);
        sp1.setDate(Date.from(LocalDate.of(2024, 6, 18).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        sp1.setAmount(100.00d);
        sp1.setContractor(contractor);
        scheduledPaymentRepository.save(sp1);

        ScheduledPayment sp2 = new ScheduledPayment();
        sp2.setApartment(apartment);
        sp2.setDate(Date.from(LocalDate.of(2024, 7, 18).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        sp2.setAmount(120.00d);
        sp2.setContractor(contractor);
        scheduledPaymentRepository.save(sp2);

        //contractor.getScheduledPayments().add(sp1);
        //contractor.getScheduledPayments().add(sp2);

        return "/testView";
    }
}