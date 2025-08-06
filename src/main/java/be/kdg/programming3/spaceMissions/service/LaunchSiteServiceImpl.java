package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.repository.LaunchSiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LaunchSiteServiceImpl implements LaunchSiteService {

    private final LaunchSiteRepository launchSiteRepository;
    private static final Logger logger = LoggerFactory.getLogger(LaunchSiteServiceImpl.class);


    public LaunchSiteServiceImpl(LaunchSiteRepository launchSiteRepository) {
        this.launchSiteRepository = launchSiteRepository;
    }

    @Override
    public List<LaunchSite> getAllLaunchSites() {
        logger.debug("Fetching all launch sites");
        return launchSiteRepository.findAllLaunchSites();
    }

    @Override
    public Optional<LaunchSite> getLaunchSiteById(int id) {
        logger.debug("Fetching all launch site by ID {}", id);
        return launchSiteRepository.findLaunchSiteById(id);
    }

    @Override
    public LaunchSite addLaunchSite(LaunchSite launchSite) {
        logger.debug("Adding a launch site");
        return launchSiteRepository.saveLaunchSite(launchSite);
    }

    @Override
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        logger.debug("Updating a launch site");
        return null;
    }

    @Override
    public void deleteLaunchSite(int id) {
        logger.debug("Deleting a launch site by ID {}", id);
        launchSiteRepository.deleteLaunchSiteById(id);
    }
}
