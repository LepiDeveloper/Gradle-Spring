package be.kdg.programming3.spaceMissions.domain;

import java.util.ArrayList;
import java.util.List;

public class LaunchSite {

    private int siteId;
    private String siteName;
    private String location;

    private List<Mission> missions = new ArrayList<Mission>();

    // no missions
    public LaunchSite(int siteId, String siteName, String location) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
    }

    // with missions
    public LaunchSite(int siteId, String siteName, String location, List<Mission> missions) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
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
