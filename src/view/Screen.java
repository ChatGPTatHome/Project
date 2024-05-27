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
public abstract class Screen extends JPanel {
    private Models modelSource;

    /**
     * Constructs a CardPanel
     */
    public Screen() {
        this(null);
    }
    
    /**
     * Constructs a CardPanel
     */
    public Screen(Models modelSource) {
        this.modelSource = modelSource;
    }

    public void setModelSource(Models modelSource) {
        this.modelSource = modelSource;
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
    protected <T> T getModel(Class<T> modelClass) {
        return this.modelSource.getModel(modelClass);
    }

    @Override
    public void setVisible(boolean isVisible) {
        this.update();
        super.setVisible(isVisible);
    }

}