package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Models;
import model.Owner;

/**
 * @author Hai Duong, Jeremiah Brenio.
 *
 * @version v1.00
 * 
 *          Displays a JPanel to set ownership of the App.
 */
public class HomeScreen extends CardPanel {
    /**
     * Constructs a HomeScreen.
     * 
     * @param owner The owner object of this app.
     */
    public HomeScreen(Models models) {
        super(models);
        this.setLayout(new BorderLayout());
        
        JPanel home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));

        JPanel entries = new JPanel();
        entries.setLayout(new BoxLayout(entries, BoxLayout.X_AXIS));

        JPanel nameEntry = new JPanel();
        nameEntry.add(new JLabel("Name:"));
        
        JTextField nameField = new JTextField(10);
        nameEntry.add(nameField);

        JPanel emailEntry = new JPanel();
        emailEntry.add(new JLabel("email:"));
        JTextField emailField = new JTextField(10);
        emailEntry.add(emailField);

        entries.add(nameEntry);
        entries.add(emailEntry);

        home.add(entries);

        JButton button = new JButton("Submit");
        button.addActionListener(e -> (this.getModel(Owner.class)).setOwner(nameField.getText(), emailField.getText()));
        home.add(button);

        this.add(home, BorderLayout.CENTER);
    }

    /**
     * Updates the home screen view.
     */
    @Override
    public void update() {}

    /**
     * Gets the name for the home screen.
     * @return The name of the home screen.
     */
    @Override
    public String getName() {
        return "Home";
    }
}