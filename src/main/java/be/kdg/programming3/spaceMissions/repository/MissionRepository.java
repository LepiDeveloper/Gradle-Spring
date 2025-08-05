package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionRepository {

    List<Mission> findAllMissions();
    Optional<Mission> findMissionById(int id);
    Mission saveMission(Mission mission);
    Mission updateMission(Mission mission);
    void deleteMissionById(int id);
    List<Mission> findMissionByMissionType(String missionType);
    List<Mission> findMissionByLaunchSiteName(String siteName);

}
