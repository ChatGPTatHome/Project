package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;

import model.Owner;

/**
 * @author Jeremiah Brenio, Hai Duong.
 *
 * @version v1.00
 * 
 *          Displays a JPanel to display ownership of the App.
 */
public class ProfileScreen extends CardPanel {

    /** To store the owner's name and email address. */
    private Owner owner;

    /** To show owner's name. */
    private JLabel nameLabel;

    /** To show owner's email. */
    private JLabel emailLabel;

    /** Makes a ProfileScreen with text boxes. */
    public ProfileScreen() {
        this.owner = this.getModel(Owner.class);
        nameLabel = new JLabel("NAME: " + owner.getName());
        emailLabel = new JLabel("EMAIL: " + owner.getEmail());

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("Owner: "));
        add(nameLabel);
        add(emailLabel);
    }

    /**
     * Returns the name of the Screen.
     */
    @Override
    public String getName() {
        return "Profile";
    }

    /**
     * Updates the Owner's information.
     */
    @Override
    public void update() {
        nameLabel.setText(owner.getName());
        emailLabel.setText(owner.getEmail());
    }
}
