package be.kdg.programming3.spaceMissions.repository.springdatajpa;

import be.kdg.programming3.spaceMissions.domain.Mission;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("springdata")
public interface MissionSpringDataRepository extends JpaRepository<Mission, Integer> {

    // Method query #1
    List<Mission> findByMissionType(String missionType);

    // Method query #2
    List<Mission> findByLaunchSite_SiteNameContainingIgnoreCase(String siteName);

    // Custom query using @Query
    @Query("SELECT m FROM Mission m WHERE m.launchDate > :afterDate AND m.isSuccess = true")
    List<Mission> findSuccessfulMissionsAfter(LocalDate afterDate);

}
