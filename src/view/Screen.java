package view;
import javax.swing.JPanel;
import model.Models;

/**
 * Standard for JPanels to be added into CardLayout Panel.
 * 
 * @author Hai Duong.
 * @version v1.00
 */
public abstract class Screen extends JPanel {
    private Models modelSource;

    /**
     * Constructs a CardPanel
     * 
     * @author Hai Duong
     */
    public Screen() {
        this(null);
    }
    
    /**
     * Constructs a CardPanel
     * 
     * @author Hai Duong
     */
    public Screen(Models modelSource) {
        this.modelSource = modelSource;
    }

    public void setModelSource(Models modelSource) {
        this.modelSource = modelSource;
    }

    /**
     * Gets the name.
     * 
     * @author Hai Duong
     */
    public abstract String getName();

    /**
     * Updates the CardPanel.
     * 
     * @author Hai Duong
     */
    public abstract void update();

    /**
     * Gets the desired model
     * 
     * @author Hai Duong
     */
    protected <T> T getModel(Class<T> modelClass) {
        return this.modelSource.getModel(modelClass);
    }
}