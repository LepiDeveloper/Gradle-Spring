package be.kdg.programming3.spaceMissions.repository.springdatajpa;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("springdata")
public interface LaunchSiteSpringDataRepository extends JpaRepository<LaunchSite, Integer> {
}
