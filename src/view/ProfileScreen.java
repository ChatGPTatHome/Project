package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;

import model.ImportExport;
import model.Models;
import model.Owner;

/**
 * @author Jeremiah Brenio, Hai Duong.
 *
 * @version v1.00
 * 
 *          Displays a JPanel to display ownership of the App.
 */
public class ProfileScreen extends Screen {

    private ImportExport importExport;

    /** To show owner's name. */
    private JLabel nameLabel;

    /** To show owner's email. */
    private JLabel emailLabel;

    /** Makes a ProfileScreen with text boxes. */
    public ProfileScreen(Models modelSource) {
        super(modelSource);
        this.importExport = this.getModel(ImportExport.class);
        nameLabel = new JLabel("NAME: " + importExport.getName());
        emailLabel = new JLabel("EMAIL: " + importExport.getEmail());

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
        nameLabel.setText(importExport.getName());
        emailLabel.setText(importExport.getEmail());
        importExport.updateSettings();
    }
}
