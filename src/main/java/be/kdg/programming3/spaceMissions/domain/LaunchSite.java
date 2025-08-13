package be.kdg.programming3.spaceMissions.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "launch_sites")
public class LaunchSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int siteId;

    @Column(nullable = false)
    private String siteName;

    @Column(nullable = false)
    private String location;

    private String imageFileName;

    @OneToMany(mappedBy = "launchSite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missions = new ArrayList<Mission>();

    // no missions
    public LaunchSite(int siteId, String siteName, String location, String imageFileName) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
        this.imageFileName = imageFileName;
    }

    // with missions
    public LaunchSite(int siteId, String siteName, String location, String imageFileName, List<Mission> missions) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
        this.imageFileName = imageFileName;
        this.missions = missions;
    }

    // No-arg constructor
    public LaunchSite() {

    }

    // Getters and Setters


    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void addMission(Mission mission) {
        if (!missions.contains(mission)) {
            missions.add(mission);
        }
    }

    @Override
    public String toString() {
        return  siteName + "(" + siteId + ") which is located in " + location + " and has operated "
                + missions.size() + " missions";
    }



}
