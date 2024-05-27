package view;

import model.About;
import model.Developer;
import model.ImportExport;
import model.Models;

import javax.swing.*;
import java.awt.*;

/**
 * Is a JPanel. Shows information about the app.
 *
 * @author Anthony Chapkin
 */
public class AboutScreen extends Screen {

    private static final String NAME = "About";
    private final About about;

    /**
     * Constructs this AboutScreen Panel. Calls multiple
     * methods to set up this panel.
     */
    public AboutScreen(Models models) {
        this.about = models.getModel(About.class);
        about.updateAbout();

        setUpPanel();
    }

    /**
     * Getter for the name of this screen.
     *
     * @return the name "About".
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Does nothing, just overrides update.
     */
    @Override
    public void update() {

    }

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
