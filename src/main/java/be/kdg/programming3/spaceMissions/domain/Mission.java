package be.kdg.programming3.spaceMissions.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Mission {

    private int missionId;
    private String missionName;
    private String missionObjective;
    private LocalDate launchDate;
    private Optional<Integer> crewOnboard;
    boolean isSuccess;

    private MissionType missionType;
    private LaunchSite launchSite;
    private List<Rocket> rockets = new ArrayList<>();

    // no rockets and launchSites
    public Mission(int missionId, String missionName, String missionObjective, LocalDate launchDate, MissionType missionType,
                   Optional<Integer> crewOnboard, boolean isSuccess) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionObjective = missionObjective;
        this.launchDate = launchDate;
        this.missionType = missionType;
        this.crewOnboard = crewOnboard;
        this.isSuccess = isSuccess;
    }

    // with rockets and launchSites
    public Mission(int missionId, String missionName, String missionObjective, LocalDate launchDate, MissionType missionType,
                   Optional<Integer> crewOnboard, boolean isSuccess, LaunchSite launchSite) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionObjective = missionObjective;
        this.launchDate = launchDate;
        this.missionType = missionType;
        this.crewOnboard = crewOnboard;
        this.isSuccess = isSuccess;
        this.launchSite = launchSite;
    }

    // Getters and Setters

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

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

    public MissionType getMissionType() {
        return missionType;
    }

    public void setMissionType(MissionType missionType) {
        this.missionType = missionType;
    }

    public Optional<Integer> getCrewOnboard() {
        return crewOnboard;
    }

    public void setCrewOnboard(Optional<Integer> crewOnboard) {
        this.crewOnboard = crewOnboard;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public LaunchSite getLaunchSite() {
        return launchSite;
    }

    public void setLaunchSite(LaunchSite launchSite) {
        this.launchSite = launchSite;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public void setRockets(List<Rocket> rockets) {
        this.rockets = rockets;
    }

    public void addRocket(Rocket rocket) {
        if (!rockets.contains(rocket)) {
            rockets.add(rocket);
            rocket.getMissions().add(this);
        }
    }

    @Override
    public String toString() {

        String rocketNames = rockets.stream()
                .map(Rocket::getRocketName)
                .collect(Collectors.joining(", "));

        return "The mission " + missionName + " under the ID of " + missionId +
                " was to do the following: " + missionObjective + ". Launched from " + launchSite.getSiteName() +
                " on " + launchDate + " with a crew of " + crewOnboard.orElse(0) +
                " as an " + missionType + " mission. Rockets used: " + rocketNames + ".";
    }

}
