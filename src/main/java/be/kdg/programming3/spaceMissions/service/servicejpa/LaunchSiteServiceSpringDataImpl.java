package be.kdg.programming3.spaceMissions.service.servicejpa;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.repository.springdatajpa.LaunchSiteSpringDataRepository;
import be.kdg.programming3.spaceMissions.service.LaunchSiteService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
public class LaunchSiteServiceSpringDataImpl implements LaunchSiteService {

    private final LaunchSiteSpringDataRepository launchSiteRepository;

    public LaunchSiteServiceSpringDataImpl(LaunchSiteSpringDataRepository launchSiteRepository) {
        this.launchSiteRepository = launchSiteRepository;
    }


    @Override
    public List<LaunchSite> getAllLaunchSites() {
        return launchSiteRepository.findAll();
    }

    @Override
    public LaunchSite getLaunchSiteById(int id) {
        return launchSiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Launch Site not found"));
    }

    @Override
    public LaunchSite addLaunchSite(LaunchSite launchSite) {
        return launchSiteRepository.save(launchSite);
    }

    @Override
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        return launchSiteRepository.save(launchSite);
    }

    @Override
    public void deleteLaunchSite(int id) {
        launchSiteRepository.deleteById(id);
    }
}
