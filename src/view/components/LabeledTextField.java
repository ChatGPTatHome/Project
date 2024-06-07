package view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * LabeledTextField is a JPanel which contains both a JLabel
 * and a JTextField. These two components should be related to
 * each other.
 * 
 * @author Hai Duong
 */
public class LabeledTextField extends JPanel implements GBComponent {

    /**
     * JTextField field contained by this class. Is shown on screen.
     * on screen.
     */
    private JTextField textField;

    /**
     * JLabel field contained by this class. Is shown on screen.
     */
    private JLabel label;

    /**
     * Constructs this LabeledTextField by calling
     * the other constructor with vertical set to false.
     *
     * @param label the text to appear next to the text field.
     * @param length the length of the text field.
     *
     * @author Hai Duong
     */
    public LabeledTextField(String label, int length) {
        this(label, length, false);
    }
    
    /**
     * Constructs a LabeledTextField with the given label and length.
     * If vertical is true then the label will appear on the top left of
     * the text field. Otherwise, the label will appear to the left of it.
     * 
     * @param label the text to appear next to the text field.
     * @param length the length of the text field.
     * @param vertical whether to place label above text field.
     *
     * @author Hai Duong
     */
    public LabeledTextField(String label, int length, boolean vertical) {
        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;

        this.textField = new JTextField(length);
        this.label = new JLabel(label);

        this.add(this.label, constraints);

        if (vertical) constraints.gridy = 1;
        this.add(this.textField, constraints);
    }

    /**
     * Gets the JTextField object.
     * 
     * @return the JTextField object.
     *
     * @author Hai Duong
     */
    public JTextField getTextField() {
        return this.textField;
    }

    /**
     * Turns the JTextField into readonly mode. When in readonly
     * mode, modifications can still by made through code (not user input).
     *
     * @author Hai Duong
     */
    public void makeReadOnly() {
        this.textField.setEditable(false);
        this.textField.setFocusable(false);
    }

    /**
     * Sets the GridBagConstraints of any JPanel members (but not this
     * object itself). If no valid members exist, this function does nothing.
     * This function may override the given constraints.
     *
     * @param constraints the new constraints.
     *
     * @author Hai Duong
     */
    @Override
    public void setChildConstraints(GridBagConstraints constraints) {
        GridBagLayout layout = (GridBagLayout)this.getLayout();
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridy = 0;
        layout.setConstraints(this.label, constraints);
        if (layout.getConstraints(this.textField).gridy == 1) constraints.gridy = 1;
        layout.setConstraints(this.textField, constraints);
    }
}
