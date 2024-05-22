package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.MaterialTab;
import view.components.LabeldeTextField;
import view.components.PricedItemTable;

/**
 * The MaterialTabScreen displays the materials entered by the user
 * for their project.
 * 
 * @author Hai Duong
 */
public class MaterialTabScreen extends JPanel {
    public MaterialTabScreen(MaterialTab materialModel) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        PricedItemTable table = new PricedItemTable(materialModel.getMaterials());

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.add(table, constraints.clone());

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.25;
        constraints.weighty = 1;
        {
            JPanel parent = new JPanel(new GridBagLayout());
            GridBagConstraints innerConstraints = new GridBagConstraints();
            
            innerConstraints.gridy = 0;
            innerConstraints.fill = GridBagConstraints.HORIZONTAL;
            innerConstraints.weightx = 1;
            innerConstraints.weighty = 1;
            innerConstraints.anchor = GridBagConstraints.CENTER;
            {
                JPanel addMaterialPanel = new JPanel(new GridBagLayout());
                GridBagConstraints addMaterialPanelConstraints = new GridBagConstraints();
                addMaterialPanelConstraints.anchor = GridBagConstraints.CENTER;
                addMaterialPanelConstraints.weightx = 1;

                LabeldeTextField nameField = new LabeldeTextField("Material: ", 10, true);
                addMaterialPanelConstraints.gridy = 0;
                addMaterialPanel.add(nameField, addMaterialPanelConstraints.clone());

                LabeldeTextField priceField = new LabeldeTextField("Price: ", 10, true);
                addMaterialPanelConstraints.gridy = 1;
                addMaterialPanel.add(priceField, addMaterialPanelConstraints.clone());

                LabeldeTextField quantityField = new LabeldeTextField("Quantity: ", 10, true);
                addMaterialPanelConstraints.gridy = 2;
                addMaterialPanel.add(quantityField, addMaterialPanelConstraints.clone());

                JButton addButton = new JButton("Add Material");
                addButton.addActionListener(e -> {
                    if (nameField.getTextField().getText().equals(""))
                        return;
                    else if (priceField.getTextField().getText().equals(""))
                        return;
                    else if (quantityField.getTextField().getText().equals(""))
                        return;

                    try {
                        table.addRow(nameField.getTextField().getText(), priceField.getTextField().getText(), quantityField.getTextField().getText());
                    } catch (Exception err) { /* MAYBE SHOW MINI WARNING WINDOW */ }

                    nameField.getTextField().setText("");
                    priceField.getTextField().setText("");
                    quantityField.getTextField().setText("");
                });
                addMaterialPanelConstraints.gridy = 3;
                addMaterialPanelConstraints.anchor = GridBagConstraints.CENTER;
                addMaterialPanelConstraints.insets = new Insets(10, 0, 0, 0);
                addMaterialPanel.add(addButton, addMaterialPanelConstraints);

                parent.add(addMaterialPanel, innerConstraints.clone());
            }

            JButton removeButton = new JButton("Remove Selection");
            removeButton.addActionListener(e -> table.removeSelectedRow());

            innerConstraints.gridy = 1;
            innerConstraints.fill = GridBagConstraints.NONE;
            innerConstraints.weightx = 0;
            innerConstraints.weighty = 1;
            innerConstraints.anchor = GridBagConstraints.CENTER;
            parent.add(removeButton, innerConstraints.clone());

            this.add(parent, constraints.clone());
        }
    }
}
