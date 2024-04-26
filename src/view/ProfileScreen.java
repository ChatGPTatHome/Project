package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Owner;

/**
 * @author Jeremiah Brenio.
 *
 * @version v1.00
 * 
 *          Displays a JPanel to set ownership of the App.
 */
public class ProfileScreen extends CardPanel {

    /** To store the owner's name and email address. */
    private Owner owner;

    /** */
    private JTextField nameText;

    /** Makes a ProfileScreen with text boxes. */
    public ProfileScreen() {
        super();
        owner = new Owner();

    }

    public String getName() {
        return "ProfileScreen";
    }

}
