package valuemakers.app.rentye.controller.contractor;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.ContractorType;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.ScheduledPayment;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ContractorTypeRepository;
import valuemakers.app.rentye.repository.ScheduledPaymentRepository;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class ContractorController {
    private static final Logger logger = Logger.getLogger(ContractorController.class.getName());
    private final ContractorRepository contractorRepository;
    private final ApartmentRepository apartmentRepository;
    private final ContractorTypeRepository contractorTypeRepository;
    private final ScheduledPaymentRepository scheduledPaymentRepository;

    public ContractorController(ContractorRepository contractorRepository, ApartmentRepository apartmentRepository, ContractorTypeRepository contractorTypeRepository, ScheduledPaymentRepository scheduledPaymentRepository) {
        this.contractorRepository = contractorRepository;
        this.apartmentRepository = apartmentRepository;
        this.contractorTypeRepository = contractorTypeRepository;
        this.scheduledPaymentRepository = scheduledPaymentRepository;
    }

    @RequestMapping(value = "/contractor/list", method = RequestMethod.GET)
    public String getContractors() {
        return "/contractor/view";
    }

    @ModelAttribute("apartments")
    public Collection<Apartment> apartments() {
        return this.apartmentRepository.findAll();
    }

    @ModelAttribute("categories")
    public Collection<ScheduledPayment> categories() {
        return this.scheduledPaymentRepository.findAll();
    }

    @ModelAttribute("allContractorTypes")
    public Collection<ContractorType> allContractorTypes() {
        return this.contractorTypeRepository.findAll();
    }

    @ModelAttribute("contractors")
    public Collection<Contractor> contractors() {
        return this.contractorRepository.findAll();
    }

    @GetMapping(value = "/contractor/add")
    public String getForm(Model model) {
        Contractor o = new Contractor();
        model.addAttribute("contractor", o);
        return "/contractor/edit";
    }

    @PostMapping(value = "/contractor/add")
    public String processForm(@Valid Contractor contractor, BindingResult result) {
        if (result.hasErrors()) {
            return "/contractor/edit";
        }
        contractorRepository.save(contractor);
        return "redirect:/contractor/list";
    }

    @GetMapping(value = "/contractor/edit/{contractor}")
    public String editContractor(@PathVariable Contractor contractor, Model model) {
        model.addAttribute("contractor", contractor);
        return "/contractor/edit";
    }

    @PostMapping(value = "/contractor/edit/{id}")
    public String updateContractor(@Valid @ModelAttribute Contractor contractor, BindingResult result) {
        if (result.hasErrors()) {
            return "/contractor/edit";
        }
        contractorRepository.save(contractor);
        return "redirect:/contractor/list";
    }

    @GetMapping(value = "/contractor/delete/{contractor}")
    public String delete(@PathVariable Contractor contractor) {
        contractorRepository.delete(contractor);
        return "redirect:/contractor/list";
    }
}