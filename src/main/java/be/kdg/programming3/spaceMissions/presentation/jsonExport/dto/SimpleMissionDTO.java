package be.kdg.programming3.spaceMissions.presentation.jsonExport.dto;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class SimpleMissionDTO {

    @SerializedName("mission_id")
    private int missionId;

    @SerializedName("mission_name")
    private String missionName;

    @SerializedName("launch_date")
    private LocalDate launchDate;

    @SerializedName("mission_type")
    private MissionType missionType;

    @SerializedName("is_success")
    private boolean isSuccess;

    public SimpleMissionDTO() {}

    public SimpleMissionDTO(Mission mission) {
        this.missionId = mission.getMissionId();
        this.missionName = mission.getMissionName();
        this.launchDate = mission.getLaunchDate();
        this.missionType = mission.getMissionType();
        this.isSuccess = mission.isSuccess();
    }

    // Getters and setters
    public int getMissionId() { return missionId; }
    public void setMissionId(int missionId) { this.missionId = missionId; }

    public String getMissionName() { return missionName; }
    public void setMissionName(String missionName) { this.missionName = missionName; }

    public LocalDate getLaunchDate() { return launchDate; }
    public void setLaunchDate(LocalDate launchDate) { this.launchDate = launchDate; }

    public MissionType getMissionType() { return missionType; }
    public void setMissionType(MissionType missionType) { this.missionType = missionType; }

    public boolean isSuccess() { return isSuccess; }
    public void setSuccess(boolean success) { isSuccess = success; }

}
