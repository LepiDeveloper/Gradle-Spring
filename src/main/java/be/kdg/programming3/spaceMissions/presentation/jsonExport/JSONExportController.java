package be.kdg.programming3.spaceMissions.presentation.jsonExport;

import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.LaunchSiteDTO;
import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.MissionDTO;
import be.kdg.programming3.spaceMissions.presentation.jsonExport.dto.RocketDTO;
import be.kdg.programming3.spaceMissions.service.serviceJson.ExportService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/export")
public class JSONExportController {

    private static final Logger logger = LoggerFactory.getLogger(JSONExportController.class);

    private final ExportService exportService;
    private final Gson gson;

    public JSONExportController(ExportService exportService, Gson gson) {
        this.exportService = exportService;
        this.gson = gson;
    }

    @GetMapping("/missions")
    public ResponseEntity<String> exportMissions() {
        logger.info("Exporting missions to JSON");

        try {
            List<MissionDTO> missions = exportService.getAllMissionsForExport();
            String jsonString = gson.toJson(missions);

            String filename = "missions_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".json";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", filename);

            logger.info("Successfully exported {} missions", missions.size());
            return new ResponseEntity<>(jsonString, headers, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error exporting missions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error exporting missions: " + e.getMessage());
        }
    }

    @GetMapping("/rockets")
    public ResponseEntity<String> exportRockets() {
        logger.info("Exporting rockets to JSON");

        try {
            List<RocketDTO> rockets = exportService.getAllRocketsForExport();
            String jsonString = gson.toJson(rockets);

            String filename = "rockets_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".json";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", filename);

            logger.info("Successfully exported {} rockets with their missions", rockets.size());
            return new ResponseEntity<>(jsonString, headers, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error exporting rockets: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error exporting rockets: " + e.getMessage());
        }
    }

    @GetMapping("/launchsites")
    public ResponseEntity<String> exportLaunchSites() {
        logger.info("Exporting launch sites to JSON");

        try {
            List<LaunchSiteDTO> launchSites = exportService.getAllLaunchSitesForExport();
            String jsonString = gson.toJson(launchSites);

            String filename = "launchsites_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".json";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", filename);

            logger.info("Successfully exported {} launch sites", launchSites.size());
            return new ResponseEntity<>(jsonString, headers, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error exporting launch sites: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error exporting launch sites: " + e.getMessage());
        }
    }


}
