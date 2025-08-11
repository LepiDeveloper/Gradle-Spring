package be.kdg.programming3.spaceMissions.presentation.viewModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RocketViewModel {

    @NotBlank(message = "Rocket name is required")
    @Size(min = 3, max = 100, message = "Rocket name must be between 3 and 100 characters")
    private String rocketName;

    @Min(value = 0, message = "Launch Capacity cannot be negative")
    private double launchCapacity;

    @NotBlank(message = "Rocket manufacturer is required")
    private String manufacturer;

    public RocketViewModel() {}

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
}
