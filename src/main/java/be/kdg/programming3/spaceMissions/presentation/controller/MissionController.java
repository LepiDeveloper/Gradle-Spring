package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.presentation.controller.httpSession.SessionHistory;
import be.kdg.programming3.spaceMissions.presentation.viewModel.MissionViewModel;
import be.kdg.programming3.spaceMissions.service.MissionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/missions")
public class MissionController {

    private static final Logger logger = LoggerFactory.getLogger(MissionController.class.getName());
    private final MissionService missionService;
    private final SessionHistory sessionHistory;

    public MissionController(MissionService missionService, SessionHistory sessionHistory) {
        this.missionService = missionService;
        this.sessionHistory = sessionHistory;
    }

    @GetMapping
    public String showMissions(Model model){
        logger.debug("Getting missions view: ");
        sessionHistory.addPageVisit("Missions List");
        model.addAttribute("missions", missionService.getAllMissions());
        return "missions";
    }

    @GetMapping("/{id}")
    public String getMissionDetails(@PathVariable int id, Model model) {
        logger.debug("Getting missions details: ");
        sessionHistory.addPageVisit("Mission Details: " + id);
        model.addAttribute("mission", missionService.getMissionById(id));
        return "missionDetails";
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
        sessionHistory.addPageVisit("Add Mission");
        model.addAttribute("mission", new MissionViewModel());
        model.addAttribute("missionTypes", MissionType.values());
        return "add-mission";
    }

    @PostMapping("/add")
    public String processAddMission(@Valid @ModelAttribute("mission") MissionViewModel missionViewModel, BindingResult errors, Model model) {
        logger.debug("Received data for a new mission: {}", missionViewModel.getMissionName());

        if (errors.hasErrors()) {
            model.addAttribute("missionTypes", MissionType.values());
            return "add-mission";
        }

        Mission mission = new Mission();
        mission.setMissionName(missionViewModel.getMissionName());
        mission.setMissionObjective(missionViewModel.getMissionObjective());
        mission.setLaunchDate(missionViewModel.getLaunchDate());
        mission.setCrewOnboard(java.util.Optional.ofNullable(missionViewModel.getCrewOnboard()));
        mission.setSuccess(missionViewModel.isSuccess());
        mission.setMissionType(missionViewModel.getMissionType());

        missionService.addMission(mission);
        return "redirect:/missions";
    }


}
