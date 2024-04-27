package controller;

import view.MainScreen;
import view.HomeScreen;
import view.AboutScreen;
import view.ProfileScreen;

public class ProjectHub {

    public static void main(String[] theArgs) {
        MainScreen window = new MainScreen();

        HomeScreen home = new HomeScreen();
        // AboutScreen about = new AboutScreen();
        // ProfileScreen profile = new ProfileScreen();

        window.addCard(home);
        // window.addCard(about);
        // window.addCard(profile);

        window.showCard(home);

        window.start();
    }

}
