package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAllMissions();
    }

    @Override
    public Optional<Mission> getMissionById(int id) {
        return missionRepository.findMissionById(id);
    }

    @Override
    public Mission addMission(Mission mission) {
        return missionRepository.saveMission(mission);
    }

    @Override
    public void updateMission(Mission mission) {

    }

    @Override
    public void deleteMission(int id) {
        missionRepository.deleteMissionById(id);
    }

    @Override
    public List<Mission> getMissionsByType(String missionType) {
        return missionRepository.findMissionByMissionType(missionType);
    }

    @Override
    public List<Mission> getMissionsByLaunchSite(String siteName) {
        return missionRepository.findMissionByLaunchSiteName(siteName);
    }

    @Override
    public List<Mission> getFilteredMissions(String missionType, String siteName) {
        return getAllMissions().stream()
                .filter(m -> missionType.isEmpty() || m.getMissionType().name().equalsIgnoreCase(missionType))
                .filter(m -> siteName.isEmpty() || m.getLaunchSite().getSiteName().toLowerCase().contains(siteName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
