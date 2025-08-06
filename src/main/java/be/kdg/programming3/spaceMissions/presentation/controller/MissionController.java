package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.service.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/missions")
public class MissionController {

    private static final Logger logger = LoggerFactory.getLogger(MissionController.class.getName());
    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public String showMissions(Model model){
        logger.debug("Getting missions view: ");
        model.addAttribute("missions", missionService.getAllMissions());
        return "missions";
    }

    @GetMapping("/filter")
    public String filterMissions(@RequestParam(required = false, defaultValue = "") String missionType,
                                 @RequestParam(required = false, defaultValue = "") String siteName,
                                 Model model) {
        logger.debug("Displaying filtered missions");
        model.addAttribute("missions", missionService.getFilteredMissions(missionType, siteName));
        return "missions";
    }


    @GetMapping("/add")
    public String addMission(Model model) {
        logger.debug("Displaying add mission form");
        model.addAttribute("mission", new Mission()); // empty Mission object for form binding
        model.addAttribute("missionTypes", MissionType.values()); // for dropdown selection
        return "add-mission";
    }

    @PostMapping("/add")
    public String processAddMission(@ModelAttribute Mission mission) {
        logger.debug("Received data for a new mission: {}", mission.getMissionName());
        missionService.addMission(mission);
        return "redirect:/missions";
    }





}
