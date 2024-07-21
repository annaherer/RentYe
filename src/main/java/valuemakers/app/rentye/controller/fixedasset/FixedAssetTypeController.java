package valuemakers.app.rentye.controller.fixedasset;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.FixedAssetType;
import valuemakers.app.rentye.model.TransactionType;
import valuemakers.app.rentye.repository.FixedAssetTypeRepository;

import java.util.Collection;

@RequestMapping("/fixedAsset")
@Controller
public class FixedAssetTypeController {
    private final FixedAssetTypeRepository fixedAssetTypeRepository;

    public FixedAssetTypeController(FixedAssetTypeRepository fixedAssetTypeRepository) {
        this.fixedAssetTypeRepository = fixedAssetTypeRepository;
    }

    @GetMapping(value = "/fixedAssetType/list")
    public String getFixedAssetTypes() {
        return "fixedasset/fixedAssetTypeList";
    }

    @ModelAttribute("fixedAssetTypes")
    public Collection<FixedAssetType> fixedAssetTypes() {
        return this.fixedAssetTypeRepository.findAll();
    }

    @GetMapping("/fixedAssetType/add")
    public String addFixedAssetType(Model model) {
        FixedAssetType fixedAssetType = new FixedAssetType();
        model.addAttribute("fixedAssetType", fixedAssetType);
        return "fixedasset/fixedAssetTypeAddEdit";
    }

    @PostMapping("/fixedAssetType/add")
    public String processAddFixedAssetType(@Valid @ModelAttribute FixedAssetType fixedAssetType, BindingResult result) {
        if (result.hasErrors()) {
            return "fixedasset/fixedAssetTypeAddEdit";
        }
        this.fixedAssetTypeRepository.save(fixedAssetType);
        return "redirect:/fixedAsset/fixedAssetType/list";
    }

    @GetMapping(value = "/fixedAssetType/edit/{fixedAssetType}")
    public String editFixedAssetType(@PathVariable FixedAssetType fixedAssetType) {
        return "fixedasset/fixedAssetTypeAddEdit";
    }

    @PostMapping(value = "/fixedAssetType/edit/{id}")
    public String processEditFixedAssetType(@Valid @ModelAttribute FixedAssetType fixedAssetType, BindingResult result) {
        if (result.hasErrors()) {
            return "fixedasset/fixedAssetTypeAddEdit";
        }
        this.fixedAssetTypeRepository.save(fixedAssetType);
        return "redirect:/fixedAsset/fixedAssetType/list";
    }

    @GetMapping(value = "/fixedAssetType/delete/{fixedAssetType}")
    public String delete(@PathVariable FixedAssetType fixedAssetType, RedirectAttributes redirectAttributes) {
        try {
            this.fixedAssetTypeRepository.delete(fixedAssetType);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/fixedAsset/fixedAssetType/list";
    }
}