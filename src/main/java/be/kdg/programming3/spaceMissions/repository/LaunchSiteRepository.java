package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;

import java.util.List;
import java.util.Optional;

public interface LaunchSiteRepository {

    List<LaunchSite> findAllLaunchSites();
    Optional<LaunchSite> findLaunchSiteById(int id);
    LaunchSite saveLaunchSite(LaunchSite launchSite);
    LaunchSite updateLaunchSite(LaunchSite launchSite);
    void deleteLaunchSiteById(int id);

}
