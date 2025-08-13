package be.kdg.programming3.spaceMissions.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int missionId;

    @Column(nullable = false, length = 100)
    private String missionName;

    @Column(nullable = false)
    private String missionObjective;

    @Column(nullable = false)
    private LocalDate launchDate;

    private Integer crewOnboard; ;

    @Column(nullable = false)
    boolean isSuccess;

    private String imageFileName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionType missionType;

    @ManyToOne
    @JoinColumn(name = "launch_site_id", nullable = false)
    private LaunchSite launchSite;

    @ManyToMany
    @JoinTable(
            name = "mission_rocket",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "rocket_id")
    )
    private List<Rocket> rockets = new ArrayList<>();

    // no rockets and launchSites
    public Mission(int missionId, String missionName, String missionObjective, LocalDate launchDate, MissionType missionType,
                   Integer  crewOnboard, boolean isSuccess, String imageFileName) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionObjective = missionObjective;
        this.launchDate = launchDate;
        this.missionType = missionType;
        this.crewOnboard = crewOnboard;
        this.isSuccess = isSuccess;
        this.imageFileName = imageFileName;
    }

    // with rockets and launchSites
    public Mission(int missionId, String missionName, String missionObjective, LocalDate launchDate, MissionType missionType,
                   Integer  crewOnboard, boolean isSuccess, String imageFileName, LaunchSite launchSite) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionObjective = missionObjective;
        this.launchDate = launchDate;
        this.missionType = missionType;
        this.crewOnboard = crewOnboard;
        this.isSuccess = isSuccess;
        this.imageFileName = imageFileName;
        this.launchSite = launchSite;
    }

    // No-arg constructor
    public Mission() {

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
        return Optional.ofNullable(crewOnboard);
    }

    public void setCrewOnboard(Optional<Integer> crewOnboard) {
        this.crewOnboard = crewOnboard.orElse(null);
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
                " on " + launchDate + " with a crew of " + crewOnboard +
                " as an " + missionType + " mission. Rockets used: " + rocketNames + ".";
    }

}
