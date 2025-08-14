package be.kdg.programming3.spaceMissions.repository.springdatajpa;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("springdata")
public interface RocketSpringDataRepository extends JpaRepository<Rocket, Integer> {

    // Method query example
    List<Rocket> findByLaunchCapacityGreaterThan(double capacity);

    // Method query for multiple IDs
    List<Rocket> findByRocketIdIn(List<Integer> ids);

}
