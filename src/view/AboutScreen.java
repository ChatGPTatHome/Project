package view;

import model.About;
import model.Developer;

import javax.swing.*;
import java.awt.*;

/**
 * Is a JPanel. Shows information about the app.
 */
public class AboutScreen extends CardPanel {

    private static final String NAME = "About";

    /**
     * Constructs this AboutScreen Panel. Calls multiple
     * methods to set up this panel.
     */
    public AboutScreen() {
        setLayout();
        setText();
    }

    /**
     * Getter for the name of this screen.
     *
     * @return the name "About".
     */
    public String getName() {
        return NAME;
    }
    
    public void update() {

    }

    private void setText() {
        add(new JLabel("Version: V" + About.getVersion()));
        add(new JLabel("Developers: "));
        add(new JLabel(Developer.getAnthony()));
        add(new JLabel(Developer.getHai()));
        add(new JLabel(Developer.getJeremiah()));
        add(new JLabel(Developer.getWindie()));
    }

    private void setLayout() {
        setLayout(new FlowLayout());
    }

}
