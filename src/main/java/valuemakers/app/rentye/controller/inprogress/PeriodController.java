package valuemakers.app.rentye.controller.inprogress;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Period;
import valuemakers.app.rentye.repository.PeriodRepository;
import java.util.Collection;

@Controller
public class PeriodController {
    private final PeriodRepository periodRepository;

    public PeriodController(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    @GetMapping(value = "/period/list")
    public String getPeriods() {
        return "period/view";
    }

    @ModelAttribute("periods")
    public Collection<Period> periods() {
        return this.periodRepository.findAll();
    }

    @GetMapping("/period/add")
    public String addPeriod(Model model) {
        Period period = new Period();
        model.addAttribute("period", period);
        return "period/edit";
    }

    @PostMapping("/period/add")
    public String processAddPeriod(@Valid @ModelAttribute Period period, BindingResult result) {
        if (result.hasErrors()) {
            return "period/edit";
        }
        this.periodRepository.save(period);
        return "redirect:/period/list";
    }

    @GetMapping(value = "/period/edit/{period}")
    public String editPeriod(@PathVariable Period period, Model model) {
        model.addAttribute("period", period);
        return "period/edit";
    }

    @PostMapping(value = "/period/edit/{id}")
    public String processEditPeriod(@Valid @ModelAttribute Period period, BindingResult result) {
        if (result.hasErrors()) {
            return "period/edit";
        }
        this.periodRepository.save(period);
        return "redirect:/period/list";
    }

    @GetMapping(value = "/period/delete/{period}")
    public String delete(@PathVariable Period period) {
        this.periodRepository.delete(period);
        return "redirect:/period/list";
    }


    //@GetMapping(value = "/fixedAssetType/delete/{fixedAssetType}")
    //    public String delete(@PathVariable FixedAssetType fixedAssetType, RedirectAttributes redirectAttributes) {
    //        try {
    //            this.fixedAssetTypeRepository.delete(fixedAssetType);
    //        } catch (DataIntegrityViolationException e) {
    //            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
    //        }
    //        return "redirect:/fixedAsset/fixedAssetType/list";
    //    }
}