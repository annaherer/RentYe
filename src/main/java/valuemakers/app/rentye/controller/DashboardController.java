package valuemakers.app.rentye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DashboardController {

    @GetMapping
    public String displayDashboard() {
        return "applicationDashboard";
    }

    @GetMapping("/inProgress")
    public String displayInProgress() {
        return "inProgress";
    }

    @GetMapping("/about")
    public String displayAbout() {
        return "about";
    }

    @GetMapping("/contact")
    public String displayContact() {
        return "contact";
    }
}