package valuemakers.app.rentye.controller.apartment;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ContractorTypeRepository;
import valuemakers.app.rentye.repository.DepreciationRepository;

import java.util.Collection;
import java.util.logging.Logger;

@RequestMapping("/apartment")
@Controller
public class ApartmentController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class.getName());
    private final ApartmentRepository apartmentRepository;
    private final ContractorRepository contractorRepository;

    public ApartmentController(ApartmentRepository apartmentRepository, ContractorRepository contractorRepository) {
        this.apartmentRepository = apartmentRepository;
        this.contractorRepository = contractorRepository;
    }

    @RequestMapping(value = "/apartment/list", method = RequestMethod.GET)
    public String getApartments() {
        return "/apartment/apartmentList";
    }

    @ModelAttribute("apartment_applicable_utilities")
    public Collection<Apartment> getApartmentsApplicableUtilities() {
        return apartmentRepository.findAll();
    }

    @ModelAttribute("apartment_contractors")
    public Collection<Contractor> getContractors() {
        return contractorRepository.findAll();
    }

    @ModelAttribute("apartments")
    public Collection<Apartment> apartments() {
        return this.apartmentRepository.findAll();
    }

    @GetMapping("/apartment/add")
    public String addApartment(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("operation", "add");
        return "/apartment/apartmentDashboard";
    }

    @PostMapping("/apartment/add")
    public String processAddApartment(@Valid @ModelAttribute Apartment apartment, BindingResult result, Model model) {
        checkDateSold(apartment, result);
        if (result.hasErrors()) {
            model.addAttribute("operation", "add");
            return "/apartment/apartmentDashboard";
        }
        apartmentRepository.save(apartment);
        return "redirect:/apartment/apartment/list";
    }

    @GetMapping(value = "/apartment/edit/{apartment}")
    public String editApartment(@PathVariable Apartment apartment, Model model) {
        model.addAttribute("operation", "edit");
        return "/apartment/apartmentDashboard";
    }

    @PostMapping(value = "/apartment/edit/{id}")
    public String processEditApartment(@Valid @ModelAttribute Apartment apartment, BindingResult result, Model model) {
        checkDateSold(apartment, result);
        if (result.hasErrors()) {
            model.addAttribute("operation", "edit");
            return "/apartment/apartmentDashboard";
        }
        this.apartmentRepository.save(apartment);
        return "redirect:/apartment/apartment/list";
    }

    @GetMapping(value = "/apartment/delete/{apartment}")
    public String delete(@PathVariable Apartment apartment, RedirectAttributes redirectAttributes) {
        try {
            this.apartmentRepository.delete(apartment);
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/apartment/apartment/list";
    }

    private void checkDateSold(Apartment apartment, BindingResult result) {
        if (apartment.getActive() && apartment.getDateSold()!=null) {
            result.addError(new FieldError("Apartment", "dateSold", "Date sold can't be filled in for active apartment"));
        }
    }
}