package valuemakers.app.rentye.controller.apartment;

import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
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

@RequestMapping("/apartment/dashboard")
@Controller
public class ApartmentDashboardController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class.getName());
    private final ContractorRepository contractorRepository;
    private final ApartmentContractorRepository apartmentContractorRepository;
    private final ScheduledPaymentRepository scheduledPaymentRepository;

    public ApartmentDashboardController(ContractorRepository contractorRepository, ApartmentContractorRepository apartmentContractorRepository, ScheduledPaymentRepository scheduledPaymentRepository) {
        this.contractorRepository = contractorRepository;
        this.apartmentContractorRepository = apartmentContractorRepository;
        this.scheduledPaymentRepository = scheduledPaymentRepository;
    }

    @GetMapping(value = "/display/{apartment}")
    public String displayDashboard(@PathVariable Apartment apartment, @Param("apartmentContractor") ApartmentContractor apartmentContractor, Model model) {
        model.addAttribute("operation", "display");
        model.addAttribute("contractors", contractorRepository.findAll().stream().filter(c -> !apartment.getApartmentContractors().stream().map(ApartmentContractor::getContractor).toList().contains(c)).toList());
        model.addAttribute("apartmentContractor", apartmentContractor);
        model.addAttribute("scheduledPayment", new ScheduledPayment());
        return "/apartment/apartmentDashboard";
    }

    @PostMapping("/addApartmentContractor/{apartment}")
    public String processAddApartmentContractor(@ModelAttribute ApartmentContractor apartmentContractor, @PathVariable Apartment apartment) {
        //To do validations: check for duplicates
        apartmentContractor.setApartment(apartment);
        apartmentContractor.setSettlePaymentsWithTenant(false);

        apartmentContractorRepository.save(apartmentContractor);
        return "redirect:../display/" + apartment.getId();
    }

    @GetMapping("/removeApartmentContractor/{apartmentContractor}")
    public String removeApartmentApartmentContractor(@PathVariable ApartmentContractor apartmentContractor) {
        apartmentContractor.getApartment().getApartmentContractors().remove(apartmentContractor);
        apartmentContractorRepository.delete(apartmentContractor);
        return "redirect:../display/" + apartmentContractor.getApartment().getId();
    }

    @GetMapping("/toggleContractorSettlement/{apartmentContractor}")
    public String toggleContractorSettlement(@PathVariable ApartmentContractor apartmentContractor) {
        apartmentContractor.setSettlePaymentsWithTenant(!apartmentContractor.getSettlePaymentsWithTenant());
        apartmentContractorRepository.save(apartmentContractor);
        return "redirect:../display/" + apartmentContractor.getApartment().getId();
    }

    @PostMapping("/addScheduledPayment/{apartmentContractor}")
    public String processAddScheduledPayment(@Valid @ModelAttribute ScheduledPayment scheduledPayment, BindingResult result, Model model, @PathVariable ApartmentContractor apartmentContractor) {
        //To do validations: check for duplicate dates

        if (result.hasErrors()) {
            model.addAttribute("operation", "display");
            model.addAttribute("contractors", contractorRepository.findAll().stream().filter(c -> !apartmentContractor.getApartment().getApartmentContractors().stream().map(ApartmentContractor::getContractor).toList().contains(c)).toList());
            model.addAttribute("apartmentContractor", apartmentContractor);
            model.addAttribute("apartment", apartmentContractor.getApartment());
            return "/apartment/apartmentDashboard";
        }

        scheduledPayment.setApartmentContractor(apartmentContractor);

        scheduledPaymentRepository.save(scheduledPayment);
        return "redirect:../display/" + apartmentContractor.getApartment().getId() + "?apartmentContractor=" + apartmentContractor.getId();
    }

    @GetMapping("/removeScheduledPayment/{scheduledPayment}")
    public String removeScheduledPayment(@PathVariable ScheduledPayment scheduledPayment) {
        scheduledPayment.getAppartmentContractor().getScheduledPayments().remove(scheduledPayment);
        scheduledPaymentRepository.delete(scheduledPayment);
        return "redirect:../display/" + scheduledPayment.getAppartmentContractor().getApartment().getId() + "?apartmentContractor=" + scheduledPayment.getAppartmentContractor().getId();
    }
}
