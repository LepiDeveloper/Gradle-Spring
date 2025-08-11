package be.kdg.programming3.spaceMissions.domain;

import java.util.ArrayList;
import java.util.List;

public class Rocket {

    private int rocketId;
    private String rocketName;
    private double launchCapacity;
    private String manufacturer;
    private String imageFileName;

    private List<Mission> missions = new ArrayList<>();

    // no missions
    public Rocket(int rocketId, String rocketName, double launchCapacity, String manufacturer, String imageFileName) {
        this.rocketId = rocketId;
        this.rocketName = rocketName;
        this.launchCapacity = launchCapacity;
        this.manufacturer = manufacturer;
        this.imageFileName = imageFileName;
    }

    // with missions
    public Rocket(int rocketId, String rocketName, double launchCapacity, String manufacturer, String imageFileName, List<Mission> missions) {
        this.rocketId = rocketId;
        this.rocketName = rocketName;
        this.launchCapacity = launchCapacity;
        this.manufacturer = manufacturer;
        this.imageFileName = imageFileName;
        this.missions = missions;
    }

    // No-arg constructor
    public Rocket() {}


    // Getters and Setters


    public int getRocketId() {
        return rocketId;
    }

    public void setRocketId(int rocketId) {
        this.rocketId = rocketId;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public double getLaunchCapacity() {
        return launchCapacity;
    }

    public void setLaunchCapacity(double launchCapacity) {
        this.launchCapacity = launchCapacity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
            mission.getRockets().add(this);
        }
    }

    @Override
    public String toString() {
        return rocketName + " (" + rocketId + ") is build and operated by " + manufacturer +
                ". The rocket has a capacity of about " + launchCapacity + "kg and has done " + missions.size() + " mission so far.";
    }



}
