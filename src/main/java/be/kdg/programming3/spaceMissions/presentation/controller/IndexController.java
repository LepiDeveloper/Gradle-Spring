package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.presentation.controller.httpSession.SessionHistory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final SessionHistory sessionHistory;

    public IndexController(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    @GetMapping("/")
    public String index() {
        sessionHistory.addPageVisit("Main Page");
        return "index";
    }


}
