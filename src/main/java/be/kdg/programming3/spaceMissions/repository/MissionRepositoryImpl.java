package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.Mission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MissionRepositoryImpl implements MissionRepository {
    @Override
    public List<Mission> findAllMissions() {
        return DataFactory.missions;
    }

    @Override
    public Optional<Mission> findMissionById(int id) {
        return DataFactory.missions.stream()
                .filter(mission -> mission.getMissionId() == id)
                .findFirst();
    }

    @Override
    public Mission saveMission(Mission mission) {
        if (!DataFactory.missions.contains(mission)) {
            DataFactory.missions.add(mission);
        }
        return mission;
    }

    @Override
    public Mission updateMission(Mission mission) {
        return null;
    }

    @Override
    public void deleteMissionById(int id) {
        DataFactory.missions.removeIf(mission -> mission.getMissionId() == id);
    }

    @Override
    public List<Mission> findMissionByMissionType(String missionType) {
        return DataFactory.missions.stream()
                .filter(mission -> mission.getMissionType().name().equalsIgnoreCase(missionType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Mission> findMissionByLaunchSiteName(String siteName) {
        return DataFactory.missions.stream()
                .filter(mission -> mission.getLaunchSite().getSiteName().toLowerCase().contains(siteName.toLowerCase()))
                .collect(Collectors.toList());
    }
}



