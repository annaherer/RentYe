package valuemakers.app.rentye.controller.fixedasset;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.FixedAssetSubtype;
import valuemakers.app.rentye.repository.FixedAssetSubtypeRepository;
import java.util.Collection;

@Controller
public class FixedAssetSubtypeController {
    private final FixedAssetSubtypeRepository fixedAssetSubtypeRepository;

    public FixedAssetSubtypeController(FixedAssetSubtypeRepository fixedAssetSubtypeRepository) {
        this.fixedAssetSubtypeRepository = fixedAssetSubtypeRepository;
    }

    @GetMapping(value = "/fixedAssetSubtype/list")
    public String getFixedAssetSubtypes() {
        return "/fixedAssetSubtype/view";
    }

    @ModelAttribute("fixedAssetSubtypes")
    public Collection<FixedAssetSubtype> fixedAssetSubtypes() {
        return this.fixedAssetSubtypeRepository.findAll();
    }

    @GetMapping("/fixedAssetSubtype/add")
    public String addFixedAssetSubtype(Model model) {
        FixedAssetSubtype fixedAssetSubtype = new FixedAssetSubtype();
        model.addAttribute("fixedAssetSubtype", fixedAssetSubtype);
        return "/fixedAssetSubtype/edit";
    }

    @PostMapping("/fixedAssetSubtype/add")
    public String processAddFixedAssetSubtype(@Valid @ModelAttribute FixedAssetSubtype fixedAssetSubtype, BindingResult result) {
        if (result.hasErrors()) {
            return "/fixedAssetSubtype/edit";
        }
        this.fixedAssetSubtypeRepository.save(fixedAssetSubtype);
        return "redirect:/fixedAssetSubtype/list";
    }

    @GetMapping(value = "/fixedAssetSubtype/edit/{fixedAssetSubtype}")
    public String editFixedAssetSubtype(@PathVariable FixedAssetSubtype fixedAssetSubtype, Model model) {
        model.addAttribute("fixedAssetSubtype", fixedAssetSubtype);
        return "/fixedAssetSubtype/edit";
    }

    @PostMapping(value = "/fixedAssetSubtype/edit/{id}")
    public String processEditFixedAssetSubtype(@Valid @ModelAttribute FixedAssetSubtype fixedAssetSubtype, BindingResult result) {
        if (result.hasErrors()) {
            return "/fixedAssetSubtype/edit";
        }
        this.fixedAssetSubtypeRepository.save(fixedAssetSubtype);
        return "redirect:/fixedAssetSubtype/list";
    }

    @GetMapping(value = "/fixedAssetSubtype/delete/{fixedAssetSubtype}")
    public String delete(@PathVariable FixedAssetSubtype fixedAssetSubtype) {
        this.fixedAssetSubtypeRepository.delete(fixedAssetSubtype);
        return "redirect:/fixedAssetSubtype/list";
    }
}