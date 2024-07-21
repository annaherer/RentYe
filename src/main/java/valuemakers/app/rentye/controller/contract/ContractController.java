package valuemakers.app.rentye.controller.contract;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contract;
import valuemakers.app.rentye.model.ContractPeriod;
import valuemakers.app.rentye.model.Tenant;
import valuemakers.app.rentye.repository.ContractPeriodRepository;
import valuemakers.app.rentye.repository.ContractRepository;
import valuemakers.app.rentye.repository.TenantRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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
    public Collection<Tenant> getAllTenants() {
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
    public String contractDetails(@PathVariable ContractPeriod contractPeriod, Model model) {
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
        validateContractPeriodsDatesConsistency(contractPeriod, result);
        validateOverlappingContracts(contractPeriod, result);
        validateTenants(contractPeriod, result);

        if (result.hasErrors()) {
            model.addAttribute("operation", "add");
            return "contract/contractDetails";
        }
        Contract contract = contractPeriod.getContract();
        if (contract.getContractPeriods() == null) {
            contract.setContractPeriods(new ArrayList<>());
        }
        contract.getContractPeriods().add(contractPeriod);
        contractRepository.save(contractPeriod.getContract());

        return "redirect:/contract/details/" + contract.getContractPeriods().stream().max(Comparator.comparing(ContractPeriod::getSequenceNumber)).get().getId();
    }

    @GetMapping("/edit/{contractPeriod}")
    public String editContract(@PathVariable ContractPeriod contractPeriod, Model model) {
        model.addAttribute("operation", "edit");
        model.addAttribute("contractPeriod", contractPeriod);
        return ("/contract/contractDetails");
    }

    @PostMapping("/edit/{id}")
    public String processEditContract(@Valid @ModelAttribute ContractPeriod contractPeriod, BindingResult result, Model model) {
        validateContractPeriodsDatesConsistency(contractPeriod, result);
        validateOverlappingContracts(contractPeriod, result);
        validateTenants(contractPeriod, result);

        if (result.hasErrors()) {
            model.addAttribute("operation", "edit");
            return "contract/contractDetails";
        }
        contractRepository.save(contractPeriod.getContract());
        contractPeriodRepository.save(contractPeriod);

        return "redirect:/contract/details/" + contractPeriod.getId();
    }

    @GetMapping("/deleteContract/{contract}")
    public String deleteContract(@PathVariable Contract contract, RedirectAttributes redirectAttributes) {
        Apartment apartment = contract.getApartment();
        contractRepository.delete(contract);
        redirectAttributes.addAttribute("apartment", apartment.getId());
        return "redirect:/contract/list";
    }

    @GetMapping("/deleteContractPeriod/{contractPeriod}")
    public String deleteContract(@PathVariable ContractPeriod contractPeriod, RedirectAttributes redirectAttributes) {
        Contract contract = contractPeriod.getContract();

        if (contract.getContractPeriods() == null || contract.getContractPeriods().size() == 1) {
            redirectAttributes.addAttribute("message", "Can't remove the only period in the contract, please remove contract instead");
        } else if (!contractPeriod.getSequenceNumber().equals(contract.getContractPeriods().stream().max(Comparator.comparing(ContractPeriod::getSequenceNumber)).get().getSequenceNumber())) {
            redirectAttributes.addAttribute("message", "Only the last contract period can be removed");
        }
        else {
            if (contractPeriod.getActive()) {
                contract.setActive(false);
                contractRepository.save(contract);
            }

            contract.getContractPeriods().remove(contractPeriod);
            contractPeriodRepository.delete(contractPeriod);
        }

        return "redirect:/contract/details/" + contract.getContractPeriods().stream().max(Comparator.comparing(ContractPeriod::getSequenceNumber)).get().getId();
    }

    @GetMapping("/toggleActivePeriod/{contractPeriod}")
    public String toggleActivePeriod(@PathVariable ContractPeriod contractPeriod) {
        if (!contractPeriod.getActive()) {
            ContractPeriod previousActive = contractPeriod.getContract().getContractPeriods().stream().filter(ContractPeriod::getActive).findFirst().orElse(null);
            if (previousActive != null) {
                previousActive.setActive(false);
                contractPeriodRepository.save(previousActive);
            } else {
                Contract activeContract = contractRepository.findByApartmentAndActive(contractPeriod.getContract().getApartment(), true);
                if (activeContract != null) {
                    activeContract.setActive(false);
                    activeContract.getContractPeriods().stream().filter(ContractPeriod::getActive).findFirst().get().setActive(false);
                    contractRepository.save(activeContract);
                }
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

    private void validateContractPeriodsDatesConsistency(ContractPeriod validatedContractPeriod, BindingResult result) {
        List<ContractPeriod> contractPeriodList = new ArrayList<>();
        contractPeriodList.add(validatedContractPeriod);
        if (validatedContractPeriod.getContract().getContractPeriods() != null)
            contractPeriodList.addAll(validatedContractPeriod.getContract().getContractPeriods().stream().filter(cp -> !cp.getId().equals(validatedContractPeriod.getId())).toList());

        LocalDate currentDate = null;
        for (ContractPeriod contractPeriod : contractPeriodList.stream().sorted(Comparator.comparing(ContractPeriod::getSequenceNumber)).toList()) {
            if (contractPeriod.getStartDate().isAfter(contractPeriod.getEndDate())) {
                String msg = "Start date cannot be after end date";
                result.addError(new ObjectError("contractPeriod", msg));
                return;
            }

            if (currentDate != null) {
                if (!currentDate.plusDays(1).equals(contractPeriod.getStartDate())) {
                    String msg = "Inconsistent period dates within contract";
                    result.addError(new ObjectError("contractPeriod", msg));
                    return;
                }
            }

            currentDate = contractPeriod.getEndDate();
        }
    }

    private void validateOverlappingContracts(ContractPeriod validatedContractPeriod, BindingResult result) {
        List<ContractPeriod> allContractPeriods = contractPeriodRepository.findByContractApartment(validatedContractPeriod.getContract().getApartment());
        if (allContractPeriods.stream().anyMatch(
                cp -> !cp.getContract().getId().equals(validatedContractPeriod.getContract().getId()) &&
                        datesOverlap(cp.getStartDate(), cp.getEndDate(), validatedContractPeriod.getStartDate(), validatedContractPeriod.getEndDate())
        )) {
            String msg = "Contract period dates collide with different contract for same apartment";
            result.addError(new ObjectError("contractPeriod", msg));
        }
    }

    private void validateTenants(ContractPeriod validatedContractPeriod, BindingResult result) {
        if (validatedContractPeriod.getContract().getTenants() == null || validatedContractPeriod.getContract().getTenants().isEmpty())
            result.addError(new FieldError("contractPeriod", "contract", "Contract must have some tenants assigned"));
        else if (validatedContractPeriod.getMainTenant() == null)
            result.addError(new ObjectError("contractPeriod", "Main tenant must be assigned to period"));
        else if (validatedContractPeriod.getContract().getTenants().stream().map(Tenant::getId).noneMatch(t -> t.equals(validatedContractPeriod.getMainTenant().getId()))) {
            result.addError(new ObjectError("contractPeriod", "Main tenant in period must be assigned to contract"));
        }
    }

    boolean datesOverlap(LocalDate period1Start, LocalDate period1End, LocalDate period2Start, LocalDate period2End) {
        return (period1Start.equals(period2End) || period1Start.isBefore(period2End)) &&
                (period1End.equals(period2Start) || period1End.isAfter(period2Start));
    }
}