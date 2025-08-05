package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LaunchSiteRepositoryImpl implements LaunchSiteRepository {

    @Override
    public List<LaunchSite> findAllLaunchSites() {
        return DataFactory.launchSites;
    }

    @Override
    public Optional<LaunchSite> findLaunchSiteById(int id) {
        return DataFactory.launchSites.stream()
                .filter(site -> site.getSiteId() == id)
                .findFirst();
    }

    @Override
    public LaunchSite saveLaunchSite(LaunchSite launchSite) {
        if (!DataFactory.launchSites.contains(launchSite)) {
            DataFactory.launchSites.add(launchSite);
        }
        return launchSite;
    }

    @Override
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        return null;
    }

    @Override
    public void deleteLaunchSiteById(int id) {
        DataFactory.launchSites.removeIf(site -> site.getSiteId() == id);
    }
}
