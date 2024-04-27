package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeScreen extends CardPanel {
    public HomeScreen() {
        this.setLayout(new BorderLayout());
        
        JPanel home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));

        JPanel entries = new JPanel();
        entries.setLayout(new BoxLayout(entries, BoxLayout.X_AXIS));

        JPanel nameEntry = new JPanel();
        nameEntry.add(new JLabel("Name:"));
        nameEntry.add(new JTextField(10));

        JPanel emailEntry = new JPanel();
        emailEntry.add(new JLabel("email:"));
        emailEntry.add(new JTextField(10));

        entries.add(nameEntry);
        entries.add(emailEntry);

        home.add(entries);
        home.add(new JButton("Submit"));

        this.add(home, BorderLayout.CENTER);
    }

    public String getName() {
        return "Home";
    }
}