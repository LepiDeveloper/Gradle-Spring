package be.kdg.programming3.spaceMissions.presentation;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.DataFactory;
import be.kdg.programming3.spaceMissions.service.LaunchSiteService;
import be.kdg.programming3.spaceMissions.service.MissionService;
import be.kdg.programming3.spaceMissions.service.RocketService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    private final MissionService missionService;
    private final LaunchSiteService launchSiteService;
    private final RocketService rocketService;
    private static final Scanner scanner = new Scanner(System.in);
    private String question = "Please choose an option (0-5):";

    public Menu(MissionService missionService, LaunchSiteService launchSiteService, RocketService rocketService) {
        this.missionService = missionService;
        this.launchSiteService = launchSiteService;
        this.rocketService = rocketService;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void displayMenu() {
        System.out.println("\nWelcome to Space Missions Database.");

        while (true) {
            printMenuOptions(getQuestion()); // Display the menu options
            System.out.print("> "); // Prompt for user input

            String userInput = scanner.nextLine().trim(); // Read and trim the input

            if (userInput.isEmpty()) {
                // Handle empty input
                setQuestion("Input cannot be empty. Please enter a number.");
                continue; // Go to the next iteration of the loop
            }

            try {
                int choice = Integer.parseInt(userInput); // Try to parse the input
                switch (choice) {
                    case 0 -> {
                        exitApplication();
                        return;
                    }
                    case 1 -> showAllRockets();
                    case 2 -> showRocketsByLaunchCapacity();
                    case 3 -> showAllMissions();
                    case 4 -> showMissionsByTypeAndLaunchSite();
                    case 5 -> showAllLaunchSites();
                    default -> setQuestion(String.format("%d is not an available option. Please enter one of the above options.", choice));
                }
            } catch (NumberFormatException e) {
                setQuestion(String.format("%s is not a valid number. Please enter one of the above options.", userInput));
            }
        }
    }

    private void printMenuOptions(String question) {
        System.out.printf("""
        ==========================================================================================================
        What would you like to do?
        0) Quit
        1) Show all rockets
        2) Show rockets with launch capacity above a value
        3) Show all missions
        4) Show missions by type
        5) Show all launch sites
        ==========================================================================================================
        %s
        >\s""", question);
    }


    // Option 1: Show all rockets
    private void showAllRockets() {
        List<Rocket> allRockets = rocketService.getAllRockets();
        printData("Rockets", allRockets);
        setQuestion("Please choose an option (0-5):"); // Resets the question
    }

    // Option 2: Show rockets with launch capacity above a certain value
    private void showRocketsByLaunchCapacity() {

        System.out.print("Enter the minimum launch capacity (in kg): ");
        String input = scanner.nextLine();
        try {
            double minCapacity = Double.parseDouble(input);
            List<Rocket> filteredRockets = rocketService.getRocketsByMinCapacity(minCapacity);

            System.out.println("\nRockets with launch capacity above " + minCapacity + " kg:");
            System.out.println("===============================================");
            printData("Filtered Rockets", filteredRockets);
            setQuestion("Please choose an option (0-5):");

        } catch (NumberFormatException e) {
            setQuestion("Invalid input. Please enter a valid number for launch capacity.");
        }

    }

    // Option 3: Show all missions
    private void showAllMissions() {
        List<Mission> allMissions = missionService.getAllMissions();
        printData("Missions", allMissions);
        setQuestion("Please choose an option (0-5):");

    }

    // Option 4: Show missions by type and/or launch site
    private void showMissionsByTypeAndLaunchSite() {
        System.out.print("Enter mission type (leave blank for no filter): ");
        String missionType = scanner.nextLine();

        System.out.print("Enter launch site name (leave blank for no filter): ");
        String siteName = scanner.nextLine();

        List<Mission> filteredMissions = missionService.getFilteredMissions(missionType, siteName);

        printData("Filtered Missions", filteredMissions);
        setQuestion("Please choose an option (0-5):");
    }

    // Option 5: Show all launch sites
    private void showAllLaunchSites() {
        List<LaunchSite> allLaunchSites = launchSiteService.getAllLaunchSites();
        printData("Launch Sites", allLaunchSites);
        setQuestion("Please choose an option (0-5):");
    }

    private void printData(String category, List<?> data) {
        System.out.println("\n" + category + "\n==========================================================================================================");

        if (data != null) {
            if (!data.isEmpty()) {
                data.forEach(item -> System.out.println(item.toString()));
            } else {
                System.out.println("No data available.");
            }
        } else {
            System.out.println("No data available.");
        }
    }


    private void exitApplication() {
        System.out.println("Thank you!! Goodbye!");
        System.exit(0);
    }



}
