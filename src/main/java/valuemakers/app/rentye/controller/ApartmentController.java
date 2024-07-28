package valuemakers.app.rentye.controller;

import org.springframework.data.repository.query.Param;
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

import java.util.Collection;

@RequestMapping("/apartment")
@Controller
public class ApartmentController {
    private final ContractorService contractorService;
    private final ApartmentService apartmentService;

    public ApartmentController(ContractorService contractorService, ApartmentService apartmentService) {
        this.contractorService = contractorService;
        this.apartmentService = apartmentService;
    }

    @ModelAttribute("apartments")
    public Collection<Apartment> apartments() {
        return this.apartmentService.getAllApartments();
    }

    @GetMapping(value = "/list")
    public String getAllApartments() {
        return "apartment/apartmentList";
    }

    public static void prepareModelForDisplay(ApartmentService apartmentService, Long apartmentId, ContractorService contractorService, Long contractorId, Model model) {
        Apartment apartment = apartmentService.getApartment(apartmentId);
        ApartmentContractor apartmentContractor = apartment.getApartmentContractors().stream().filter(ac -> ac.getContractor().getId().equals(contractorId)).findFirst().orElse(null);

        model.addAttribute("apartment", apartment);
        model.addAttribute("operation", "display");
        model.addAttribute("contractors", contractorService.getAllContractors().stream().filter(c -> !apartment.getApartmentContractors().stream().map(ApartmentContractor::getContractor).toList().contains(c)).toList());
        model.addAttribute("apartmentContractor", new ApartmentContractor());
        if (apartmentContractor != null) {
            model.addAttribute("contractor", apartmentContractor.getContractor());
            model.addAttribute("allScheduledPayments", apartmentContractor.getScheduledPayments());
        }
    }

    @GetMapping(value = "/details/{apartmentId}")
    public String apartmentDetails(@PathVariable Long apartmentId, @Param("contractorId") Long contractorId, Model model) {
        prepareModelForDisplay(apartmentService, apartmentId, contractorService, contractorId, model);
        model.addAttribute("scheduledPayment", new ScheduledPayment());
        return "apartment/apartmentDetails";
    }

    @GetMapping("/add")
    public String addApartment(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("operation", "add");
        return "apartment/apartmentDetails";
    }

    @PostMapping("/add")
    public String processAddApartment(@ModelAttribute Apartment apartment, BindingResult result, Model model) {
        try {
            apartmentService.addApartment(apartment);
        } catch (ServiceErrorException ex) {
            for(ObjectError error : ex.getErrors()) result.addError(error);
            model.addAttribute("operation", "add");
            return "apartment/apartmentDetails";
        }

        return "redirect:/apartment/details/" + apartment.getId();
    }

    @GetMapping(value = "/edit/{apartmentId}")
    public String editApartment(@PathVariable Long apartmentId, Model model) {
        model.addAttribute("operation", "edit");
        model.addAttribute("apartment", apartmentService.getApartment(apartmentId));
        return "apartment/apartmentDetails";
    }

    @PostMapping(value = "/edit/{id}")
    public String processEditApartment(@ModelAttribute Apartment apartment, BindingResult result, Model model) {
        try {
            apartmentService.updateApartment(apartment);
        } catch (ServiceErrorException ex) {
            for(ObjectError error : ex.getErrors()) result.addError(error);
            model.addAttribute("operation", "edit");
            return "apartment/apartmentDetails";
        }

        return "redirect:/apartment/details/" + apartment.getId();
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            apartmentService.deleteApartment(id);
        } catch (ServiceErrorException ex) {
            redirectAttributes.addAttribute("message", ex.getMessage());
        }

        return "redirect:/apartment/list";
    }
}