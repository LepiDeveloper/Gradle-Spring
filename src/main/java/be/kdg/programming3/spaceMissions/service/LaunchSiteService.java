package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;

import java.util.List;
import java.util.Optional;

public interface LaunchSiteService {

    List<LaunchSite> getAllLaunchSites();
    LaunchSite getLaunchSiteById(int id);
    LaunchSite addLaunchSite(LaunchSite launchSite);
    LaunchSite updateLaunchSite(LaunchSite launchSite);
    void deleteLaunchSite(int id);

}
