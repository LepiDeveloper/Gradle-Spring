package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.Rocket;

import java.util.List;
import java.util.Optional;

public interface RocketRepository {

    List<Rocket> findAllRockets();
    Optional<Rocket> findRocketById(int id);
    Rocket saveRocket(Rocket rocket);
    Rocket updateRocket(Rocket rocket);
    void deleteRocketById(int id);
    List<Rocket> findRocketsByLaunchCapacityGreaterThan(double capacity);
    List<Rocket> findRocketsByIds(List<Integer> ids);

}
