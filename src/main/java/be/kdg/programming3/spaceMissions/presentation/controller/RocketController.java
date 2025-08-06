package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.service.RocketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/rockets")
public class RocketController {

    private static final Logger logger = LoggerFactory.getLogger(RocketController.class.getName());
    private final RocketService rocketService;

    public RocketController(RocketService rocketService) {
        this.rocketService = rocketService;
    }


    @GetMapping
    public String showRockets(Model model){
        logger.debug("Getting rockets view: ");
        model.addAttribute("rockets", rocketService.getAllRockets());
        return "rockets";
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
        model.addAttribute("rocket", new Rocket());
        return "add-rocket";
    }

    @PostMapping("/add")
    public String processAddRocket(@ModelAttribute Rocket rocket) {
        logger.debug("Received data for a new rocket {}", rocket.getRocketName());
        rocketService.addRocket(rocket);
        return "redirect:/rockets";
    }



}
