package view;
import javax.swing.JPanel;
import model.Models;

/**
 * @author Hai Duong.
 *
 * @version v1.00
 * 
 *          Standard for JPanels to be added into CardLayout Panel.
 */
public abstract class CardPanel extends JPanel {
    private Models models;

    /**
     * Constructs a CardPanel
     */
    public CardPanel(Models model) {
        this.models = model;
    }

    /**
     * Gets the name.
     */
    public abstract String getName();

    /**
     * Updates the CardPanel.
     */
    public abstract void update();

    /**
     * Gets the desired model
     */
    public <T> T getModel(Class<T> modelClass) {
        return this.models.getModel(modelClass);
    }
}