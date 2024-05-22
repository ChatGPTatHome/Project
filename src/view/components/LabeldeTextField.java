package view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * LabeldTextField is a JPanel which contains both a JLabel
 * and a JTextField. These two components should be related to
 * each other.
 * 
 * @author Hai Duong
 */
public class LabeldeTextField extends JPanel {
    private JTextField textField;
    
    public LabeldeTextField(String label, int length) {
        this(label, length, false);
    }
    
    /**
     * Constructs a LabeledTextField with the given label and length.
     * If vertical is true then the label will appear on the top left of
     * the text field. Otherwise, the label will appear to the left of it.
     * 
     * @param label the text to appear next to the text field.
     * @param length the length of the text field.
     * @param vertical whether or not to place label above text field.
     */
    public LabeldeTextField(String label, int length, boolean vertical) {
        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;

        this.textField = new JTextField(length);

        this.add(new JLabel(label), constraints);

        if (vertical) constraints.gridy = 1;
        this.add(this.textField, constraints);
    }

    /**
     * Gets the JTextField object.
     * 
     * @return the JTextField object.
     */
    public JTextField getTextField() {
        return this.textField;
    }

    /**
     * Turns the JTextField into readonly mode. When in readonly
     * mode, modifications can still by made through code (not user input).
     */
    public void makeReadOnly() {
        this.textField.setEditable(false);
        this.textField.setFocusable(false);
    }
}
