package be.kdg.programming3.spaceMissions.repository.collection;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.DataFactory;
import be.kdg.programming3.spaceMissions.repository.RocketRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Profile("collection")
public class RocketRepositoryImpl implements RocketRepository {

    private static final Logger logger = LoggerFactory.getLogger(RocketRepositoryImpl.class);

    @Override
    public List<Rocket> findAllRockets() {
        logger.info("Finding all rockets");
        return DataFactory.rockets;
    }

    @Override
    public Optional<Rocket> findRocketById(int id) {
        logger.info("Finding rocket by id: {}", id);
        return DataFactory.rockets.stream()
                .filter(rocket -> rocket.getRocketId() == id)
                .findFirst();
    }

    @Override
    public Rocket saveRocket(Rocket rocket) {
        logger.info("Saving rocket: {}", rocket);
        if (!DataFactory.rockets.contains(rocket)) {
            DataFactory.rockets.add(rocket);
        }
        return rocket;
    }

    @Override
    public Rocket updateRocket(Rocket rocket) {
        logger.info("Updating rocket: {}", rocket);
        return null;
    }

    @Override
    public void deleteRocketById(int id) {
        logger.info("Deleting rocket by id: {}", id);
        DataFactory.rockets.removeIf(rocket -> rocket.getRocketId() == id);
    }

    @Override
    public List<Rocket> findRocketsByLaunchCapacityGreaterThan(double capacity) {
        logger.info("Finding rockets by launch capacity: {}", capacity);
        return DataFactory.rockets.stream()
                .filter(rocket -> rocket.getLaunchCapacity() > capacity)
                .collect(Collectors.toList());
    }
}
