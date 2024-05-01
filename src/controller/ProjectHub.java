package controller;

import view.MainFrame;
import view.HomeScreen;
import model.Owner;
import view.AboutScreen;
import view.ProfileScreen;

/**
 *
 * @author ChatGPTatHome
 * @version 1.0
 */

public class ProjectHub {
    // Holds the owner information for the application.
    private static final Owner owner = new Owner();

    /**
     * Main method to start the application.
     * It creates the main window, adds the home, about, and profile screens.
     */
    public static void main(String[] theArgs) {
        MainFrame window = new MainFrame();

        HomeScreen home = new HomeScreen(owner);
        AboutScreen about = new AboutScreen();
        ProfileScreen profile = new ProfileScreen(owner);

        window.addCard(home);
        window.addCard(about);
        window.addCard(profile);

        window.showCard(home);

        window.start();
    }
}
