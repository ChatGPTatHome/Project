package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MaterialTab;
import view.components.PricedItemTable;
import view.components.SubmittableTextField;

/**
 * The MaterialTabScreen displays the materials entered by the user
 * for their project.
 * 
 * @author Hai Duong
 */
public class MaterialTabScreen extends Screen {
    private PricedItemTable table;
    
    /**
     * Constructs a MaterialTabScreen.
     * 
     * @param materialModel the material model
     * @author Hai Duong.
     */
    public MaterialTabScreen(MaterialTab materialModel) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        this.table = new PricedItemTable(materialModel.getMaterials());

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

            GridBagConstraints addMaterialPanelConstraints = new GridBagConstraints();
            addMaterialPanelConstraints.anchor = GridBagConstraints.CENTER;
            addMaterialPanelConstraints.weightx = 1;

            SubmittableTextField itemFields = new SubmittableTextField(10, "Add Material", true);
            itemFields.setChildConstraints(addMaterialPanelConstraints);

            JTextField nameField = itemFields.addTextField("Material:", (String text) -> !text.isEmpty());
            JTextField priceField = itemFields.addTextField("Price:", (String text) -> Double.valueOf(text) >= 0);
            JTextField quantityField = itemFields.addTextField("Quantity:", (String text) -> Integer.valueOf(text) >= 1);
            
            itemFields.setActionCallback(() -> {
                table.addRow(nameField.getText(), priceField.getText(), quantityField.getText());
            });

            GridBagConstraints innerConstraints = new GridBagConstraints();
            
            innerConstraints.gridy = 0;
            innerConstraints.fill = GridBagConstraints.HORIZONTAL;
            innerConstraints.weightx = 1;
            innerConstraints.weighty = 1;
            innerConstraints.anchor = GridBagConstraints.CENTER;

            parent.add(itemFields, innerConstraints);

            JButton removeButton = new JButton("Remove Selection");
            removeButton.addActionListener(e -> table.removeSelectedRow());

            innerConstraints.gridy = 1;
            innerConstraints.fill = GridBagConstraints.NONE;
            innerConstraints.weightx = 0;
            innerConstraints.weighty = 1;
            innerConstraints.anchor = GridBagConstraints.CENTER;
            parent.add(removeButton, innerConstraints);

            this.add(parent, constraints);
        }
    }

    @Override
    public String getName() {
        return "Materials";
    }

    @Override
    public void update() {
        this.table.update();
    }
}
