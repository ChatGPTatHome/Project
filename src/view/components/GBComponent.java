package view.components;

import java.awt.GridBagConstraints;

/**
 * GBComponent provides an interface for JPanel objects using
 * GridBagLayout so that they can forward their given constraints
 * to child JPanels. A copied GridBagConstraints should be passed into
 * the child to prevent side effects. Also, setChildConstraints should be
 * called prior to adding the JPanel.
 */
public interface GBComponent {
    /**
     * Sets the GridBagConstraints of any JPanel members (but not this
     * object itself). If no valid members exist, this function does nothing.
     * This function may override the given constraints.
     * 
     * @param constraints the new contraints.
     */
    public void setChildConstraints(GridBagConstraints constraints);
}
