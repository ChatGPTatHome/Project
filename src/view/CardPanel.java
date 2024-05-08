package view;
import javax.swing.JPanel;
import model.CardModel;

/**
 * @author Hai Duong.
 *
 * @version v1.00
 * 
 *          Standard for JPanels to be added into CardLayout Panel.
 */
public abstract class CardPanel extends JPanel {
    private CardModel model;

    /**
     * Constructs a CardPanel
     */
    public CardPanel(CardModel model) {
        this.model = model;
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
    public Object getModel(Class modelClass) {
        System.out.println("B");
        return this.model.getModel(modelClass);
    }
}