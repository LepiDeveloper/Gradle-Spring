package be.kdg.programming3.spaceMissions.service.serviceJson;

import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.LaunchSiteDTO;
import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.MissionDTO;
import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.RocketDTO;
import be.kdg.programming3.spaceMissions.service.LaunchSiteService;
import be.kdg.programming3.spaceMissions.service.MissionService;
import be.kdg.programming3.spaceMissions.service.RocketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    private final MissionService missionService;
    private final RocketService rocketService;
    private final LaunchSiteService launchSiteService;

    public ExportServiceImpl(MissionService missionService, RocketService rocketService, LaunchSiteService launchSiteService) {
        this.missionService = missionService;
        this.rocketService = rocketService;
        this.launchSiteService = launchSiteService;
    }

    @Override
    public List<MissionDTO> getAllMissionsForExport() {
        return missionService.getAllMissions().stream()
                .map(MissionDTO::new)
                .toList();
    }

    @Override
    public List<RocketDTO> getAllRocketsForExport() {
        return rocketService.getAllRockets().stream()
                .map(rocket -> new RocketDTO(rocket, true)) // Include missions
                .toList();
    }

    @Override
    public List<LaunchSiteDTO> getAllLaunchSitesForExport() {
        return launchSiteService.getAllLaunchSites().stream()
                .map(LaunchSiteDTO::new)
                .toList();
    }

}
