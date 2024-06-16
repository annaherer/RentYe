package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.ScheduledPayment;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ScheduledPaymentRepository;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
public class ScheduledPaymentController {
    private static final Logger logger = Logger.getLogger(ScheduledPaymentController.class.getName());
    private final ScheduledPaymentRepository scheduledPaymentRepository;
    private final ContractorRepository contractorRepository;
    private final ApartmentRepository apartmentRepository;

    public ScheduledPaymentController(ScheduledPaymentRepository scheduledPaymentRepository, ContractorRepository contractorRepository, ApartmentRepository apartmentRepository) {
        this.scheduledPaymentRepository = scheduledPaymentRepository;
        this.contractorRepository = contractorRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @RequestMapping(value = "/scheduledPayment/list", method = RequestMethod.GET)
    public String getScheduledPayments() {
        return "/scheduledPayment/view";
    }

    @ModelAttribute("contractors")
    public Collection<Contractor> contractors() {
        return this.contractorRepository.findAll();
    }

    @ModelAttribute("allApartments")
    public Collection<Apartment> allApartments() {
        return this.apartmentRepository.findAll();
    }

    @ModelAttribute("scheduledPayments")
    public Collection<ScheduledPayment> scheduledPayments() {
        return this.scheduledPaymentRepository.findAll();
    }

    @GetMapping(value = "/scheduledPayment/add")
    public String getForm(Model model) {
        ScheduledPayment o = new ScheduledPayment();
        model.addAttribute("scheduledPayment", o);
        return "/scheduledPayment/edit";
    }

    @PostMapping(value = "/scheduledPayment/add")
    public String processForm(@Valid ScheduledPayment scheduledPayment, BindingResult result) {
        if (result.hasErrors()) {
            return "/scheduledPayment/edit";
        }
        scheduledPaymentRepository.save(scheduledPayment);
        return "redirect:/scheduledPayment/list";
    }

    @GetMapping(value = "/scheduledPayment/edit/{scheduledPayment}")
    public String editScheduledPayment(@PathVariable ScheduledPayment scheduledPayment, Model model) {
        model.addAttribute("scheduledPayment", scheduledPayment);
        return "/scheduledPayment/edit";
    }

    @PostMapping(value = "/scheduledPayment/edit/{id}")
    public String updateScheduledPayment(@Valid @ModelAttribute ScheduledPayment scheduledPayment, BindingResult result) {
        if (result.hasErrors()) {
            return "/scheduledPayment/edit";
        }
        scheduledPaymentRepository.save(scheduledPayment);
        return "redirect:/scheduledPayment/list";
    }

    @GetMapping(value = "/scheduledPayment/delete/{scheduledPayment}")
    public String delete(@PathVariable ScheduledPayment scheduledPayment) {
        scheduledPaymentRepository.delete(scheduledPayment);
        return "redirect:/scheduledPayment/list";
    }
}