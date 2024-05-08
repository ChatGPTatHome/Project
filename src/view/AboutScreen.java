package view;

import model.About;
import model.Developer;
import model.CardModel;

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
    public AboutScreen(CardModel cardModel) {
        super(cardModel);
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

        panel1.add(new JLabel("Version: " + About.getVersion()));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel("Group: ChatGPTatHome"));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel(Developer.getAnthony()));
        panel1.add(new JLabel(Developer.getHai()));
        panel1.add(new JLabel(Developer.getJeremiah()));
        panel1.add(new JLabel(Developer.getWindie()));

        add(panel1);

        setLayout(new FlowLayout());

    }

}
