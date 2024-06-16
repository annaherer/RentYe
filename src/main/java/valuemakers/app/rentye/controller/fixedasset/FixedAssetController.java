package valuemakers.app.rentye.controller.fixedasset;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valuemakers.app.rentye.model.FixedAsset;
import valuemakers.app.rentye.repository.FixedAssetRepository;
import java.util.Collection;

@Controller
public class FixedAssetController {
    private final FixedAssetRepository fixedAssetRepository;

    public FixedAssetController(FixedAssetRepository fixedAssetRepository) {
        this.fixedAssetRepository = fixedAssetRepository;
    }

    @GetMapping(value = "/fixedAsset/list")
    public String getFixedAssets() {
        return "/fixedAsset/view";
    }

    @ModelAttribute("fixedAssets")
    public Collection<FixedAsset> fixedAssets() {
        return this.fixedAssetRepository.findAll();
    }

    @GetMapping("/fixedAsset/add")
    public String addFixedAsset(Model model) {
        FixedAsset fixedAsset = new FixedAsset();
        model.addAttribute("fixedAsset", fixedAsset);
        return "/fixedAsset/edit";
    }

    @PostMapping("/fixedAsset/add")
    public String processAddFixedAsset(@Valid @ModelAttribute FixedAsset fixedAsset, BindingResult result) {
        if (result.hasErrors()) {
            return "/fixedAsset/edit";
        }
        this.fixedAssetRepository.save(fixedAsset);
        return "redirect:/fixedAsset/list";
    }

    @GetMapping(value = "/fixedAsset/edit/{fixedAsset}")
    public String editFixedAsset(@PathVariable FixedAsset fixedAsset, Model model) {
        model.addAttribute("fixedAsset", fixedAsset);
        return "/fixedAsset/edit";
    }

    @PostMapping(value = "/fixedAsset/edit/{id}")
    public String processEditFixedAsset(@Valid @ModelAttribute FixedAsset fixedAsset, BindingResult result) {
        if (result.hasErrors()) {
            return "/fixedAsset/edit";
        }
        this.fixedAssetRepository.save(fixedAsset);
        return "redirect:/fixedAsset/list";
    }

    @GetMapping(value = "/fixedAsset/delete/{fixedAsset}")
    public String delete(@PathVariable FixedAsset fixedAsset) {
        this.fixedAssetRepository.delete(fixedAsset);
        return "redirect:/fixedAsset/list";
    }
}