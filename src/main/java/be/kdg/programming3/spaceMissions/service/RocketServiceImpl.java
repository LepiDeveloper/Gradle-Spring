package be.kdg.programming3.spaceMissions.service;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.RocketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RocketServiceImpl implements RocketService {

    private final RocketRepository rocketRepository;

    public RocketServiceImpl(RocketRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
    }

    @Override
    public List<Rocket> getAllRockets() {
        return rocketRepository.findAllRockets();
    }

    @Override
    public Optional<Rocket> getRocketById(int id) {
        return rocketRepository.findRocketById(id);
    }

    @Override
    public Rocket addRocket(Rocket rocket) {
        return rocketRepository.saveRocket(rocket);
    }

    @Override
    public Rocket updateRocket(Rocket rocket) {
        return null;
    }

    @Override
    public void deleteRocket(int id) {
        rocketRepository.deleteRocketById(id);
    }

    @Override
    public List<Rocket> getRocketsByMinCapacity(double minCapacity) {
        return rocketRepository.findRocketsByLaunchCapacityGreaterThan(minCapacity);
    }
}
