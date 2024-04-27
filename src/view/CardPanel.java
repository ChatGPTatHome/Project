package view;
import javax.swing.JPanel;

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