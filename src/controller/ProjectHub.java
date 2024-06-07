package controller;

import model.Models;
import view.*;

/**
 * A controller class that contains the main method
 * and is the driver of the program.
 *
 * @author Anthony Chapkin
 * @author Hai Duong
 * @version 1.0
 */
public class ProjectHub {

    /**
     * Main method to start the application.
     * It creates a MainFrame, adds Screens to it,
     * and then starts it.
     *
     * @author Hai Duong - Created initial MainFrame,
     * called addCard() for most cards, and started
     * the MainFrame.
     * @author Anthony Chapkin - Edited the addCard() calls and
     * created some components to call an overload of
     * addCard() to allow communication between certain cards.
     */
    public static void main(String[] theArgs) {
        MainFrame window = new MainFrame();
        Models models = window.getModelSource();
        HomeScreen homeScreen = new HomeScreen(models, window);
        ProjectScreen projectScreen = new ProjectScreen(models, window);

        window.addCard(homeScreen, true, true);
        window.addCard(AboutScreen.class);
        window.addCard(ProfileScreen.class);
        window.addCard(ImportExportScreen.class);
        window.addCard(projectScreen, false, false);
        window.addCard(AcknowledgementsScreen.class);

        window.start();
    }
}
