package be.kdg.programming3.spaceMissions.presentation.controller;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.presentation.controller.httpSession.SessionHistory;
import be.kdg.programming3.spaceMissions.presentation.viewModel.MissionViewModel;
import be.kdg.programming3.spaceMissions.service.LaunchSiteService;
import be.kdg.programming3.spaceMissions.service.MissionService;
import be.kdg.programming3.spaceMissions.service.RocketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/missions")
public class MissionController {

    private static final Logger logger = LoggerFactory.getLogger(MissionController.class.getName());
    private final MissionService missionService;
    private final RocketService rocketService;
    private final LaunchSiteService launchSiteService;
    private final SessionHistory sessionHistory;

    public MissionController(MissionService missionService, RocketService rocketService, LaunchSiteService launchSiteService, SessionHistory sessionHistory) {
        this.missionService = missionService;
        this.rocketService = rocketService;
        this.launchSiteService = launchSiteService;
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
        model.addAttribute("launchSites", launchSiteService.getAllLaunchSites());
        model.addAttribute("rockets", rocketService.getAllRockets());
        return "add-mission";
    }

    @PostMapping("/add")
    public String processAddMission(@Valid @ModelAttribute("mission") MissionViewModel missionViewModel, BindingResult errors, Model model) throws IOException {
        logger.debug("Received data for a new mission: {}", missionViewModel.getMissionName());

        if (errors.hasErrors()) {
            logger.debug("Validation errors found: {}", errors.getAllErrors());
            // Log each error individually for clarity
            errors.getFieldErrors().forEach(error ->
                    logger.debug("Field error - Field: {}, Value: {}, Message: {}",
                            error.getField(), error.getRejectedValue(), error.getDefaultMessage())
            );
            model.addAttribute("missionTypes", MissionType.values());
            model.addAttribute("launchSites", launchSiteService.getAllLaunchSites());
            model.addAttribute("rockets", rocketService.getAllRockets());
            return "add-mission";
        }

        Mission mission = new Mission();
        mission.setMissionName(missionViewModel.getMissionName());
        mission.setMissionObjective(missionViewModel.getMissionObjective());
        mission.setLaunchDate(missionViewModel.getLaunchDate());
        mission.setCrewOnboard(java.util.Optional.ofNullable(missionViewModel.getCrewOnboard()));
        mission.setSuccess(missionViewModel.isSuccess());
        mission.setMissionType(missionViewModel.getMissionType());
        logger.debug("Setting launch site with ID: {}", missionViewModel.getLaunchSite());
        mission.setLaunchSite(launchSiteService.getLaunchSiteById(missionViewModel.getLaunchSite()));
        logger.debug("Setting rockets with IDs: {}", missionViewModel.getRockets());
        mission.setRockets(rocketService.getRocketsByIds(missionViewModel.getRockets()));


        if (!missionViewModel.getImageFile().isEmpty()) {
            String fileName = missionViewModel.getImageFile().getOriginalFilename();
            Path uploadPath = Paths.get("src/main/resources/static");
            Files.createDirectories(uploadPath);
            missionViewModel.getImageFile().transferTo(uploadPath.resolve(fileName));
            mission.setImageFileName(fileName);
            logger.debug("Image uploaded: {}", fileName);
        }

        logger.debug("About to save mission: {}", mission.getMissionName());
        missionService.addMission(mission);
        logger.debug("Mission saved successfully");

        return "redirect:/missions";
    }

    @PostMapping("/{id}/delete")
    public String deleteMission(@PathVariable int id) {
        missionService.deleteMission(id);
        return "redirect:/missions";
    }


}
