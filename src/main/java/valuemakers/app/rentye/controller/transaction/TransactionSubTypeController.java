package valuemakers.app.rentye.controller.transaction;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.TransactionSubType;
import valuemakers.app.rentye.repository.TransactionSubTypeRepository;
import java.util.Collection;

@Controller
public class TransactionSubTypeController {
    private final TransactionSubTypeRepository transactionSubTypeRepository;

    public TransactionSubTypeController(TransactionSubTypeRepository transactionSubTypeRepository) {
        this.transactionSubTypeRepository = transactionSubTypeRepository;
    }

    @GetMapping(value = "/transactionSubType/list")
    public String getTransactionSubTypes() {
        return "/transactionSubType/view";
    }

    @ModelAttribute("transactionSubTypes")
    public Collection<TransactionSubType> transactionSubTypes() {
        return this.transactionSubTypeRepository.findAll();
    }

    @GetMapping("/transactionSubType/add")
    public String addTransactionSubType(Model model) {
        TransactionSubType transactionSubType = new TransactionSubType();
        model.addAttribute("transactionSubType", transactionSubType);
        return "/transactionSubType/edit";
    }

    @PostMapping("/transactionSubType/add")
    public String processAddTransactionSubType(@Valid @ModelAttribute TransactionSubType transactionSubType, BindingResult result) {
        if (result.hasErrors()) {
            return "/transactionSubType/edit";
        }
        this.transactionSubTypeRepository.save(transactionSubType);
        return "redirect:/transactionSubType/list";
    }

    @GetMapping(value = "/transactionSubType/edit/{transactionSubType}")
    public String editTransactionSubType(@PathVariable TransactionSubType transactionSubType, Model model) {
        model.addAttribute("transactionSubType", transactionSubType);
        return "/transactionSubType/edit";
    }

    @PostMapping(value = "/transactionSubType/edit/{id}")
    public String processEditTransactionSubType(@Valid @ModelAttribute TransactionSubType transactionSubType, BindingResult result) {
        if (result.hasErrors()) {
            return "/transactionSubType/edit";
        }
        this.transactionSubTypeRepository.save(transactionSubType);
        return "redirect:/transactionSubType/list";
    }

    @GetMapping(value = "/transactionSubType/delete/{transactionSubType}")
    public String delete(@PathVariable TransactionSubType transactionSubType) {
        this.transactionSubTypeRepository.delete(transactionSubType);
        return "redirect:/transactionSubType/list";
    }
}