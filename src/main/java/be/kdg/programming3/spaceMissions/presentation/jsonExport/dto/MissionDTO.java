package be.kdg.programming3.spaceMissions.presentation.jsonExport.dto;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

public class MissionDTO {

    @SerializedName("mission_id")
    private int missionId;

    @SerializedName("mission_name")
    private String missionName;

    @SerializedName("mission_objective")
    private String missionObjective;

    @SerializedName("launch_date")
    private LocalDate launchDate;

    @SerializedName("crew_onboard")
    private Integer crewOnboard;

    @SerializedName("is_success")
    private boolean isSuccess;

    @SerializedName("mission_type")
    private MissionType missionType;

    @SerializedName("image_file_name")
    private String imageFileName;

    @SerializedName("launch_site")
    private LaunchSiteDTO launchSite;

    @SerializedName("rockets")
    private List<RocketDTO> rockets;

    public MissionDTO() {}

    public MissionDTO(Mission mission) {
        this.missionId = mission.getMissionId();
        this.missionName = mission.getMissionName();
        this.missionObjective = mission.getMissionObjective();
        this.launchDate = mission.getLaunchDate();
        this.crewOnboard = mission.getCrewOnboard().orElse(null);
        this.isSuccess = mission.isSuccess();
        this.missionType = mission.getMissionType();
        this.imageFileName = mission.getImageFileName();

        if (mission.getLaunchSite() != null) {
            this.launchSite = new LaunchSiteDTO(mission.getLaunchSite());
        }

        if (mission.getRockets() != null) {
            this.rockets = mission.getRockets().stream()
                    .map(RocketDTO::new)
                    .toList();
        }
    }

    // Getters and setters
    public int getMissionId() { return missionId; }
    public void setMissionId(int missionId) { this.missionId = missionId; }

    public String getMissionName() { return missionName; }
    public void setMissionName(String missionName) { this.missionName = missionName; }

    public String getMissionObjective() { return missionObjective; }
    public void setMissionObjective(String missionObjective) { this.missionObjective = missionObjective; }

    public LocalDate getLaunchDate() { return launchDate; }
    public void setLaunchDate(LocalDate launchDate) { this.launchDate = launchDate; }

    public Integer getCrewOnboard() { return crewOnboard; }
    public void setCrewOnboard(Integer crewOnboard) { this.crewOnboard = crewOnboard; }

    public boolean isSuccess() { return isSuccess; }
    public void setSuccess(boolean success) { isSuccess = success; }

    public MissionType getMissionType() { return missionType; }
    public void setMissionType(MissionType missionType) { this.missionType = missionType; }

    public String getImageFileName() { return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName = imageFileName; }

    public LaunchSiteDTO getLaunchSite() { return launchSite; }
    public void setLaunchSite(LaunchSiteDTO launchSite) { this.launchSite = launchSite; }

    public List<RocketDTO> getRockets() { return rockets; }
    public void setRockets(List<RocketDTO> rockets) { this.rockets = rockets; }

}
