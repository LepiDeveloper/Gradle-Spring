package be.kdg.programming3.spaceMissions.service.servicejpa;

import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.springdatajpa.RocketSpringDataRepository;
import be.kdg.programming3.spaceMissions.service.RocketService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
public class RocketServiceSpringDataImpl implements RocketService {

    private final RocketSpringDataRepository rocketRepository;

    public RocketServiceSpringDataImpl(RocketSpringDataRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
    }

    @Override
    public List<Rocket> getAllRockets() {
        return rocketRepository.findAll();
    }

    @Override
    public Rocket getRocketById(int id) {
        return rocketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rocket not found"));
    }

    @Override
    public Rocket addRocket(Rocket rocket) {
        return rocketRepository.save(rocket);
    }

    @Override
    public Rocket updateRocket(Rocket rocket) {
        return rocketRepository.save(rocket);
    }

    @Override
    public void deleteRocket(int id) {
        rocketRepository.deleteById(id);
    }

    @Override
    public List<Rocket> getRocketsByMinCapacity(double minCapacity) {
        return rocketRepository.findByLaunchCapacityGreaterThan(minCapacity);
    }

    @Override
    public List<Rocket> getRocketsByIds(List<Integer> ids) {
        return rocketRepository.findByRocketIdIn(ids);
    }
}
