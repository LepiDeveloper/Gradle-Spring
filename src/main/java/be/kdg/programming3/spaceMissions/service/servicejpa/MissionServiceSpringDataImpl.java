package be.kdg.programming3.spaceMissions.service.servicejpa;

import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.exceptions.MissionNotFoundException;
import be.kdg.programming3.spaceMissions.repository.springdatajpa.MissionSpringDataRepository;
import be.kdg.programming3.spaceMissions.service.MissionService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Profile("springdata")
public class MissionServiceSpringDataImpl implements MissionService {

    private final MissionSpringDataRepository missionRepository;

    public MissionServiceSpringDataImpl(MissionSpringDataRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Override
    public Mission getMissionById(int id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new MissionNotFoundException("Mission with ID " + id + " not found"));
    }

    @Override
    public Mission addMission(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public void updateMission(Mission mission) {
        missionRepository.save(mission);
    }

    @Override
    public void deleteMission(int id) {
        missionRepository.deleteById(id);
    }

    @Override
    public List<Mission> getMissionsByType(String missionType) {
        return missionRepository.findByMissionType(missionType);
    }

    @Override
    public List<Mission> getMissionsByLaunchSite(String siteName) {
        return missionRepository.findByLaunchSite_SiteNameContainingIgnoreCase(siteName);
    }

    @Override
    public List<Mission> getFilteredMissions(String missionType, String siteName) {
        return missionRepository.findAll().stream()
                .filter(m -> missionType.isEmpty() || m.getMissionType().name().equalsIgnoreCase(missionType))
                .filter(m -> siteName.isEmpty() || m.getLaunchSite().getSiteName().toLowerCase().contains(siteName.toLowerCase()))
                .toList();
    }

    public List<Mission> getSuccessfulMissionsAfter(LocalDate date) {
        return missionRepository.findSuccessfulMissionsAfter(date);
    }



}
