package valuemakers.app.rentye.controller.contract;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.controller.apartment.ApartmentController;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.repository.*;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class ContractController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class.getName());
    private final ContractRepository contractRepository;
    private final ContractPeriodRepository contractPeriodRepository;
    private final ApartmentRepository apartmentRepository;
    private final TenantRepository tenantRepository;

    public ContractController(ContractRepository contractRepository, ContractPeriodRepository contractPeriodRepository, ApartmentRepository apartmentRepository, TenantRepository tenantRepository) {
        this.contractRepository = contractRepository;
        this.contractPeriodRepository = contractPeriodRepository;
        this.apartmentRepository = apartmentRepository;
        this.tenantRepository = tenantRepository;
    }

    @RequestMapping(value ="/contract/list", method = RequestMethod.GET)
    public String getContracts() {
        return "contract/list";
    }

    @ModelAttribute("contract_tenants")
    public Collection<Contract> getTenants() {
        return contractRepository.findAll();
    }

    @ModelAttribute("contracts")
    public Collection<Contract> contracts() {
        return this.contractRepository.findAll();
    }

    @GetMapping("/contract/add")
    public String addContract(Model model) {
        Contract contract = new Contract();
        model.addAttribute("contract", contract);
        return "/contract/edit";
    }

    @PostMapping("/contract/add")
    public String processAddContract(@Valid @ModelAttribute Contract contract, BindingResult result) {
        if (result.hasErrors()) {
            return "/contract/edit";
        }
        this.contractRepository.save(contract);
        return "redirect:/contract/list";
    }

    @GetMapping(value = "/contract/edit/{contract}")
    public String editContract(@PathVariable Contract contract, Model model) {
        model.addAttribute("contract", contract);
        return "/contract/edit";
    }

    @PostMapping(value = "/Contract/edit/{id}")
    public String processEditContract(@Valid @ModelAttribute Contract contract, BindingResult result) {
        if (result.hasErrors()) {
            return "/contract/edit";
        }
        this.contractRepository.save(contract);
        return "redirect:/contract/list";
    }

    @GetMapping(value = "/contract/delete/{contract}")
    public String delete(@PathVariable Contract contract) {
        this.contractRepository.delete(contract);
        return "redirect:/contract/list";
    }
}