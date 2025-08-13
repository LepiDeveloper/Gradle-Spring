package be.kdg.programming3.spaceMissions.repository.jpa;


import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.RocketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpa")
public class RocketRepositoryJPA implements RocketRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Rocket> findAllRockets() {
        return em.createQuery("SELECT r FROM Rocket r", Rocket.class).getResultList();
    }

    @Override
    public Optional<Rocket> findRocketById(int id) {
        return Optional.ofNullable(em.find(Rocket.class, id));
    }

    @Override
    @Transactional
    public Rocket saveRocket(Rocket rocket) {
        em.persist(rocket);
        return rocket;
    }

    @Override
    @Transactional
    public Rocket updateRocket(Rocket rocket) {
        return em.merge(rocket);
    }

    @Override
    @Transactional
    public void deleteRocketById(int id) {
        findRocketById(id).ifPresent(em::remove);
    }

    @Override
    public List<Rocket> findRocketsByLaunchCapacityGreaterThan(double capacity) {
        return em.createQuery(
                        "SELECT r FROM Rocket r WHERE r.launchCapacity > :cap",
                        Rocket.class)
                .setParameter("cap", capacity)
                .getResultList();
    }

    @Override
    public List<Rocket> findRocketsByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return List.of();
        return em.createQuery(
                        "SELECT r FROM Rocket r WHERE r.rocketId IN :ids",
                        Rocket.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
