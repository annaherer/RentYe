package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.model.FixedAssetSubtype;
import valuemakers.app.rentye.model.FixedAssetType;
import valuemakers.app.rentye.repository.FixedAssetSubtypeRepository;
import valuemakers.app.rentye.repository.FixedAssetTypeRepository;

import java.util.Collection;

@Controller
public class FixedAssetSubtypeController {
    private final FixedAssetSubtypeRepository fixedAssetSubtypeRepository;
    private final FixedAssetTypeRepository fixedAssetTypeRepository;

    public FixedAssetSubtypeController(FixedAssetSubtypeRepository fixedAssetSubtypeRepository, FixedAssetTypeRepository fixedAssetTypeRepository) {
        this.fixedAssetSubtypeRepository = fixedAssetSubtypeRepository;
        this.fixedAssetTypeRepository = fixedAssetTypeRepository;
    }

    @GetMapping(value = "/fixedAsset/fixedAssetSubtype/list")
    public String getFixedAssetSubtypes() {
        return "fixedasset/fixedAssetSubtypeList";
    }

    @ModelAttribute("fixedAssetSubtypes")
    public Collection<FixedAssetSubtype> fixedAssetSubtypes() {
        return this.fixedAssetSubtypeRepository.findAll();
    }

    @ModelAttribute("fixedAssetTypes")
    public Collection<FixedAssetType> fixedAssetTypes() {
        return this.fixedAssetTypeRepository.findAll();
    }

    @GetMapping("/fixedAsset/fixedAssetSubtype/add")
    public String addFixedAssetSubtype(Model model) {
        FixedAssetSubtype fixedAssetSubtype = new FixedAssetSubtype();
        model.addAttribute("fixedAssetSubtype", fixedAssetSubtype);
        return "fixedasset/fixedAssetSubtypeAddEdit";
    }

    @GetMapping(value = "/fixedAsset/fixedAssetSubtype/edit/{fixedAssetSubtype}")
    public String editFixedAssetSubtype(@PathVariable FixedAssetSubtype fixedAssetSubtype) {
        return "fixedasset/fixedAssetSubtypeAddEdit";
    }

    @PostMapping(value = {"/fixedAsset/fixedAssetSubtype/edit/{id}",
            "/fixedAsset/fixedAssetSubtype/add"})
    public String processEditAddFixedAssetSubtype(@Valid @ModelAttribute FixedAssetSubtype fixedAssetSubtype, BindingResult result) {
        if (result.hasErrors()) {
            return "fixedasset/fixedAssetSubtypeAddEdit";
        }
        this.fixedAssetSubtypeRepository.save(fixedAssetSubtype);
        return "redirect:/fixedAsset/fixedAssetSubtype/list";
    }

    @GetMapping(value = "/fixedAsset/fixedAssetSubtype/delete/{fixedAssetSubtype}")
    public String delete(@PathVariable FixedAssetSubtype fixedAssetSubtype, RedirectAttributes redirectAttributes) {
        try {
            this.fixedAssetSubtypeRepository.delete(fixedAssetSubtype);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("message", "Delete restricted: related records exist");
        }
        return "redirect:/fixedAsset/fixedAssetSubtype/list";
    }
}