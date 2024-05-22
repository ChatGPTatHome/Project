package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CostTab;
import view.components.LabeldeTextField;

/**
 * The ToolTabScreen displays the budget, total, and cost.
 * the budget is entered by the user while total and cost come from
 * the CostTab model.
 * 
 * @author Hai Duong
 */
public class CostTabScreen extends JPanel {
    CostTab costModel;
    LabeldeTextField budgetField;
    LabeldeTextField costField;
    LabeldeTextField remainderField;
    
    public CostTabScreen(CostTab costModel) {
        super(new GridBagLayout());

        this.costModel = costModel;
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;

        this.budgetField = new LabeldeTextField("Budget: ", 12);
        budgetField.getTextField().setText("0.0");
        
        this.costField = new LabeldeTextField("Cost: ", 12);
        costField.makeReadOnly();

        this.remainderField = new LabeldeTextField("Remaining: ", 12);
        remainderField.makeReadOnly();

        budgetField.addKeyListener(new KeyAdapter() {
            private boolean dispatched = false;
            
            @Override
            public void keyTyped(KeyEvent e) {
                if (this.dispatched)
                    return;
                if (e.getKeyChar() == 'f' || e.getKeyChar() == 'd') {
                    e.consume();
                    return;
                }
                
                try {
                    JTextField textField = (JTextField)e.getSource();
                    int cursor = textField.getCaretPosition();
                    this.dispatched = true;
                    textField.dispatchEvent(e);
                    this.dispatched = false;
                    double budget = Double.parseDouble(budgetField.getTextField().getText());
                    budgetField.getTextField().setText(Double.toString(budget));

                    update();
                    
                    if ((int)e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        textField.setCaretPosition(Math.max(0, cursor));
                    }
                    else
                        textField.setCaretPosition(Math.min(budgetField.getTextField().getText().length(), cursor + 1));
                } catch (NumberFormatException err) {
                    budgetField.getTextField().setText("0.0");
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

    public void update() {
        this.costField.getTextField().setText(Double.toString(costModel.getCost()));
        this.remainderField.getTextField().setText(Double.toString(Double.parseDouble(this.budgetField.getTextField().getText()) - costModel.getCost()));
    }
}
