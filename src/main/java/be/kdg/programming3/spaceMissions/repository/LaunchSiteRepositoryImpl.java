package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class LaunchSiteRepositoryImpl implements LaunchSiteRepository {

    private static final Logger logger = LoggerFactory.getLogger(LaunchSiteRepositoryImpl.class);

    @Override
    public List<LaunchSite> findAllLaunchSites() {
        logger.info("Find all launch sites");
        return DataFactory.launchSites;
    }

    @Override
    public Optional<LaunchSite> findLaunchSiteById(int id) {
        logger.info("Find launch site by id: {}", id);
        return DataFactory.launchSites.stream()
                .filter(site -> site.getSiteId() == id)
                .findFirst();
    }

    @Override
    public LaunchSite saveLaunchSite(LaunchSite launchSite) {
        logger.info("Save launch site: {}", launchSite);
        if (!DataFactory.launchSites.contains(launchSite)) {
            DataFactory.launchSites.add(launchSite);
        }
        return launchSite;
    }

    @Override
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        logger.info("Update launch site: {}", launchSite);
        return null;
    }

    @Override
    public void deleteLaunchSiteById(int id) {
        logger.info("Delete launch site by id: {}", id);
        DataFactory.launchSites.removeIf(site -> site.getSiteId() == id);
    }
}
