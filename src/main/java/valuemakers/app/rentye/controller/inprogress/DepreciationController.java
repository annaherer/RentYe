package valuemakers.app.rentye.controller.inprogress;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.Depreciation;
import valuemakers.app.rentye.repository.DepreciationRepository;
import java.util.Collection;

@Controller
public class DepreciationController {
    private final DepreciationRepository depreciationRepository;

    public DepreciationController(DepreciationRepository depreciationRepository) {
        this.depreciationRepository = depreciationRepository;
    }

    @GetMapping(value = "/depreciation/list")
    public String getDepreciations() {
        return "depreciation/view";
    }

    @ModelAttribute("depreciations")
    public Collection<Depreciation> depreciations() {
        return this.depreciationRepository.findAll();
    }

    @GetMapping("/depreciation/add")
    public String addDepreciation(Model model) {
        Depreciation depreciation = new Depreciation();
        model.addAttribute("depreciation", depreciation);
        return "depreciation/edit";
    }

    @PostMapping("/depreciation/add")
    public String processAddDepreciation(@Valid @ModelAttribute Depreciation depreciation, BindingResult result) {
        if (result.hasErrors()) {
            return "depreciation/edit";
        }
        this.depreciationRepository.save(depreciation);
        return "redirect:/depreciation/list";
    }

    @GetMapping(value = "/depreciation/edit/{depreciation}")
    public String editDepreciation(@PathVariable Depreciation depreciation, Model model) {
        model.addAttribute("depreciation", depreciation);
        return "depreciation/edit";
    }

    @PostMapping(value = "/depreciation/edit/{id}")
    public String processEditDepreciation(@Valid @ModelAttribute Depreciation depreciation, BindingResult result) {
        if (result.hasErrors()) {
            return "depreciation/edit";
        }
        this.depreciationRepository.save(depreciation);
        return "redirect:/depreciation/list";
    }

    @GetMapping(value = "/depreciation/delete/{depreciation}")
    public String delete(@PathVariable Depreciation depreciation) {
        this.depreciationRepository.delete(depreciation);
        return "redirect:/depreciation/list";
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