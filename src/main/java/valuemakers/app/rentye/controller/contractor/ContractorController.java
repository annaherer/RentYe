package valuemakers.app.rentye.controller.contractor;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.*;
import valuemakers.app.rentye.repository.*;
import java.util.Collection;
import java.util.logging.Logger;

@RequestMapping("/contractor")
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
        return "contractor/contractorList";
    }

    @ModelAttribute("apartments")
    public Collection<Apartment> apartments() {
        return this.apartmentRepository.findAll();
    }

    @ModelAttribute("categories")
    public Collection<ScheduledPayment> categories() {
        return this.scheduledPaymentRepository.findAll();
    }

    @ModelAttribute("contractorTypes")
    public Collection<ContractorType> contractorTypes() {
        return this.contractorTypeRepository.findAll();
    }

    @ModelAttribute("contractors")
    public Collection<Contractor> contractors() {
        return this.contractorRepository.findAll();
    }

    @GetMapping(value = "/contractor/add")
    public String getForm(Model model) {
        Contractor contractor = new Contractor();
        contractor.setTransactionParty(new TransactionParty());
        model.addAttribute("contractor", contractor);
        model.addAttribute("operation", "add");
        return "contractor/contractorAddEdit";
    }

    @PostMapping(value = "/contractor/add")
    public String processForm(@Valid Contractor contractor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contractor", contractor);
            model.addAttribute("operation", "add");
            return "contractor/contractorAddEdit";
        }
        this.contractorRepository.save(contractor);
        return "redirect:/contractor/contractor/list";
    }

    @GetMapping(value = "/contractor/display/{contractor}")
    public String displayTenant(@PathVariable Contractor contractor, Model model) {
        model.addAttribute("operation", "display");
        return "contractor/contractorAddEdit";
    }

    @GetMapping(value = "/contractor/edit/{contractor}")
    public String editContractor(@PathVariable Contractor contractor, Model model) {
        model.addAttribute("operation", "edit");
        return "contractor/contractorAddEdit";
    }

    @PostMapping(value = "/contractor/edit/{id}")
    public String updateContractor(@Valid @ModelAttribute Contractor contractor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "edit");
            return "contractor/contractorAddEdit";
        }
        this.contractorRepository.save(contractor);
        return "redirect:/contractor/contractor/list";
    }

    @GetMapping(value = "/contractor/delete/{contractor}")
    public String delete(@PathVariable Contractor contractor, RedirectAttributes redirectAttributes) {
        try {
            this.contractorRepository.delete(contractor);
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/contractor/contractor/list";
    }
}