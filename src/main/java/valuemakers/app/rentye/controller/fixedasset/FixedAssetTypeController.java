package valuemakers.app.rentye.controller.fixedasset;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.FixedAssetType;
import valuemakers.app.rentye.repository.FixedAssetTypeRepository;
import java.util.Collection;

@Controller
public class FixedAssetTypeController {
    private final FixedAssetTypeRepository fixedAssetTypeRepository;

    public FixedAssetTypeController(FixedAssetTypeRepository fixedAssetTypeRepository) {
        this.fixedAssetTypeRepository = fixedAssetTypeRepository;
    }

    @GetMapping(value = "/fixedAssetType/list")
    public String getFixedAssetTypes() {
        return "/fixedAssetType/view";
    }

    @ModelAttribute("fixedAssetTypes")
    public Collection<FixedAssetType> fixedAssetTypes() {
        return this.fixedAssetTypeRepository.findAll();
    }

    @GetMapping("/fixedAssetType/add")
    public String addFixedAssetType(Model model) {
        FixedAssetType fixedAssetType = new FixedAssetType();
        model.addAttribute("fixedAssetType", fixedAssetType);
        return "/fixedAssetType/edit";
    }

    @PostMapping("/fixedAssetType/add")
    public String processAddFixedAssetType(@Valid @ModelAttribute FixedAssetType fixedAssetType, BindingResult result) {
        if (result.hasErrors()) {
            return "/fixedAssetType/edit";
        }
        this.fixedAssetTypeRepository.save(fixedAssetType);
        return "redirect:/fixedAssetType/list";
    }

    @GetMapping(value = "/fixedAssetType/edit/{fixedAssetType}")
    public String editFixedAssetType(@PathVariable FixedAssetType fixedAssetType, Model model) {
        model.addAttribute("fixedAssetType", fixedAssetType);
        return "/fixedAssetType/edit";
    }

    @PostMapping(value = "/fixedAssetType/edit/{id}")
    public String processEditFixedAssetType(@Valid @ModelAttribute FixedAssetType fixedAssetType, BindingResult result) {
        if (result.hasErrors()) {
            return "/fixedAssetType/edit";
        }
        this.fixedAssetTypeRepository.save(fixedAssetType);
        return "redirect:/fixedAssetType/list";
    }

    @GetMapping(value = "/fixedAssetType/delete/{fixedAssetType}")
    public String delete(@PathVariable FixedAssetType fixedAssetType) {
        this.fixedAssetTypeRepository.delete(fixedAssetType);
        return "redirect:/fixedAssetType/list";
    }
}