package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RocketRepositoryImpl implements RocketRepository {
    @Override
    public List<Rocket> findAllRockets() {
        return DataFactory.rockets;
    }

    @Override
    public Optional<Rocket> findRocketById(int id) {
        return DataFactory.rockets.stream()
                .filter(rocket -> rocket.getRocketId() == id)
                .findFirst();
    }

    @Override
    public Rocket saveRocket(Rocket rocket) {
        if (!DataFactory.rockets.contains(rocket)) {
            DataFactory.rockets.add(rocket);
        }
        return rocket;
    }

    @Override
    public Rocket updateRocket(Rocket rocket) {
        return null;
    }

    @Override
    public void deleteRocketById(int id) {
        DataFactory.rockets.removeIf(rocket -> rocket.getRocketId() == id);
    }

    @Override
    public List<Rocket> findRocketsByLaunchCapacityGreaterThan(double capacity) {
        return DataFactory.rockets.stream()
                .filter(rocket -> rocket.getLaunchCapacity() > capacity)
                .collect(Collectors.toList());
    }
}
