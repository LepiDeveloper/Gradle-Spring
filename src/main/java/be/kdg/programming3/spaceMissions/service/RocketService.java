package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.Rocket;

import java.util.List;
import java.util.Optional;

public interface RocketService {

    List<Rocket> getAllRockets();
    Optional<Rocket> getRocketById(int id);
    Rocket addRocket(Rocket rocket);
    Rocket updateRocket(Rocket rocket);
    void deleteRocket(int id);
    List<Rocket> getRocketsByMinCapacity(double minCapacity);

}
