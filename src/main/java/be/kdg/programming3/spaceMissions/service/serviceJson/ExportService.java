package be.kdg.programming3.spaceMissions.service.serviceJson;

import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.LaunchSiteDTO;
import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.MissionDTO;
import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.RocketDTO;

import java.util.List;

public interface ExportService {

    List<MissionDTO> getAllMissionsForExport();
    List<RocketDTO> getAllRocketsForExport();
    List<LaunchSiteDTO> getAllLaunchSitesForExport();

}
