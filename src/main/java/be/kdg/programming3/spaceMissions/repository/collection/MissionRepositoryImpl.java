package be.kdg.programming3.spaceMissions.repository.collection;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.repository.DataFactory;
import be.kdg.programming3.spaceMissions.repository.MissionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Profile("collection")
public class MissionRepositoryImpl implements MissionRepository {

    private static final Logger logger = LoggerFactory.getLogger(MissionRepositoryImpl.class);

    @Override
    public List<Mission> findAllMissions() {
        logger.info("Finding all missions");
        return DataFactory.missions;
    }

    @Override
    public Optional<Mission> findMissionById(int id) {
        logger.info("Finding mission by id: {}", id);
        return DataFactory.missions.stream()
                .filter(mission -> mission.getMissionId() == id)
                .findFirst();
    }

    @Override
    public Mission saveMission(Mission mission) {
        logger.info("Saving mission: {}", mission);
        if (!DataFactory.missions.contains(mission)) {
            DataFactory.missions.add(mission);
        }
        return mission;
    }

    @Override
    public Mission updateMission(Mission mission) {
        logger.info("Updating mission: {}", mission);
        return null;
    }

    @Override
    public void deleteMissionById(int id) {
        logger.info("Deleting mission by id: {}", id);
        DataFactory.missions.removeIf(mission -> mission.getMissionId() == id);
    }

    @Override
    public List<Mission> findMissionByMissionType(String missionType) {
        logger.info("Finding mission by type: {}", missionType);
        return DataFactory.missions.stream()
                .filter(mission -> mission.getMissionType().name().equalsIgnoreCase(missionType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Mission> findMissionByLaunchSiteName(String siteName) {
        logger.info("Finding mission by launch site name: {}", siteName);
        return DataFactory.missions.stream()
                .filter(mission -> mission.getLaunchSite().getSiteName().toLowerCase().contains(siteName.toLowerCase()))
                .collect(Collectors.toList());
    }
}



