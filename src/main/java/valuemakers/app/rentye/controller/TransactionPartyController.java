package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.Transaction;
import valuemakers.app.rentye.model.TransactionParty;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.TransactionPartyRepository;
import valuemakers.app.rentye.repository.TransactionRepository;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class TransactionPartyController {
    private static final Logger logger = Logger.getLogger(TransactionPartyController.class.getName());
    private final TransactionPartyRepository transactionPartyRepository;
    private final TransactionRepository transactionRepository;
    private final ContractorRepository contractorRepository;
    private final ApartmentRepository apartmentRepository;

    public TransactionPartyController(TransactionPartyRepository transactionPartyRepository, TransactionRepository transactionRepository, ContractorRepository contractorRepository, ApartmentRepository apartmentRepository) {
        this.transactionPartyRepository = transactionPartyRepository;
        this.transactionRepository = transactionRepository;
        this.contractorRepository = contractorRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @RequestMapping(value = "/transactionParty/list", method = RequestMethod.GET)
    public String getTransactionPartys() {
        return "/transactionParty/view";
    }

    @ModelAttribute("transactions")
    public Collection<Transaction> transactions() {
        return this.transactionRepository.findAll();
    }

    @ModelAttribute("categories")
    public Collection<Apartment> categories() {
        return this.apartmentRepository.findAll();
    }

    @ModelAttribute("allContractors")
    public Collection<Contractor> allContractors() {
        return this.contractorRepository.findAll();
    }

    @ModelAttribute("transactionPartys")
    public Collection<TransactionParty> transactionPartys() {
        return this.transactionPartyRepository.findAll();
    }

    @GetMapping(value = "/transactionParty/add")
    public String getForm(Model model) {
        TransactionParty o = new TransactionParty();
        model.addAttribute("transactionParty", o);
        return "/transactionParty/edit";
    }

    @PostMapping(value = "/transactionParty/add")
    public String processForm(@Valid TransactionParty transactionParty, BindingResult result) {
        if (result.hasErrors()) {
            return "/transactionParty/edit";
        }
        transactionPartyRepository.save(transactionParty);
        return "redirect:/transactionParty/list";
    }

    @GetMapping(value = "/transactionParty/edit/{transactionParty}")
    public String editTransactionParty(@PathVariable TransactionParty transactionParty, Model model) {
        model.addAttribute("transactionParty", transactionParty);
        return "/transactionParty/edit";
    }

    @PostMapping(value = "/transactionParty/edit/{id}")
    public String updateTransactionParty(@Valid @ModelAttribute TransactionParty transactionParty, BindingResult result) {
        if (result.hasErrors()) {
            return "/transactionParty/edit";
        }
        transactionPartyRepository.save(transactionParty);
        return "redirect:/transactionParty/list";
    }

    @GetMapping(value = "/transactionParty/delete/{transactionParty}")
    public String delete(@PathVariable TransactionParty transactionParty) {
        transactionPartyRepository.delete(transactionParty);
        return "redirect:/transactionParty/list";
    }
}