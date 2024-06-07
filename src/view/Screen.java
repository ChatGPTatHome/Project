package view;
import javax.swing.JPanel;
import model.Models;

/**
 * Standard for JPanels to be added into CardLayout Panel.
 * 
 * @author Hai Duong
 * @author Anthony Chapkin - Minor addition.
 * @version v1.00
 */
public abstract class Screen extends JPanel {

    /**
     * A Models field that contains the Models
     * instance shared by a lot of classes.
     */
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
     * @param modelSource the Models to be used
     *                    by this Screen.
     *
     * @author Hai Duong
     */
    public Screen(Models modelSource) {
        this.modelSource = modelSource;
    }

    /**
     * Sets this Screens modelSource.
     *
     * @param modelSource the Models that becomes
     *                    this Screens modelSource.
     *
     * @author Hai Duong
     */
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

    /**
     * When this Screen is setVisible, updates
     * this Screen and sets visible its parent.
     *
     * @param isVisible  true to make the component visible; false to
     *          make it invisible
     *
     * @author Anthony Chapkin
     */
    @Override
    public void setVisible(boolean isVisible) {
        this.update();
        super.setVisible(isVisible);
    }

}