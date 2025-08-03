package be.kdg.programming3.spaceMissions.presentation;

import be.kdg.programming3.spaceMissions.repository.DataFactory;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("==========================");
            System.out.println("0) Quit");
            System.out.println("1) Show all rockets");
            System.out.println("2) Show rockets with launch capacity above a value");
            System.out.println("3) Show all missions");
            System.out.println("4) Show missions by type");
            System.out.println("5) Show all launch sites");
            System.out.print("Choice (0-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    showAllRockets();
                    break;
                case 2:
                    showRocketsByLaunchCapacity();
                    break;
                case 3:
                    showAllMissions();
                    break;
                case 4:
                    showMissionsByTypeAndLaunchSite();
                    break;
                case 5:
                    showAllLaunchSites();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Option 1: Show all rockets
    private void showAllRockets() {
        System.out.println("\nAll Rockets");
        System.out.println("===========");
        DataFactory.rockets.forEach(System.out::println);
    }

    // Option 2: Show rockets with launch capacity above a certain value
    private void showRocketsByLaunchCapacity() {
        System.out.print("Enter the minimum launch capacity (in kg): ");
        double minCapacity = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        System.out.println("\nRockets with launch capacity above " + minCapacity + " kg:");
        System.out.println("===============================================");

        DataFactory.rockets.stream()
                .filter(rocket -> rocket.getLaunchCapacity() > minCapacity)
                .forEach(System.out::println);
    }

    // Option 3: Show all missions
    private void showAllMissions() {
        System.out.println("\nAll Missions");
        System.out.println("============");
        DataFactory.missions.forEach(System.out::println);
    }

    // Option 4: Show missions by type and/or launch site
    private void showMissionsByTypeAndLaunchSite() {
        System.out.print("Enter mission type (leave blank for no filter): ");
        String missionType = scanner.nextLine();

        System.out.print("Enter launch site name (leave blank for no filter): ");
        String siteName = scanner.nextLine();

        System.out.println("\nMissions filtered by type and/or launch site:");
        System.out.println("=============================================");

        DataFactory.missions.stream()
                .filter(m -> missionType.isEmpty() || m.getMissionType().name().equalsIgnoreCase(missionType))
                .filter(m -> siteName.isEmpty() || m.getLaunchSite().getSiteName().toLowerCase().contains(siteName.toLowerCase()))
                .forEach(System.out::println);

    }

    // Option 5: Show all launch sites
    private void showAllLaunchSites() {
        System.out.println("\nAll Launch Sites");
        System.out.println("============");
        DataFactory.launchSites.forEach(System.out::println);
    }


}
