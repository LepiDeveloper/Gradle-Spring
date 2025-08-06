package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private static final Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class);

    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<Mission> getAllMissions() {
        logger.debug("Fetching all missions");
        return missionRepository.findAllMissions();
    }

    @Override
    public Optional<Mission> getMissionById(int id) {
        logger.debug("Fetching mission with id {}", id);
        return missionRepository.findMissionById(id);
    }

    @Override
    public Mission addMission(Mission mission) {
        logger.debug("Adding mission {}", mission);
        return missionRepository.saveMission(mission);
    }

    @Override
    public void updateMission(Mission mission) {
        logger.debug("Updating mission {}", mission);

    }

    @Override
    public void deleteMission(int id) {
        logger.debug("Deleting mission with id {}", id);
        missionRepository.deleteMissionById(id);
    }

    @Override
    public List<Mission> getMissionsByType(String missionType) {
        logger.debug("Fetching missions by type {}", missionType);
        return missionRepository.findMissionByMissionType(missionType);
    }

    @Override
    public List<Mission> getMissionsByLaunchSite(String siteName) {
        logger.debug("Fetching missions by launch site {}", siteName);
        return missionRepository.findMissionByLaunchSiteName(siteName);
    }

    @Override
    public List<Mission> getFilteredMissions(String missionType, String siteName) {
        logger.debug("Fetching missions by type {} and site {}", missionType, siteName);
        return getAllMissions().stream()
                .filter(m -> missionType.isEmpty() || m.getMissionType().name().equalsIgnoreCase(missionType))
                .filter(m -> siteName.isEmpty() || m.getLaunchSite().getSiteName().toLowerCase().contains(siteName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
