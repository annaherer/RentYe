package valuemakers.app.rentye.controller.transaction;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Transaction;
import valuemakers.app.rentye.model.TransactionSort;
import valuemakers.app.rentye.model.TransactionType;
import valuemakers.app.rentye.repository.TransactionTypeRepository;
import java.util.Collection;

@Controller
public class TransactionTypeController {
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeController(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @GetMapping(value = "/transaction/transactionType/list")
    public String getTransactionTypes() {
        return "/transaction/transactionTypeList";
    }

    @ModelAttribute("transactionTypes")
    public Collection<TransactionType> transactionTypes() {
        return this.transactionTypeRepository.findAll();
    }

    @GetMapping("/transaction/transactionType/add")
    public String addTransactionType(Model model) {
        TransactionType transactionType = new TransactionType();
        transactionType.setDefaultTransactionSort(TransactionSort.EXCLUDED);
        model.addAttribute("transactionType", transactionType);
        return "/transaction/transactionTypeAddEdit";
    }

    @PostMapping("/transaction/transactionType/add")
    public String processAddTransactionType(@Valid @ModelAttribute TransactionType transactionType, BindingResult result) {
        if (result.hasErrors()) {
            return "/transaction/transactionTypeAddEdit";
        }
        this.transactionTypeRepository.save(transactionType);
        return "redirect:/transaction/transactionType/list";
    }

    @GetMapping(value = "/transaction/transactionType/edit/{transactionType}")
    public String editTransactionType(@PathVariable TransactionType transactionType, Model model) {
        model.addAttribute("transactionType", transactionType);
        return "/transaction/transactionTypeAddEdit";
    }

    @PostMapping(value = "/transaction/transactionType/edit/{id}")
    public String processEditTransactionType(@Valid @ModelAttribute TransactionType transactionType, BindingResult result) {
        if (result.hasErrors()) {
            return "/transaction/transactionTypeAddEdit";
        }
        this.transactionTypeRepository.save(transactionType);
        return "redirect:/transaction/transactionType/list";
    }

    @GetMapping(value = "/transaction/transactionType/delete/{transactionType}")
    public String delete(@PathVariable TransactionType transactionType) {
        this.transactionTypeRepository.delete(transactionType);
        return "redirect:/transaction/transactionType/list";
    }
}