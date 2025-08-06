package be.kdg.programming3.spaceMissions.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/") // Handles requests to the root URL
    public String index() {
        return "index"; // Returns the index.html view
    }


}
