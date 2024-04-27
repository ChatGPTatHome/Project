package view;

import model.About;
import model.Developer;

import javax.swing.*;
import java.awt.*;

/**
 * Is a JPanel. Shows information about the app.
 *
 * @author Anthony Chapkin
 */
public class AboutScreen extends CardPanel {

    private static final String NAME = "About";

    /**
     * Constructs this AboutScreen Panel. Calls multiple
     * methods to set up this panel.
     */
    public AboutScreen() {
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

    private void setText() {
        add(new JLabel("Version: V" + About.getVersion()));
        add(new JLabel("Group: ChatGPTatHome"));
        add(new JLabel(Developer.getAnthony()));
        add(new JLabel(Developer.getHai()));
        add(new JLabel(Developer.getJeremiah()));
        add(new JLabel(Developer.getWindie()));
    }

    private void setLayout() {
        setLayout(new FlowLayout());
    }

    private void setUpPanel() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        panel1.add(new JLabel("Version: V" + About.getVersion()));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel("Developers: "));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel(Developer.getAnthony()));
        panel1.add(new JLabel(Developer.getHai()));
        panel1.add(new JLabel(Developer.getJeremiah()));
        panel1.add(new JLabel(Developer.getWindie()));

        add(panel1);

        setLayout(new FlowLayout());

    }

}
