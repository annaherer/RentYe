package valuemakers.app.rentye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InProgressController {

        @GetMapping("/inProgress")
        public String displayInProgress() {
            return "inProgress";
    }
}