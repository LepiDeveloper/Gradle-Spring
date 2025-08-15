package be.kdg.programming3.spaceMissions.presentation.jsonExport.dto;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import com.google.gson.annotations.SerializedName;

public class LaunchSiteDTO {

    @SerializedName("site_id")
    private int siteId;

    @SerializedName("site_name")
    private String siteName;

    @SerializedName("location")
    private String location;

    @SerializedName("image_file_name")
    private String imageFileName;

    public LaunchSiteDTO() {}

    public LaunchSiteDTO(LaunchSite launchSite) {
        this.siteId = launchSite.getSiteId();
        this.siteName = launchSite.getSiteName();
        this.location = launchSite.getLocation();
        this.imageFileName = launchSite.getImageFileName();
    }

    // Getters and setters
    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getImageFileName() { return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName = imageFileName; }

}
