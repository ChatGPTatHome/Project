package view;

import model.About;

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

    private void setText() {
        JLabel label = new JLabel("Version: V" + About.getVersion());
        add(label);
    }

    private void setLayout() {
        setLayout(new FlowLayout());
    }

}
