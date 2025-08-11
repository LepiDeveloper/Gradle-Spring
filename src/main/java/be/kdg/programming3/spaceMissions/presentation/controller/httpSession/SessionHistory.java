package be.kdg.programming3.spaceMissions.presentation.controller.httpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionHistory {

    private final List<String> history = new ArrayList<>();

    public void addPageVisit(String page) {
        history.add(LocalDateTime.now() + " - " + page);
    }

    public List<String> getHistory() {
        return history;
    }

}
