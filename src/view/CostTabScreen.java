package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CostTab;
import view.components.LabeledTextField;

/**
 * The ToolTabScreen displays the budget, total, and cost.
 * the budget is entered by the user while total and cost come from
 * the CostTab model.
 * 
 * @author Hai Duong
 */
public class CostTabScreen extends Screen {

    /**
     * A CostTab field used by this CostTabScreen.
     */
    CostTab costModel;

    /**
     * A LabeledTextField used to show the budget.
     */
    LabeledTextField budgetField;

    /**
     * A LabeledTextField used to show the cost.
     */
    LabeledTextField costField;

    /**
     * A LabeledTextField used to show the remaining budget.
     */
    LabeledTextField remainderField;
    
    /**
     * Construct a CostTabScreen.
     * 
     * @param costModel the costModel.
     *
     * @author Hai Duong.
     */
    public CostTabScreen(CostTab costModel) {
        this.setLayout(new GridBagLayout());

        this.costModel = costModel;
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;

        this.budgetField = new LabeledTextField("Budget: ", 12);
        budgetField.getTextField().setText(Double.toString(costModel.getBudget()));
        
        this.costField = new LabeledTextField("Cost: ", 12);
        costField.makeReadOnly();

        this.remainderField = new LabeledTextField("Remaining: ", 12);
        remainderField.makeReadOnly();

        budgetField.getTextField().addKeyListener(new KeyAdapter() {
            private boolean dispatched = false;
            
            @Override
            public void keyTyped(KeyEvent e) {
                if (this.dispatched)
                    return;
                if (e.getKeyChar() == 'f' || e.getKeyChar() == 'd') {
                    e.consume();
                    return;
                }

                JTextField textField = (JTextField)e.getSource();
                
                try {
                    int cursor = textField.getCaretPosition();
                    this.dispatched = true;
                    textField.dispatchEvent(e);
                    this.dispatched = false;
                    double budget = Double.parseDouble(budgetField.getTextField().getText());
                    budgetField.getTextField().setText(Double.toString(budget));

                    costModel.setBudget(Double.valueOf(textField.getText()));

                    update();
                    
                    if ((int)e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        textField.setCaretPosition(Math.max(0, cursor));
                    }
                    else
                        textField.setCaretPosition(Math.min(budgetField.getTextField().getText().length(), cursor + 1));
                } catch (NumberFormatException err) {
                    budgetField.getTextField().setText("0.0");
                    costModel.setBudget(0);
                }

                e.consume();
            }
        });
        
        
        JPanel parent = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.LINE_END;
        
        constraints.gridy = 0;
        parent.add(budgetField, constraints.clone());

        constraints.gridy = 1;
        parent.add(costField, constraints.clone());

        constraints.gridy = 2;
        parent.add(remainderField, constraints.clone());

        constraints.anchor = GridBagConstraints.CENTER;
        this.add(parent);

        this.update();
    }

    /**
     * Updates this CostTabScreen's GUI
     *
     * @author Hai Duong
     */
    @Override
    public void update() {
        this.budgetField.getTextField().setText(Double.toString(costModel.getBudget()));
        this.costField.getTextField().setText(Double.toString(costModel.getCost()));
        this.remainderField.getTextField().setText(String.format("%.2f", costModel.getRemaining()));
    }

    /**
     * Gets this Screens name.
     *
     * @return this Screens name as
     * a String "Costs".
     *
     * @author Hai Duong
     */
    @Override
    public String getName() {
        return "Costs";
    }
}
