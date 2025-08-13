package be.kdg.programming3.spaceMissions.repository;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataFactory {

    // Static fields to hold the lists of Rockets and Missions
    public static List<Rocket> rockets = new ArrayList<>();
    public static List<Mission> missions = new ArrayList<>();
    public static List<LaunchSite> launchSites = new ArrayList<>();


    // Static method to seed data into the lists, but I needed to get rid of the static part for the PostConstruct to work.
    @PostConstruct
    public void seed() {
        // Create launch site
        LaunchSite kennedySpaceCenter = new LaunchSite(1, "Kennedy Space Center",
                "Florida, USA", "launchSitePlaceholder.png");
        LaunchSite starbase = new LaunchSite(2, "StarBase OML-A",
                "Texas, USA", "launchSitePlaceholder.png");
        LaunchSite vandenbergAirForceBase = new LaunchSite(3, "Vandenberg Air Force Base",
                "California, USA", "launchSitePlaceholder.png");
        LaunchSite capeCanaveral = new LaunchSite(4, "Cape Canaveral Space Force Station",
                "Florida, USA", "launchSitePlaceholder.png");

        launchSites.add(kennedySpaceCenter);
        launchSites.add(starbase);
        launchSites.add(vandenbergAirForceBase);
        launchSites.add(capeCanaveral);

        // Seed rockets
        Rocket falcon9 = new Rocket(1, "Falcon 9", 22800, "SpaceX", "rocketPlaceholder.png");
        Rocket starship = new Rocket(2, "Starship", 150000, "SpaceX", "rocketPlaceholder.png");
        Rocket atlasV = new Rocket(3, "Atlas V", 18800, "Lockheed Martin and ULA", "rocketPlaceholder.png");
        Rocket deltaIV = new Rocket(4, "Delta IV Heavy", 28600, "Boeing and ULA", "rocketPlaceholder.png");

        // Add rockets to the list
        rockets.add(falcon9);
        rockets.add(starship);
        rockets.add(atlasV);
        rockets.add(deltaIV);

        // Seed missions
        Mission starlinkMission = new Mission(101, "Starlink 1", "Deliver payload to low-earth orbit",
                LocalDate.of(2020, 5, 30),  MissionType.SATELLITE_DEPLOYMENT, Optional.empty(),true,"missionPlaceholder.png", kennedySpaceCenter);
        Mission crew1Mission = new Mission(102, "Crew-1", "Deliver astronauts Michael Hopkins, Victor Glover and Shannon Walker to the ISS",
                LocalDate.of(2021, 4, 23),  MissionType.CREWED_MISSION,Optional.of(3),true,"missionPlaceholder.png", kennedySpaceCenter);
        Mission perseveranceMission = new Mission(103, "Mars Perseverance Rover", "Objective for Perseverance's mission on Mars is astrobiology, including the search for signs of ancient microbial life",
                LocalDate.of(2020, 7, 30),  MissionType.INTERPLANETARY_EXPLORATION,Optional.empty(),true,"missionPlaceholder.png", vandenbergAirForceBase );
        Mission starshipTest = new Mission(104, "Fourth Flight Test", " Attempt a landing on a virtual tower, in preparation for a catch during Flight 5",
                LocalDate.of(2024, 6, 6),  MissionType.TECHNOLOGY_DEMONSTRATION,Optional.empty(),false,"missionPlaceholder.png", starbase);
        Mission LRO = new Mission(105, "Lunar Reconnaissance Orbiter", "primary goal was to make a 3D map of the Moon’s surface",
                LocalDate.of(2009, 6, 18), MissionType.LUNAR_EXPLORATION,Optional.empty(),true,"missionPlaceholder.png", capeCanaveral);
        Mission orion = new Mission(106, "Orion NROL-70", "Classified",
                LocalDate.of(2024, 4, 9), MissionType.MILITARY,Optional.empty(),true,"missionPlaceholder.png", capeCanaveral);
        Mission resupplyMission = new Mission(107, "SpaceX CRS-30", "Deliver cargo and equipment to the International Space Station",
                LocalDate.of(2024, 3, 21), MissionType.CARGO_RESUPPLY,Optional.empty(),true,"missionPlaceholder.png", capeCanaveral);

        // Add missions to the list
        missions.add(starlinkMission);
        missions.add(crew1Mission);
        missions.add(perseveranceMission);
        missions.add(starshipTest);
        missions.add(LRO);
        missions.add(orion);
        missions.add(resupplyMission);

        // this is the many-to-many relationships between rockets and missions
        starlinkMission.addRocket(falcon9);
        crew1Mission.addRocket(falcon9);
        perseveranceMission.addRocket(atlasV);
        starshipTest.addRocket(starship);
        LRO.addRocket(atlasV);
        orion.addRocket(deltaIV);
        resupplyMission.addRocket(falcon9);

        // this is the many-to-many relationships between missions and rockets
        falcon9.addMission(starlinkMission);
        starship.addMission(starlinkMission);
        falcon9.addMission(crew1Mission);
        atlasV.addMission(perseveranceMission);
        starship.addMission(starshipTest);
        atlasV.addMission(LRO);
        deltaIV.addMission(orion);
        falcon9.addMission(resupplyMission);

        // and this is the many-to-one relationships between missions and launch sites
        kennedySpaceCenter.addMission(starlinkMission);
        kennedySpaceCenter.addMission(crew1Mission);
        vandenbergAirForceBase.addMission(perseveranceMission);
        starbase.addMission(starshipTest);
        capeCanaveral.addMission(LRO);
        capeCanaveral.addMission(orion);
        capeCanaveral.addMission(resupplyMission);



    }


}
