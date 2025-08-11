package be.kdg.programming3.spaceMissions.presentation.viewModel;

import be.kdg.programming3.spaceMissions.domain.MissionType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
}
