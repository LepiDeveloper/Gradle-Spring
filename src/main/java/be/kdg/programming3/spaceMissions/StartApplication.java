package be.kdg.programming3.spaceMissions;

import be.kdg.programming3.spaceMissions.presentation.Menu;
import be.kdg.programming3.spaceMissions.repository.DataFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private final Menu menu;

    public StartApplication(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menu.displayMenu();
    }

}
