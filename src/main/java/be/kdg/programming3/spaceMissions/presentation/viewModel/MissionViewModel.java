package be.kdg.programming3.spaceMissions.presentation.viewModel;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class MissionViewModel {

    @NotBlank(message = "Mission name is required")
    @Size(min = 3, max = 100, message = "Mission name must be between 3 and 100 characters")
    private String missionName;

    @NotBlank(message = "Mission objective is required")
    private String missionObjective;

    @NotNull(message = "Launch date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate launchDate;

    @Min(value = 0, message = "Crew on board cannot be negative")
    private Integer crewOnboard;

    private boolean success;

    @NotNull(message = "Mission type is required")
    private MissionType missionType;

    private Integer launchSite;
    private List<Integer> rockets;
    private MultipartFile imageFile; // Uploaded file


    public MissionViewModel() {}

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionObjective() {
        return missionObjective;
    }

    public void setMissionObjective(String missionObjective) {
        this.missionObjective = missionObjective;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public Integer getCrewOnboard() {
        return crewOnboard;
    }

    public void setCrewOnboard(Integer crewOnboard) {
        this.crewOnboard = crewOnboard;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MissionType getMissionType() {
        return missionType;
    }

    public void setMissionType(MissionType missionType) {
        this.missionType = missionType;
    }

    public Integer getLaunchSite() {
        return launchSite;
    }

    public void setLaunchSite(Integer launchSite) {
        this.launchSite = launchSite;
    }

    public List<Integer> getRockets() {
        return rockets;
    }

    public void setRockets(List<Integer> rockets) {
        this.rockets = rockets;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
