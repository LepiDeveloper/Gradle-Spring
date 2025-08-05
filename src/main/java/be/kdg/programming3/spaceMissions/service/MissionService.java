package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionService {

    List<Mission> getAllMissions();
    Optional<Mission> getMissionById(int id);
    Mission addMission(Mission mission);
    void updateMission(Mission mission);
    void deleteMission(int id);
    List<Mission> getMissionsByType(String missionType);
    List<Mission> getMissionsByLaunchSite(String siteName);
    List<Mission> getFilteredMissions(String missionType, String siteName);

}
