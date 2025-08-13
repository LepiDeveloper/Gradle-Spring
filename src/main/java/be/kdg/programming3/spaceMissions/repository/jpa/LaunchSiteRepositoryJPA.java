package be.kdg.programming3.spaceMissions.repository.jpa;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.repository.LaunchSiteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpa")
public class LaunchSiteRepositoryJPA implements LaunchSiteRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<LaunchSite> findAllLaunchSites() {
        return em.createQuery("SELECT ls FROM LaunchSite ls", LaunchSite.class).getResultList();
    }

    @Override
    public Optional<LaunchSite> findLaunchSiteById(int id) {
        return Optional.ofNullable(em.find(LaunchSite.class, id));
    }

    @Override
    @Transactional
    public LaunchSite saveLaunchSite(LaunchSite launchSite) {
        em.persist(launchSite);
        return launchSite;
    }

    @Override
    @Transactional
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        return em.merge(launchSite);
    }

    @Override
    @Transactional
    public void deleteLaunchSiteById(int id) {
        findLaunchSiteById(id).ifPresent(em::remove);
    }

}
