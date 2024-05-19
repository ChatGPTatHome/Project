package controller;

import view.*;

/**
 *
 * @author ChatGPTatHome
 * @version 1.0
 */

public class ProjectHub {
    /**
     * Main method to start the application.
     * It creates the main window, adds the home, about, and profile screens.
     */
public static void main(String[] theArgs) {
    MainFrame window = new MainFrame();

    window.addCard(HomeScreen.class, true);
    window.addCard(AboutScreen.class);
    window.addCard(ProfileScreen.class);
    window.addCard(ImportExportScreen.class);

    window.start();
}
}
