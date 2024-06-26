package view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SubmittableTextField manages a group of text fields with a submit button
 * that triggers the action callback when all text fields have been validated
 * 
 * @author Hai Duong
 */
public class SubmittableTextField extends JPanel implements GBComponent {

    /**
     * A List field that contains FilteredTextFields.
     */
    private List<FilteredTextField> textFields;

    /**
     * A JButton field that submits.
     */
    private JButton button;

    /**
     * An int field that is the length of each
     * text field.
     */
    private int length;

    /**
     * A boolean field that dictates if
     * all elements should be stacked or not.
     */
    private boolean vertical;

    /**
     * A Runnable field that runs a method with no arguments
     * and has no return.
     */
    Runnable actionCallback;

    /**
     * A GridBagConstraints field that is used to
     * set the constraints on the layout.
     */
    GridBagConstraints constraints;
    
    /**
     * Constructs a SubmittableTextField where each text field has the given length,
     * and the submit button has the given button label. Elements are placed horizontally.
     * 
     * @param length the length of each text field.
     * @param buttonLabel the text on the submit button.
     *
     * @author Hai Duong
     */
    public SubmittableTextField(int length, String buttonLabel) {
        this(length, buttonLabel, false);
    }
    
    /**
     * Constructs a SubmittableTextField where each text field has the given length,
     * and the submit button has the given button label. If vertical is true, the text
     * fields will be stacked above the submit button.
     * 
     * @param length the length of each text field.
     * @param buttonLabel the text on the submit button.
     * @param vertical true if all elements should be stacked
     *
     * @author Hai Duong
     */
    public SubmittableTextField(int length, String buttonLabel, boolean vertical) {
        super(new GridBagLayout());
        this.textFields = new ArrayList<>();
        this.button = new JButton(buttonLabel);        
        this.length = length;
        this.vertical = vertical;

        this.button.addActionListener(e -> {
            for (FilteredTextField textField : this.textFields) {
                if (!textField.validateField())
                    return;
            }

            this.actionCallback.run();

            for (FilteredTextField textField : this.textFields)
                textField.getTextField().setText("");
        });

        this.add(this.button);
    }

    /**
     * Notifies this component that it now has a parent component.
     * When this method is invoked, the chain of parent
     * components is set up with KeyboardAction event listeners.
     *
     * @author Hai Duong
     */
    @Override
    public void addNotify() {
        super.addNotify();

        GridBagConstraints constraintsCopy = (GridBagConstraints)this.constraints.clone();
        this.constraints.gridy = 0;
        for (FilteredTextField textField : this.textFields) {
            textField.setChildConstraints(constraintsCopy);
            this.add(textField, this.constraints);
            if (vertical) this.constraints.gridy++;
        }

        if (vertical) {
            this.constraints.gridy++;
            this.constraints.insets = new Insets(15, 0, 0, 0);
        }
        this.add(this.button, this.constraints);
    }

    /**
     * Gets the submit button.
     * 
     * @return the submit button.
     *
     * @author Hai Duong
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Sets the action callback. The action callback will be 
     * run when all text fields are validated and the submit button
     * is pressed.
     * 
     * @param actionCallback the callback to call.
     *
     * @author Hai Duong
     */
    public void setActionCallback(Runnable actionCallback) {
        this.actionCallback = actionCallback;
    }

    /**
     * Adds a text field to the end of the group.
     * 
     * @param validationCallback the callback to validate a text field's input.
     * @return the created text field.
     *
     * @author Hai Duong
     */
    public JTextField addTextField(Function<String, Boolean> validationCallback) {
        return this.addTextField("", validationCallback);
    }
    
    /**
     * Adds a labeled text field to the end of the group.
     * 
     * @param label the text field's label.
     * @param verifyCallback the callback to validate a text field's input.
     * @return the created text field.
     *
     * @author Hai Duong
     */
    public JTextField addTextField(String label, Function<String, Boolean> verifyCallback) {
        FilteredTextField textField = new FilteredTextField(label, this.length, this.vertical, verifyCallback);
        this.textFields.add(textField);

        return textField.getTextField();
    }

    /**
     * Sets the GridBagConstraints of the child components (text fields and 
     * submit button).
     * 
     * @param constraints the GridBagConstraints instance.
     *
     * @author Hai Duong
     */
    @Override
    public void setChildConstraints(GridBagConstraints constraints) {
        this.constraints = constraints;
    }
}
