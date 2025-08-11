package be.kdg.programming3.spaceMissions.presentation.controller.httpSession;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
    private final SessionHistory sessionHistory;

    public SessionController(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    @GetMapping
    public String showHistory(Model model) {
        model.addAttribute("history", sessionHistory.getHistory());
        return "history";
    }

}
