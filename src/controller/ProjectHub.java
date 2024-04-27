package controller;

import view.MainScreen;
import view.HomeScreen;
import model.Owner;
import view.AboutScreen;
import view.Developer;
import view.ProfileScreen;

public class ProjectHub {
    private static final Owner owner = new Owner();

    public static void main(String[] theArgs) {
        MainScreen window = new MainScreen();

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
