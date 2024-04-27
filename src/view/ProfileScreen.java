package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

    /** To show owner's name. */
    private JLabel nameLabel;

    /** To show owner's email. */
    private JLabel emailLabel;

    /** Makes a ProfileScreen with text boxes. */
    public ProfileScreen() {
        super();
        owner = new Owner();
        nameLabel = new JLabel("NAME: " + owner.getName());
        emailLabel = new JLabel("EMAIL: " + owner.getEmail());

        setLayout(new BorderLayout());
        add(nameLabel, BorderLayout.NORTH);
        add(emailLabel, BorderLayout.SOUTH);
    }

    public String getName() {
        return "Profile";
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new ProfileScreen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
