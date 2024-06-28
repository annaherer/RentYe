package valuemakers.app.rentye.controller.transaction;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.TransactionSubtype;
import valuemakers.app.rentye.model.TransactionType;
import valuemakers.app.rentye.repository.TransactionSubtypeRepository;
import valuemakers.app.rentye.repository.TransactionTypeRepository;

import java.util.Collection;

@Controller
public class TransactionSubtypeController {
    private final TransactionSubtypeRepository transactionSubtypeRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionSubtypeController(TransactionSubtypeRepository transactionSubtypeRepository, TransactionTypeRepository transactionTypeRepository) {
        this.transactionSubtypeRepository = transactionSubtypeRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @GetMapping(value = "/transaction/transactionSubtype/list")
    public String getTransactionSubtypes() {
        return "/transaction/transactionSubtypeList";
    }

    @ModelAttribute("transactionSubtypes")
    public Collection<TransactionSubtype> transactionSubtypes() {
        return this.transactionSubtypeRepository.findAll();
    }

    @ModelAttribute("transactionTypes")
    public Collection<TransactionType> transactionTypes() {
        return this.transactionTypeRepository.findAll();
    }

    @GetMapping("/transaction/transactionSubtype/add")
    public String addTransactionSubtype(Model model) {
        TransactionSubtype transactionSubtype = new TransactionSubtype();
        model.addAttribute("transactionSubtype", transactionSubtype);
        return "/transaction/transactionSubtypeAddEdit";
    }

    @PostMapping("/transaction/transactionSubtype/add")
    public String processAddTransactionSubtype(@Valid @ModelAttribute TransactionSubtype transactionSubtype, BindingResult result) {
        checkCodeUnique(transactionSubtype, result);
        if (result.hasErrors()) {
            return "/transaction/transactionSubtypeAddEdit";
        }
        this.transactionSubtypeRepository.save(transactionSubtype);
        return "redirect:/transaction/transactionSubtype/list";
    }

    @GetMapping(value = "/transaction/transactionSubtype/edit/{transactionSubtype}")
    public String editTransactionSubtype(@PathVariable TransactionSubtype transactionSubtype) {
        return "/transaction/transactionSubtypeAddEdit";
    }

    @PostMapping(value = "/transaction/transactionSubtype/edit/{id}")
    public String processEditTransactionSubtype(@Valid @ModelAttribute TransactionSubtype transactionSubtype, BindingResult result) {
        checkCodeUnique(transactionSubtype, result);
        if (result.hasErrors()) {
            return "/transaction/transactionSubtypeAddEdit";
        }
        this.transactionSubtypeRepository.save(transactionSubtype);
        return "redirect:/transaction/transactionSubtype/list";
    }

    @GetMapping(value = "/transaction/transactionSubtype/delete/{transactionSubtype}")
    public String delete(@PathVariable TransactionSubtype transactionSubtype, RedirectAttributes redirectAttributes) {
        try {
            this.transactionSubtypeRepository.delete(transactionSubtype);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/transaction/transactionSubtype/list";
    }

    private void checkCodeUnique(TransactionSubtype transactionSubtype, BindingResult result) {
        if (transactionSubtypes().stream().anyMatch(t -> !t.getId().equals(transactionSubtype.getId()) && t.getCode().equals(transactionSubtype.getCode()))) {
            result.addError(new FieldError("TransactionSubtype", "code", "Transaction sub-type code must be unique"));
        }
    }
}