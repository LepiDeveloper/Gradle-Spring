package be.kdg.programming3.spaceMissions.repository.jpa;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.repository.MissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpa")
public class MissionRepositoryJPA implements MissionRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Mission> findAllMissions() {
        return em.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
    }

    @Override
    public Optional<Mission> findMissionById(int id) {
        return Optional.ofNullable(em.find(Mission.class, id));
    }

    @Override
    @Transactional
    public Mission saveMission(Mission mission) {
        em.persist(mission);
        return mission;
    }

    @Override
    @Transactional
    public Mission updateMission(Mission mission) {
        return em.merge(mission);
    }

    @Override
    @Transactional
    public void deleteMissionById(int id) {
        Mission mission = em.find(Mission.class, id);
        if (mission != null) {
            em.remove(mission);
        }
    }

    @Override
    public List<Mission> findMissionByMissionType(String missionType) {
        return em.createQuery("SELECT m FROM Mission m WHERE LOWER(m.missionType) = LOWER(:type)", Mission.class)
                .setParameter("type", missionType)
                .getResultList();
    }

    @Override
    public List<Mission> findMissionByLaunchSiteName(String siteName) {
        return em.createQuery("SELECT m FROM Mission m WHERE LOWER(m.launchSite.siteName) LIKE LOWER(:name)", Mission.class)
                .setParameter("name", "%" + siteName + "%")
                .getResultList();
    }
}
