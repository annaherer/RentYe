package valuemakers.app.rentye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.ContractorType;
import valuemakers.app.rentye.model.TransactionParty;
import valuemakers.app.rentye.service.ContractorService;
import valuemakers.app.rentye.util.ServiceErrorException;

import java.util.Collection;

@RequestMapping("/contractor")
@Controller
public class ContractorController {
    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @ModelAttribute("contractorTypes")
    public Collection<ContractorType> contractorTypes() {
        return this.contractorService.getAllContractorTypes();
    }

    @ModelAttribute("contractors")
    public Collection<Contractor> contractors() {
        return this.contractorService.getAllContractors();
    }

    @GetMapping(value = "/contractor/list")
    public String getAllContractors() {
        return "contractor/contractorList";
    }

    @GetMapping(value = "/contractor/add")
    public String addContractor(Model model) {
        Contractor contractor = new Contractor();
        contractor.setTransactionParty(new TransactionParty());
        model.addAttribute("contractor", contractor);
        model.addAttribute("operation", "add");
        return "contractor/contractorAddEdit";
    }

    @PostMapping(value = "/contractor/add")
    public String processAddContractor(@ModelAttribute Contractor contractor, BindingResult result, Model model) {
        try {
            contractorService.addContractor(contractor);
        } catch (ServiceErrorException ex) {
            for(ObjectError error : ex.getErrors()) result.addError(error);
            model.addAttribute("contractor", contractor);
            model.addAttribute("operation", "add");
            return "contractor/contractorAddEdit";
        }

        return "redirect:/contractor/contractor/list";
    }

    @GetMapping(value = "/contractor/display/{contractorId}")
    public String displayContractor(@PathVariable Long contractorId, Model model) {
        model.addAttribute("operation", "display");
        model.addAttribute("contractor", contractorService.getContractor(contractorId));
        return "contractor/contractorAddEdit";
    }

    @GetMapping(value = "/contractor/edit/{contractorId}")
    public String editContractor(@PathVariable Long contractorId, Model model) {
        model.addAttribute("operation", "edit");
        model.addAttribute("contractor", contractorService.getContractor(contractorId));
        return "contractor/contractorAddEdit";
    }

    @PostMapping(value = "/contractor/edit/{id}")
    public String updateContractor(@ModelAttribute Contractor contractor, BindingResult result, Model model) {
        try {
            contractorService.updateContractor(contractor);
        } catch (ServiceErrorException ex) {
            for(ObjectError error : ex.getErrors()) result.addError(error);
            model.addAttribute("contractor", contractor);
            model.addAttribute("operation", "edit");
            return "contractor/contractorAddEdit";
        }

        return "redirect:/contractor/contractor/list";
    }

    @GetMapping(value = "/contractor/delete/{contractorId}")
    public String deleteContractor(@PathVariable Long contractorId, RedirectAttributes redirectAttributes) {
        try {
            contractorService.deleteContractor(contractorId);
        } catch (ServiceErrorException ex) {
            redirectAttributes.addAttribute("message", ex.getMessage());
        }

        return "redirect:/contractor/contractor/list";
    }
}