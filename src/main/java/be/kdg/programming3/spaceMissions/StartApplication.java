package be.kdg.programming3.spaceMissions;

import be.kdg.programming3.spaceMissions.presentation.Menu;
import be.kdg.programming3.spaceMissions.repository.DataFactory;

public class StartApplication {

    public static void main(String[] args) {
        DataFactory.seed();

        Menu menu = new Menu();
        menu.displayMenu();

    }


}
