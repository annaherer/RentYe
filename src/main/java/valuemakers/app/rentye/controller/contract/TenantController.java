package valuemakers.app.rentye.controller.contract;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.model.ContractPeriod;
import valuemakers.app.rentye.model.Tenant;
import valuemakers.app.rentye.model.TransactionParty;
import valuemakers.app.rentye.repository.ContractPeriodRepository;
import valuemakers.app.rentye.repository.ContractRepository;
import valuemakers.app.rentye.repository.TenantRepository;
import valuemakers.app.rentye.repository.TransactionPartyRepository;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class TenantController {
    private static final Logger logger = Logger.getLogger(TenantController.class.getName());
    private final TenantRepository tenantRepository;
    private final ContractRepository contractRepository;
    private final ContractPeriodRepository contractPeriodRepository;
    private final TransactionPartyRepository transactionPartyRepository;

    public TenantController(TenantRepository tenantRepository, ContractRepository contractRepository, ContractPeriodRepository contractPeriodRepository, TransactionPartyRepository transactionPartyRepository) {
        this.tenantRepository = tenantRepository;
        this.contractRepository = contractRepository;
        this.contractPeriodRepository = contractPeriodRepository;
        this.transactionPartyRepository = transactionPartyRepository;
    }

    @RequestMapping(value = "/tenant/list", method = RequestMethod.GET)
    public String getTenants() {
        return "/tenant/view";
    }

    @ModelAttribute("contracts")
    public Collection<Contract> contracts() {
        return this.contractRepository.findAll();
    }

    @ModelAttribute("categories")
    public Collection<TransactionParty> categories() {
        return this.transactionPartyRepository.findAll();
    }

    @ModelAttribute("allContractPeriods")
    public Collection<ContractPeriod> allContractPeriods() {
        return this.contractPeriodRepository.findAll();
    }

    @ModelAttribute("tenants")
    public Collection<Tenant> tenants() {
        return this.tenantRepository.findAll();
    }

    @GetMapping(value = "/tenant/add")
    public String getForm(Model model) {
        Tenant o = new Tenant();
        model.addAttribute("tenant", o);
        return "/tenant/edit";
    }

    @PostMapping(value = "/tenant/add")
    public String processForm(@Valid Tenant tenant, BindingResult result) {
        if (result.hasErrors()) {
            return "/tenant/edit";
        }
        tenantRepository.save(tenant);
        return "redirect:/tenant/list";
    }

    @GetMapping(value = "/tenant/edit/{tenant}")
    public String editTenant(@PathVariable Tenant tenant, Model model) {
        model.addAttribute("tenant", tenant);
        return "/tenant/edit";
    }

    @PostMapping(value = "/tenant/edit/{id}")
    public String updateTenant(@Valid @ModelAttribute Tenant tenant, BindingResult result) {
        if (result.hasErrors()) {
            return "/tenant/edit";
        }
        tenantRepository.save(tenant);
        return "redirect:/tenant/list";
    }

    @GetMapping(value = "/tenant/delete/{tenant}")
    public String delete(@PathVariable Tenant tenant) {
        tenantRepository.delete(tenant);
        return "redirect:/tenant/list";
    }
}