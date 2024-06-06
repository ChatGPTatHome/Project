package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JLabel;

import model.ImportExport;
import model.Models;

/**
 * @author Jeremiah Brenio, Hai Duong.
 *
 * @version v1.00
 *
 *          Displays a JPanel to display ownership of the App.
 */
public class ProfileScreen extends Screen {

    /** ImportExport model to retrieve persistent data. */
    private ImportExport importExport;

    /** To show owner's name. */
    private JLabel nameLabel;

    /** To show owner's email. */
    private JLabel emailLabel;

    /**
     * Constructor for ProfileScreen.
     * Displays the owner's name and email.
     *
     * @param modelSource The model source to retrieve data.
     * @author Jeremiah Brenio - Created GUI and functionality to display owner's
     *         name and email.
     *         Hai Duong - Added compatability with modelSource to access
     *         ImportExport.class.
     */
    public ProfileScreen(Models modelSource) {
        super(modelSource);
        this.importExport = this.getModel(ImportExport.class);
        JLabel ownerLabel = new JLabel("Owner");
        nameLabel = new JLabel("NAME: " + importExport.getName());
        emailLabel = new JLabel("EMAIL: " + importExport.getEmail());

        Font ownerFont = new Font("Arial", Font.BOLD, 30);
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        ownerLabel.setFont(ownerFont);
        nameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);

        setLayout(new GridBagLayout());

        GridBagConstraints gbcOwner = new GridBagConstraints();
        gbcOwner.gridwidth = GridBagConstraints.REMAINDER;
        gbcOwner.anchor = GridBagConstraints.NORTH;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 1;

        add(ownerLabel, gbcOwner);
        add(Box.createVerticalGlue(), gbc);
        add(nameLabel, gbc);
        add(emailLabel, gbc);
        add(Box.createVerticalGlue(), gbc);
    }

    /**
     * Returns the name of the Screen.
     * 
     * @author Hai Duong
     */
    @Override
    public String getName() {
        return "Profile";
    }

    /**
     * Updates the Owner's information.
     * 
     * @author Jeremiah Brenio
     */
    @Override
    public void update() {
        nameLabel.setText("NAME: " + importExport.getName());
        emailLabel.setText("EMAIL: " + importExport.getEmail());
        importExport.updateSettings();
    }
}
