package valuemakers.app.rentye.controller.contract;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.model.Tenant;
import valuemakers.app.rentye.model.ContractPeriod;
import valuemakers.app.rentye.repository.ContractPeriodRepository;
import valuemakers.app.rentye.repository.ContractRepository;
import valuemakers.app.rentye.repository.TenantRepository;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class ContractPeriodController {
    private static final Logger logger = Logger.getLogger(ContractPeriodController.class.getName());
    private final ContractPeriodRepository contractPeriodRepository;
    private final ContractRepository contractRepository;
    private final TenantRepository tenantRepository;

    public ContractPeriodController(ContractPeriodRepository contractPeriodRepository, ContractRepository contractRepository, TenantRepository tenantRepository) {
        this.contractPeriodRepository = contractPeriodRepository;
        this.contractRepository = contractRepository;
        this.tenantRepository = tenantRepository;
    }

    @RequestMapping(value = "/contractPeriod/list", method = RequestMethod.GET)
    public String getContractPeriods() {
        return "/contractPeriod/view";
    }

    @ModelAttribute("contracts")
    public Collection<Contract> contracts() {
        return this.contractRepository.findAll();
    }

    @ModelAttribute("allTenants")
    public Collection<Tenant> allTenants() {
        return this.tenantRepository.findAll();
    }

    @ModelAttribute("contractPeriods")
    public Collection<ContractPeriod> contractPeriods() {
        return this.contractPeriodRepository.findAll();
    }

    @GetMapping(value = "/contractPeriod/add")
    public String getForm(Model model) {
        ContractPeriod o = new ContractPeriod();
        model.addAttribute("contractPeriod", o);
        return "/contractPeriod/edit";
    }

    @PostMapping(value = "/contractPeriod/add")
    public String processForm(@Valid ContractPeriod contractPeriod, BindingResult result) {
        if (result.hasErrors()) {
            return "/contractPeriod/edit";
        }
        contractPeriodRepository.save(contractPeriod);
        return "redirect:/contractPeriod/list";
    }

    @GetMapping(value = "/contractPeriod/edit/{contractPeriod}")
    public String editContractPeriod(@PathVariable ContractPeriod contractPeriod, Model model) {
        model.addAttribute("contractPeriod", contractPeriod);
        return "/contractPeriod/edit";
    }

    @PostMapping(value = "/contractPeriod/edit/{id}")
    public String updateContractPeriod(@Valid @ModelAttribute ContractPeriod contractPeriod, BindingResult result) {
        if (result.hasErrors()) {
            return "/contractPeriod/edit";
        }
        contractPeriodRepository.save(contractPeriod);
        return "redirect:/contractPeriod/list";
    }

    @GetMapping(value = "/contractPeriod/delete/{contractPeriod}")
    public String delete(@PathVariable ContractPeriod contractPeriod) {
        contractPeriodRepository.delete(contractPeriod);
        return "redirect:/contractPeriod/list";
    }  
}