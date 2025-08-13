package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.presentation.controller.httpSession.SessionHistory;
import be.kdg.programming3.spaceMissions.service.LaunchSiteService;
import be.kdg.programming3.spaceMissions.service.RocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/launchSites")
public class LaunchSiteController {

    private static final Logger logger = LoggerFactory.getLogger(LaunchSiteController.class.getName());
    private final LaunchSiteService launchSiteService;
    private final SessionHistory sessionHistory;

    public LaunchSiteController(LaunchSiteService launchSiteService, SessionHistory sessionHistory) {
        this.launchSiteService = launchSiteService;
        this.sessionHistory = sessionHistory;
    }

    @GetMapping
    public String showLaunchSite(Model model){
        logger.debug("Getting launch site view: ");
        sessionHistory.addPageVisit("Launch Site List");
        model.addAttribute("launchSites", launchSiteService.getAllLaunchSites());
        return "launchSites";
    }

    @GetMapping("/{id}")
    public String getLaunchSiteDetails(@PathVariable int id, Model model) {
        sessionHistory.addPageVisit("Launch Site Details: " + id);
        model.addAttribute("launchSite", launchSiteService.getLaunchSiteById(id));
        return "launchSiteDetails";
    }


}
