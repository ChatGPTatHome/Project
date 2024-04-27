package view;
import javax.swing.JPanel;

/**
 * @author Hai Duong.
 *
 * @version v1.00
 * 
 *          Standard for JPanels to be added into CardLayout Panel.
 */
public abstract class CardPanel extends JPanel {
    /**
     * Gets the name.
     */
    public abstract String getName();

    /**
     * Updates the CardPanel.
     */
    public abstract void update();
}