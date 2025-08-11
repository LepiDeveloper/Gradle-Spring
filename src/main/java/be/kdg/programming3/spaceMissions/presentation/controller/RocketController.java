package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.presentation.controller.httpSession.SessionHistory;
import be.kdg.programming3.spaceMissions.presentation.viewModel.RocketViewModel;
import be.kdg.programming3.spaceMissions.service.RocketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/rockets")
public class RocketController {

    private static final Logger logger = LoggerFactory.getLogger(RocketController.class.getName());
    private final RocketService rocketService;
    private final SessionHistory sessionHistory;

    public RocketController(RocketService rocketService, SessionHistory sessionHistory) {
        this.rocketService = rocketService;
        this.sessionHistory = sessionHistory;
    }


    @GetMapping
    public String showRockets(Model model){
        logger.debug("Getting rockets view: ");
        sessionHistory.addPageVisit("Rockets List");
        model.addAttribute("rockets", rocketService.getAllRockets());
        return "rockets";
    }

    @GetMapping("/{id}")
    public String getRocketDetails(@PathVariable int id, Model model) {
        sessionHistory.addPageVisit("Rocket Details: " + id);
        model.addAttribute("rocket", rocketService.getRocketById(id));
        return "rocketDetails"; // This should correspond to your Thymeleaf template
    }

    @GetMapping("/filter")
    public String filterRockets(@RequestParam double minCapacity, Model model) {
        logger.debug("Filtering rockets with capacity over {}", minCapacity);
        model.addAttribute("rockets", rocketService.getRocketsByMinCapacity(minCapacity));
        return "rockets";
    }


    @GetMapping("/add")
    public String addRocket(Model model) {
        logger.debug("Displaying add mission form");
        sessionHistory.addPageVisit("Add Rockets");
        model.addAttribute("rocket", new RocketViewModel());
        return "add-rocket";
    }

    @PostMapping("/add")
    public String processAddRocket(@Valid @ModelAttribute("rocket") RocketViewModel rocketViewModel, BindingResult errors, Model model) {
        logger.debug("Received data for a new rocket {}", rocketViewModel.getRocketName());

        if (errors.hasErrors()) {
            return "add-rocket";
        }

        Rocket rocket = new Rocket();
        rocket.setRocketName(rocket.getRocketName());
        rocket.setLaunchCapacity(rocketViewModel.getLaunchCapacity());
        rocket.setManufacturer(rocketViewModel.getManufacturer());

        rocketService.addRocket(rocket);
        return "redirect:/rockets";
    }



}
