package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;

import model.Owner;

/**
 * @author Hai Duong, Jeremiah Brenio.
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
    public ProfileScreen(Owner owner) {
        super();
        this.owner = owner;
        nameLabel = new JLabel("NAME: " + owner.getName());
        emailLabel = new JLabel("EMAIL: " + owner.getEmail());

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(nameLabel);
        add(emailLabel);
    }

    @Override
    public String getName() {
        return "Profile";
    }

    @Override
    public void update() {
        nameLabel.setText(owner.getName());
        emailLabel.setText(owner.getEmail());
    }
}
