package valuemakers.app.rentye.controller.apartment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.ApartmentContractor;
import valuemakers.app.rentye.model.ScheduledPayment;
import valuemakers.app.rentye.repository.ApartmentContractorRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ScheduledPaymentRepository;

import java.util.logging.Logger;

@RequestMapping("/apartment/contractors")
@Controller
public class ApartmentContractorsController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class.getName());
    private final ContractorRepository contractorRepository;
    private final ApartmentContractorRepository apartmentContractorRepository;
    private final ScheduledPaymentRepository scheduledPaymentRepository;

    public ApartmentContractorsController(ContractorRepository contractorRepository, ApartmentContractorRepository apartmentContractorRepository, ScheduledPaymentRepository scheduledPaymentRepository) {
        this.contractorRepository = contractorRepository;
        this.apartmentContractorRepository = apartmentContractorRepository;
        this.scheduledPaymentRepository = scheduledPaymentRepository;
    }

    @PostMapping("/add/{apartment}")
    public String processAddApartmentContractor(@ModelAttribute ApartmentContractor apartmentContractor, @PathVariable Apartment apartment) {
        //To do validations: check for duplicates
        apartmentContractor.setApartment(apartment);
        apartmentContractor.setSettlePaymentsWithTenant(false);

        apartmentContractorRepository.save(apartmentContractor);
        return "redirect:/apartment/details/" + apartment.getId();
    }

    @GetMapping("/delete/{apartmentContractor}")
    public String deleteApartmentContractor(@PathVariable ApartmentContractor apartmentContractor) {
        apartmentContractor.getApartment().getApartmentContractors().remove(apartmentContractor);
        apartmentContractorRepository.delete(apartmentContractor);
        return "redirect:/apartment/details/" + apartmentContractor.getApartment().getId();
    }

    @GetMapping("/toggleSettlement/{apartmentContractor}")
    public String toggleContractorSettlement(@PathVariable ApartmentContractor apartmentContractor) {
        apartmentContractor.setSettlePaymentsWithTenant(!apartmentContractor.getSettlePaymentsWithTenant());
        apartmentContractorRepository.save(apartmentContractor);
        return "redirect:/apartment/details/" + apartmentContractor.getApartment().getId();
    }

    @PostMapping("/addScheduledPayment/{apartmentContractor}")
    public String processAddScheduledPayment(@Valid @ModelAttribute ScheduledPayment scheduledPayment, BindingResult result, Model model, @PathVariable ApartmentContractor apartmentContractor) {
        //To do validations: check for duplicate dates

        if (result.hasErrors()) {
            model.addAttribute("operation", "display");
            model.addAttribute("contractors", contractorRepository.findAll().stream().filter(c -> !apartmentContractor.getApartment().getApartmentContractors().stream().map(ApartmentContractor::getContractor).toList().contains(c)).toList());
            model.addAttribute("apartmentContractor", apartmentContractor);
            model.addAttribute("apartment", apartmentContractor.getApartment());
            return "/apartment/apartmentDetails";
        }

        scheduledPayment.setApartmentContractor(apartmentContractor);

        scheduledPaymentRepository.save(scheduledPayment);
        return "redirect:/apartment/details/" + apartmentContractor.getApartment().getId() + "?apartmentContractor=" + apartmentContractor.getId();
    }

    @GetMapping("/deleteScheduledPayment/{scheduledPayment}")
    public String deleteScheduledPayment(@PathVariable ScheduledPayment scheduledPayment) {
        scheduledPayment.getAppartmentContractor().getScheduledPayments().remove(scheduledPayment);
        scheduledPaymentRepository.delete(scheduledPayment);
        return "redirect:/apartment/details/" + scheduledPayment.getAppartmentContractor().getApartment().getId() + "?apartmentContractor=" + scheduledPayment.getAppartmentContractor().getId();
    }
}
