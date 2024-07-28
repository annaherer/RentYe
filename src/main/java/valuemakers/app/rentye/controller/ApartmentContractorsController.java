package valuemakers.app.rentye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.ApartmentContractor;
import valuemakers.app.rentye.model.ScheduledPayment;
import valuemakers.app.rentye.service.ApartmentService;
import valuemakers.app.rentye.service.ContractorService;
import valuemakers.app.rentye.util.ServiceErrorException;

@RequestMapping("/apartment/contractors")
@Controller
public class ApartmentContractorsController {
    private final ApartmentService apartmentService;
    private final ContractorService contractorService;

    public ApartmentContractorsController(ApartmentService apartmentService, ContractorService contractorService) {
        this.apartmentService = apartmentService;
        this.contractorService = contractorService;
    }

    @PostMapping("/add/{apartmentId}")
    public String processAddApartmentContractor(@ModelAttribute ApartmentContractor apartmentContractor, @PathVariable Long apartmentId, RedirectAttributes redirectAttributes, Model model) {
        try {
            apartmentService.addContractor(apartmentId, apartmentContractor.getContractor().getId(), false);
        } catch (ServiceErrorException ex) {
            redirectAttributes.addAttribute("message", ex.getMessage());
        }

        return "redirect:/apartment/details/" + apartmentId;
    }

    @GetMapping("/delete/{apartmentId}/{contractorId}")
    public String deleteApartmentContractor(@PathVariable Long apartmentId, @PathVariable Long contractorId) {
        apartmentService.deleteContractor(apartmentId, contractorId);
        return "redirect:/apartment/details/" + apartmentId;
    }

    @GetMapping("/toggleSettlement/{apartmentId}/{contractorId}")
    public String toggleContractorSettlement(@PathVariable Long apartmentId, @PathVariable Long contractorId) {
        apartmentService.setContractorSettlePaymentsWithTenant(apartmentId, contractorId,
                !apartmentService.getContractorSettlePaymentsWithTenant(apartmentId, contractorId));

        return "redirect:/apartment/details/" + apartmentId;
    }

    @PostMapping("/addScheduledPayment/{apartmentId}/{contractorId}")
    public String processAddScheduledPayment(@ModelAttribute ScheduledPayment scheduledPayment, BindingResult result, @PathVariable Long apartmentId, @PathVariable Long contractorId, Model model) {
        try {
            apartmentService.addScheduledPayment(apartmentId, contractorId, scheduledPayment.getDate(), scheduledPayment.getAmount());
        } catch (ServiceErrorException ex) {
            for(ObjectError error : ex.getErrors()) result.addError(error);
            ApartmentController.prepareModelForDisplay(apartmentService, apartmentId, contractorService, contractorId, model);
            return "apartment/apartmentDetails";
        }

        return "redirect:/apartment/details/" + apartmentId + "?contractorId=" + contractorId;
    }

    @GetMapping("/deleteScheduledPayment/{apartmentId}/{contractorId}/{scheduledPaymentId}")
    public String deleteScheduledPayment(@PathVariable Long apartmentId, @PathVariable Long contractorId, @PathVariable Long scheduledPaymentId) {
        apartmentService.deleteScheduledPayment(scheduledPaymentId);

        Apartment apartment = apartmentService.getApartment(apartmentId);
        ApartmentContractor apartmentContractor = apartment.getApartmentContractors().stream().filter(ac -> ac.getContractor().getId().equals(contractorId)).findFirst().orElse(null);
        return "redirect:/apartment/details/" + apartmentId + "?contractorId=" + contractorId;
    }
}
