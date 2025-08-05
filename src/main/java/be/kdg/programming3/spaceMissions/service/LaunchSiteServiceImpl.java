package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.repository.LaunchSiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaunchSiteServiceImpl implements LaunchSiteService {

    private final LaunchSiteRepository launchSiteRepository;

    public LaunchSiteServiceImpl(LaunchSiteRepository launchSiteRepository) {
        this.launchSiteRepository = launchSiteRepository;
    }

    @Override
    public List<LaunchSite> getAllLaunchSites() {
        return launchSiteRepository.findAllLaunchSites();
    }

    @Override
    public Optional<LaunchSite> getLaunchSiteById(int id) {
        return launchSiteRepository.findLaunchSiteById(id);
    }

    @Override
    public LaunchSite addLaunchSite(LaunchSite launchSite) {
        return launchSiteRepository.saveLaunchSite(launchSite);
    }

    @Override
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        return null;
    }

    @Override
    public void deleteLaunchSite(int id) {
        launchSiteRepository.deleteLaunchSiteById(id);
    }
}
