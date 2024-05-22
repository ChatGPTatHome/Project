package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TaskTab;
import view.components.ListJListSyncer;

/**
 * The TaskTabScreen displays the tasks entered by the user
 * for their project.
 * 
 * @author Hai Duong
 */
public class TaskTabScreen extends JPanel {
    /**
     * Constructs a TaskTabScreen.
     * 
     * @param taskModel the TaskTab model to use.
     */
    public TaskTabScreen(TaskTab taskModel) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridy = 0;
        ListJListSyncer<String> list = new ListJListSyncer<>(taskModel.getTasks());
        this.add(list, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridy = 1;
        {
            JPanel parent = new JPanel(new GridBagLayout());
            GridBagConstraints innerConstraints = new GridBagConstraints();
            
            innerConstraints.weightx = 1;
            innerConstraints.anchor = GridBagConstraints.LINE_START;
            {
                JPanel addTaskPanel = new JPanel(new GridBagLayout());
                GridBagConstraints addTaskPanelConstraints = new GridBagConstraints();
                addTaskPanelConstraints.fill = GridBagConstraints.VERTICAL;

                JTextField textField = new JTextField(30);
                addTaskPanel.add(textField, addTaskPanelConstraints);

                JButton addButton = new JButton("Add Task");
                addButton.addActionListener(e -> {
                    String text = textField.getText();
                    if (text.equals(""))
                        return;

                    list.addRow(text);
                    textField.setText("");
                });

                addTaskPanel.add(addButton, addTaskPanelConstraints);

                parent.add(addTaskPanel, innerConstraints);
            }

            JButton removeButton = new JButton("Remove Selection");
            removeButton.addActionListener(e -> list.removeSelectedRow());

            innerConstraints.weightx = 1;
            innerConstraints.anchor = GridBagConstraints.LINE_END;
            parent.add(removeButton, innerConstraints);

            this.add(parent, constraints);
        }
    }
}
