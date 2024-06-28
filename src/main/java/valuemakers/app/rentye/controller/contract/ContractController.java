package valuemakers.app.rentye.controller.contract;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.model.ContractPeriod;
import valuemakers.app.rentye.model.Tenant;
import valuemakers.app.rentye.repository.ContractPeriodRepository;
import valuemakers.app.rentye.repository.ContractRepository;
import valuemakers.app.rentye.repository.TenantRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestMapping("/contract")
@Controller
public class ContractController {
    private static final Logger logger = Logger.getLogger(ContractController.class.getName());
    private final ContractRepository contractRepository;
    private final ContractPeriodRepository contractPeriodRepository;
    private final TenantRepository tenantRepository;

    public ContractController(ContractRepository contractRepository, ContractPeriodRepository contractPeriodRepository, TenantRepository tenantRepository) {
        this.contractRepository = contractRepository;
        this.contractPeriodRepository = contractPeriodRepository;
        this.tenantRepository = tenantRepository;
    }

    @ModelAttribute("allTenants")
    public Collection<Tenant> allTenants() {
        return tenantRepository.findAll();
    }

    @GetMapping("/list")
    public String contractList(@RequestParam Apartment apartment, Model model) {
        model.addAttribute("apartment", apartment);
        model.addAttribute("contracts",
                contractRepository.findByApartment(apartment).
                        stream().map(c -> new ContractDTO(
                                c.getId(),
                                c.getActive(),
                                c.getContractPeriods().stream().map(ContractPeriod::getStartDate).min(Comparator.naturalOrder()).orElse(null),
                                c.getContractPeriods().stream().map(ContractPeriod::getEndDate).max(Comparator.naturalOrder()).orElse(null),
                                c.getContractPeriods().stream().map(cp -> cp.getMainTenant().getTransactionParty().getDescription()).distinct().collect(Collectors.joining(", ")),
                                c.getContractPeriods().stream().sorted(Comparator.comparing(ContractPeriod::getSequenceNumber).reversed()).toList().getFirst()
                        )).sorted(Comparator.comparing(ContractDTO::getEndDate).reversed()).toList()
        );

        return "contract/contractList";
    }

    @GetMapping("/details/{contractPeriod}")
    public String displayContract(@PathVariable ContractPeriod contractPeriod, Model model) {
        model.addAttribute("operation", "display");
        model.addAttribute("contractPeriod", contractPeriod);
        model.addAttribute("contractPeriodsList", contractPeriod.getContract().getContractPeriods().stream().sorted(Comparator.comparing(ContractPeriod::getSequenceNumber).reversed()).toList());
        return ("/contract/contractDetails");
    }

    @GetMapping("/addContract")
    public String addContract(@RequestParam Apartment apartment, Model model) {
        ContractPeriod contractPeriod = new ContractPeriod();
        contractPeriod.setActive(false);
        Contract contract = new Contract();
        contract.setActive(false);
        contract.setApartment(apartment);
        contractPeriod.setContract(contract);
        contractPeriod.setSequenceNumber(0);

        model.addAttribute("operation", "add");
        model.addAttribute("contractPeriod", contractPeriod);
        return ("/contract/contractDetails");
    }

    @GetMapping("/addPeriod")
    public String addContractPeriod(@RequestParam Contract contract, Model model) {
        ContractPeriod contractPeriod = new ContractPeriod();
        contractPeriod.setActive(false);
        contractPeriod.setContract(contract);
        contractPeriod.setSequenceNumber(contract.getContractPeriods().stream().map(ContractPeriod::getSequenceNumber).max(Comparator.naturalOrder()).orElse(-1) + 1);

        model.addAttribute("operation", "add");
        model.addAttribute("contractPeriod", contractPeriod);
        return ("/contract/contractDetails");
    }

    @PostMapping({"/addContract", "/addPeriod"})
    public String processAddContract(@Valid @ModelAttribute ContractPeriod contractPeriod, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "add");
            return "/contract/contractDetails";
        }
        Contract contract = contractPeriod.getContract();
        if (contract.getContractPeriods() == null) {
            contract.setContractPeriods(new ArrayList<>());
        }
        contract.getContractPeriods().add(contractPeriod);
        contractRepository.save(contractPeriod.getContract());
        redirectAttributes.addAttribute("apartment", contractPeriod.getContract().getApartment().getId());
        return "redirect:/contract/list";
    }

    @GetMapping("/edit/{contractPeriod}")
    public String editContract(@PathVariable ContractPeriod contractPeriod, Model model) {
        model.addAttribute("operation", "edit");
        model.addAttribute("contractPeriod", contractPeriod);
        return ("/contract/contractDetails");
    }

    @PostMapping("/edit/{id}")
    public String processEditContract(@Valid @ModelAttribute ContractPeriod contractPeriod, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        validateContractDates(contractPeriod, result);
        if (result.hasErrors()) {
            model.addAttribute("operation", "add");
            return "/contract/contractDetails";
        }
        contractRepository.save(contractPeriod.getContract());
        contractPeriodRepository.save(contractPeriod);
        redirectAttributes.addAttribute("apartment", contractPeriod.getContract().getApartment().getId());
        return "redirect:/contract/list";
    }

    @GetMapping("/toggleActivePeriod/{contractPeriod}")
    public String toggleActivePeriod(@PathVariable ContractPeriod contractPeriod) {
        if (!contractPeriod.getActive()) {
            ContractPeriod previousActive = contractPeriod.getContract().getContractPeriods().stream().filter(ContractPeriod::getActive).findFirst().orElse(null);
            if (previousActive != null) {
                previousActive.setActive(false);
                contractPeriodRepository.save(previousActive);
            }
            else {
                contractPeriod.getContract().setActive(true);
                contractRepository.save(contractPeriod.getContract());
            }
            contractPeriod.setActive(true);
        } else {
            contractPeriod.getContract().setActive(false);
            contractPeriod.setActive(false);
            contractRepository.save(contractPeriod.getContract());
        }

        contractPeriodRepository.save(contractPeriod);
        return "redirect:/contract/details/" + contractPeriod.getId();
    }

    private void validateContractDates(ContractPeriod validatedContractPeriod, BindingResult result) {
        List<ContractPeriod> contractPeriodList = new ArrayList<ContractPeriod>();
        contractPeriodList.add(validatedContractPeriod);
        contractPeriodList.addAll(validatedContractPeriod.getContract().getContractPeriods().stream().filter(cp -> !cp.getId().equals(validatedContractPeriod.getId())).toList());

        LocalDate currentDate = null;
        for(ContractPeriod contractPeriod: contractPeriodList.stream().sorted(Comparator.comparing(ContractPeriod::getSequenceNumber)).toList()) {
            if (contractPeriod.getStartDate().isAfter(contractPeriod.getEndDate())) {
                String msg = "Start date cannot be after end date";
                result.addError(new FieldError("contractPeriod", "endDate", msg));
                return;
            }

            if (currentDate != null) {
                if(!currentDate.plusDays(1).equals(contractPeriod.getStartDate())) {
                    String msg = "Inconsistent contract period dates";
                    result.addError(new FieldError("contractPeriod", "endDate", msg));
                    return;
                }
            }

            currentDate = contractPeriod.getEndDate();
        }
    }
}