package valuemakers.app.rentye.controller.transaction;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Transaction;
import valuemakers.app.rentye.repository.TransactionRepository;
import java.util.Collection;

@Controller
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(value = "/transaction/list")
    public String getTransactions() {
        return "/transaction/view";
    }

    @ModelAttribute("transactions")
    public Collection<Transaction> transactions() {
        return this.transactionRepository.findAll();
    }

    @GetMapping("/transaction/add")
    public String addTransaction(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "/transaction/edit";
    }

    @PostMapping("/transaction/add")
    public String processAddTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "/transaction/edit";
        }
        this.transactionRepository.save(transaction);
        return "redirect:/transaction/list";
    }

    @GetMapping(value = "/transaction/edit/{transaction}")
    public String editTransaction(@PathVariable Transaction transaction, Model model) {
        model.addAttribute("transaction", transaction);
        return "/transaction/edit";
    }

    @PostMapping(value = "/transaction/edit/{id}")
    public String processEditTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "/transaction/edit";
        }
        this.transactionRepository.save(transaction);
        return "redirect:/transaction/list";
    }

    @GetMapping(value = "/transaction/delete/{transaction}")
    public String delete(@PathVariable Transaction transaction) {
        this.transactionRepository.delete(transaction);
        return "redirect:/transaction/list";
    }
}