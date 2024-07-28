package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.model.ContractPeriod;
import valuemakers.app.rentye.model.Tenant;
import valuemakers.app.rentye.repository.ContractRepository;
import valuemakers.app.rentye.repository.ContractPeriodRepository;
import valuemakers.app.rentye.repository.TenantRepository;

import java.util.Collection;

@RequestMapping("/contract")
@Controller
public class TenantController {
    private final TenantRepository tenantRepository;
    private final ContractRepository contractRepository;
    private final ContractPeriodRepository contractPeriodRepository;

    public TenantController(TenantRepository tenantRepository, ContractRepository contractRepository, ContractPeriodRepository contractPeriodRepository) {
        this.tenantRepository = tenantRepository;
        this.contractRepository = contractRepository;
        this.contractPeriodRepository = contractPeriodRepository;
    }

    @RequestMapping(value = "/tenant/list", method = RequestMethod.GET)
    public String getTenants() {
        return "contract/tenantList";
    }

    @ModelAttribute("contracts")
    public Collection<Contract> contracts() {
        return this.contractRepository.findAll();
    }

    @ModelAttribute("contractPeriods")
    public Collection<ContractPeriod> contractPeriods() {
        return this.contractPeriodRepository.findAll();
    }

    @ModelAttribute("tenants")
    public Collection<Tenant> tenants() {
        return this.tenantRepository.findAll();
    }

    @GetMapping(value = "/tenant/add")
    public String getForm(Model model) {
        Tenant tenant = new Tenant();
        model.addAttribute("tenant", tenant);
        model.addAttribute("operation", "add");
        return "contract/tenantAddEdit";
    }

    @PostMapping(value = "/tenant/add")
    public String processForm(@Valid Tenant tenant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "add");
            return "contract/tenantAddEdit";
        }
        tenantRepository.save(tenant);
        return "redirect:/contract/tenant/list";
    }

    @GetMapping(value = "/tenant/display/{tenant}")
    public String displayTenant(@PathVariable Tenant tenant, Model model) {
        model.addAttribute("operation", "display");
        return "contract/tenantAddEdit";
    }

    @GetMapping(value = "/tenant/edit/{tenant}")
    public String editTenant(@PathVariable Tenant tenant, Model model) {
        model.addAttribute("operation", "edit");
        return "contract/tenantAddEdit";
    }

    @PostMapping(value = "/tenant/edit/{id}")
    public String updateTenant(@Valid @ModelAttribute Tenant tenant, BindingResult result, Model model) {
        if (!tenant.getActive()) {
            if (!contractRepository.findByTenantsContainingAndActive(tenant, true).isEmpty()) {
                result.addError(new FieldError("Tenant", "active", "The tenant has active contracts"));
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("operation", "edit");
            return "contract/tenantAddEdit";
        }
        tenantRepository.save(tenant);
        return "redirect:/contract/tenant/list";
    }

    @GetMapping(value = "/tenant/delete/{tenant}")
    public String delete(@PathVariable Tenant tenant, RedirectAttributes redirectAttributes) {
        try {
            this.tenantRepository.delete(tenant);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/contract/tenant/list";
    }
}