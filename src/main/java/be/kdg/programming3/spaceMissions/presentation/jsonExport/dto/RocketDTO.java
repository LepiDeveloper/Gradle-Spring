package be.kdg.programming3.spaceMissions.presentation.jsonExport.dto;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RocketDTO {

    @SerializedName("rocket_id")
    private int rocketId;

    @SerializedName("rocket_name")
    private String rocketName;

    @SerializedName("launch_capacity")
    private double launchCapacity;

    @SerializedName("manufacturer")
    private String manufacturer;

    @SerializedName("image_file_name")
    private String imageFileName;

    @SerializedName("missions")
    private List<SimpleMissionDTO> missions;

    public RocketDTO() {}

    public RocketDTO(Rocket rocket) {
        this.rocketId = rocket.getRocketId();
        this.rocketName = rocket.getRocketName();
        this.launchCapacity = rocket.getLaunchCapacity();
        this.manufacturer = rocket.getManufacturer();
        this.imageFileName = rocket.getImageFileName();

        if (rocket.getMissions() != null) {
            this.missions = rocket.getMissions().stream()
                    .map(SimpleMissionDTO::new)
                    .toList();
        }
    }

    // Constructor without missions to avoid circular references
    public RocketDTO(Rocket rocket, boolean includeMissions) {
        this.rocketId = rocket.getRocketId();
        this.rocketName = rocket.getRocketName();
        this.launchCapacity = rocket.getLaunchCapacity();
        this.manufacturer = rocket.getManufacturer();
        this.imageFileName = rocket.getImageFileName();

        if (includeMissions && rocket.getMissions() != null) {
            this.missions = rocket.getMissions().stream()
                    .map(SimpleMissionDTO::new)
                    .toList();
        }
    }

    // Getters and setters
    public int getRocketId() { return rocketId; }
    public void setRocketId(int rocketId) { this.rocketId = rocketId; }

    public String getRocketName() { return rocketName; }
    public void setRocketName(String rocketName) { this.rocketName = rocketName; }

    public double getLaunchCapacity() { return launchCapacity; }
    public void setLaunchCapacity(double launchCapacity) { this.launchCapacity = launchCapacity; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getImageFileName() { return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName = imageFileName; }

    public List<SimpleMissionDTO> getMissions() { return missions; }
    public void setMissions(List<SimpleMissionDTO> missions) { this.missions = missions; }

}
