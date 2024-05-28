package view;

import model.About;
import model.Models;

import javax.swing.*;
import java.awt.*;

/**
 * Is a JPanel. Shows information about the app.
 *
 * @author Anthony Chapkin
 */
public class AboutScreen extends Screen {

    /**
     * Constant that represents this classes
     * name as a String.
     */
    private static final String NAME = "About";

    /**
     * Model class used to read persistent data
     * about this app.
     */
    private final About about;

    /**
     * Constructs this AboutScreen. Calls multiple
     * methods to set up this Screen.
     *
     * @param models A Models object used to initialize
     *               models used by this AboutScreen.
     */
    public AboutScreen(Models models) {
        this.about = models.getModel(About.class);
        about.updateAbout();

        setUpPanel();
    }

    /**
     * Getter for the name of this Screen.
     *
     * @return the String constant field NAME.
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Implements and overrides Screen's update()
     * method. Has no functionality.
     */
    @Override
    public void update() {

    }

    /**
     * Sets up the GUI for this AboutScreen. Uses About model
     * to fetch persistent data.
     */
    private void setUpPanel() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        final String[] developers = this.about.getDevelopers();

        panel1.add(new JLabel("Version: " + this.about.getVersion()));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel("Group: " + this.about.getGroupName()));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel(developers[0]));
        panel1.add(new JLabel(developers[1]));
        panel1.add(new JLabel(developers[2]));
        panel1.add(new JLabel(developers[3]));

        add(panel1);

        setLayout(new FlowLayout());

    }

}
