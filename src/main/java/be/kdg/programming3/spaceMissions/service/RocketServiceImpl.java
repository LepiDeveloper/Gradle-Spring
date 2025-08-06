package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.RocketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RocketServiceImpl implements RocketService {

    private final RocketRepository rocketRepository;
    private static final Logger logger = LoggerFactory.getLogger(RocketServiceImpl.class);


    public RocketServiceImpl(RocketRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
    }

    @Override
    public List<Rocket> getAllRockets() {
        logger.info("Get all rockets");
        return rocketRepository.findAllRockets();
    }

    @Override
    public Optional<Rocket> getRocketById(int id) {
        logger.info("Get rocket by id: {}", id);
        return rocketRepository.findRocketById(id);
    }

    @Override
    public Rocket addRocket(Rocket rocket) {
        logger.info("Add rocket: {}", rocket);
        return rocketRepository.saveRocket(rocket);
    }

    @Override
    public Rocket updateRocket(Rocket rocket) {
        logger.info("Update rocket: {}", rocket);
        return null;
    }

    @Override
    public void deleteRocket(int id) {
        logger.info("Delete rocket by id: {}", id);
        rocketRepository.deleteRocketById(id);
    }

    @Override
    public List<Rocket> getRocketsByMinCapacity(double minCapacity) {
        logger.info("Get all rockets by min capacity: {}", minCapacity);
        return rocketRepository.findRocketsByLaunchCapacityGreaterThan(minCapacity);
    }
}
