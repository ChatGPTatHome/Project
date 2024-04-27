package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Owner;

public class HomeScreen extends CardPanel {
    public HomeScreen(Owner owner) {
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
        button.addActionListener(e -> owner.setOwner(nameField.getText(), emailField.getText()));
        home.add(button);

        this.add(home, BorderLayout.CENTER);
    }

    public String getName() {
        return "Home";
    }
}