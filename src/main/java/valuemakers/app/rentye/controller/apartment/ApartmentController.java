package valuemakers.app.rentye.controller.apartment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ContractorTypeRepository;
import valuemakers.app.rentye.repository.DepreciationRepository;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class ApartmentController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class.getName());
    private final ApartmentRepository apartmentRepository;
    private final ContractorRepository contractorRepository;
    private final ContractorTypeRepository contractorTypeRepository;
    private final DepreciationRepository depreciationRepository;

    public ApartmentController(ApartmentRepository apartmentRepository, ContractorRepository contractorRepository, ContractorTypeRepository contractorTypeRepository,DepreciationRepository depreciationRepository) {
        this.apartmentRepository = apartmentRepository;
        this.contractorRepository = contractorRepository;
        this.contractorTypeRepository = contractorTypeRepository;
        this.depreciationRepository = depreciationRepository;
    }

    @RequestMapping(value ="/apartment/list", method = RequestMethod.GET)
    public String getApartments() {
        return "apartment/list";
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
        return "/apartment/edit";
    }

    @PostMapping("/apartment/add")
    public String processAddApartment(@Valid @ModelAttribute Apartment apartment, BindingResult result) {
        if (result.hasErrors()) {
            return "/apartment/edit";
        }
        this.apartmentRepository.save(apartment);
        return "redirect:/apartment/list";
    }

    @GetMapping(value = "/apartment/edit/{apartment}")
    public String editApartment(@PathVariable Apartment apartment, Model model) {
        model.addAttribute("apartment", apartment);
        return "/apartment/edit";
    }

    @PostMapping(value = "/Apartment/edit/{id}")
    public String processEditApartment(@Valid @ModelAttribute Apartment apartment, BindingResult result) {
        if (result.hasErrors()) {
            return "/apartment/edit";
        }
        this.apartmentRepository.save(apartment);
        return "redirect:/apartment/list";
    }

    @GetMapping(value = "/apartment/delete/{apartment}")
    public String delete(@PathVariable Apartment apartment) {
        this.apartmentRepository.delete(apartment);
        return "redirect:/apartment/list";
    }
}