package valuemakers.app.rentye.controller.contractor;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.ContractorType;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ContractorTypeRepository;

import java.util.Collection;
import java.util.logging.Logger;

@RequestMapping("/contractor")
@Controller
public class ContractorTypeController {
    private static final Logger logger = Logger.getLogger(ContractorTypeController.class.getName());
    private final ContractorTypeRepository contractorTypeRepository;
    private final ContractorRepository contractorRepository;
    private final ApartmentRepository apartmentRepository;

    public ContractorTypeController(ContractorTypeRepository contractorTypeRepository, ContractorRepository contractorRepository, ApartmentRepository apartmentRepository) {
        this.contractorTypeRepository = contractorTypeRepository;
        this.contractorRepository = contractorRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping("/contractorType/list")
    public String getContractorTypes() {
        return "/contractor/contractorTypeList";
    }

    @ModelAttribute("contractors")
    public Collection<Contractor> contractors() {
        return this.contractorRepository.findAll();
    }

    @ModelAttribute("allApartments")
    public Collection<Apartment> allApartments() {
        return this.apartmentRepository.findAll();
    }

    @ModelAttribute("contractorTypes")
    public Collection<ContractorType> contractorTypes() {
        return this.contractorTypeRepository.findAll();
    }

    @GetMapping(value = "/contractorType/add")
    public String getForm(Model model) {
        ContractorType o = new ContractorType();
        model.addAttribute("contractorType", o);
        return "/contractor/contractorTypeAddEdit";
    }

    @PostMapping(value = "/contractorType/add")
    public String processForm(@Valid ContractorType contractorType, BindingResult result) {
        if (result.hasErrors()) {
            return "/contractor/contractorTypeAddEdit";
        }
        contractorTypeRepository.save(contractorType);
        return "redirect:/contractor/contractorType/list";
    }

    @GetMapping(value = "/contractorType/edit/{contractorType}")
    public String editContractorType(@PathVariable ContractorType contractorType) {
        return "/contractor/contractorTypeAddEdit";
    }

    @PostMapping(value = "/contractorType/edit/{id}")
    public String updateContractorType(@Valid @ModelAttribute ContractorType contractorType, BindingResult result) {
        if (result.hasErrors()) {
            return "/contractor/contractorTypeAddEdit";
        }
        contractorTypeRepository.save(contractorType);
        return "redirect:/contractor/contractorType/list";
    }

    @GetMapping(value = "/contractorType/delete/{contractorType}")
    public String delete(@PathVariable ContractorType contractorType, RedirectAttributes redirectAttributes) {
        try {
            this.contractorTypeRepository.delete(contractorType);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/contractor/contractorType/list";
    }
}