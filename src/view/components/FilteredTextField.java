package view.components;

import java.awt.GridBagConstraints;
import java.util.function.Function;

/**
 * FilteredTextField ensures the containing text field
 * is valid by using the given validation callback.
 * 
 * @author Hai Duong
 */
public class FilteredTextField extends LabeledTextField {
    private Function<String, Boolean> validationCallback;
    
    /**
     * Constructs a FilteredTextField with the given length. Labels will be placed
     * either above the text field if vertical is true, or to the left. Pass an empty
     * string to label to have an unlabeled text field.
     * 
     * @param label the label of the text field.
     * @param length the length of the text field.
     * @param vertical whether or not to place the label above the text field.
     * @param validationCallback validates a string from the text field. Returns true if string is valid.
     */
    public FilteredTextField(String label, int length, boolean vertical, Function<String, Boolean> validationCallback) {
        super(label, length, vertical);
        this.validationCallback = validationCallback;
    }

    /**
     * Validates the text field. If an error occurs or false is returned then
     * false will be returned from this function. Otherwise true is return.
     * 
     * @return true if text field was successfully validated.
     */
    public boolean validateField() {
        try {
            return this.validationCallback.apply(this.getTextField().getText());
        } catch (Exception err) {
            return false;
        }
    }

    @Override
    public void setChildConstraints(GridBagConstraints constraints) {
        super.setChildConstraints(constraints);
    }
}