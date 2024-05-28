package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TaskTab;
import view.components.ListJListSyncer;
import view.components.SubmittableTextField;

/**
 * The TaskTabScreen displays the tasks entered by the user
 * for their project.
 * 
 * @author Hai Duong
 */
public class TaskTabScreen extends Screen {
    /**
     * Constructs a TaskTabScreen.
     * 
     * @param taskModel the TaskTab model to use.
     * @author Hai Duong.
     */
    public TaskTabScreen(TaskTab taskModel) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // ADD LIST
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridy = 0;
        ListJListSyncer<String> list = new ListJListSyncer<>(taskModel.getTasks());
        this.add(list, constraints);

        // CREATE BOTTOM ROW
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridy = 1;
        JPanel bottomRow = new JPanel(new GridBagLayout());
        
        // ADD SUBMITTABLE TEXT FIELD
        SubmittableTextField addTaskForm = new SubmittableTextField(30, "Add Task");
        GridBagConstraints textFieldConstraint = new GridBagConstraints();
        textFieldConstraint.fill = GridBagConstraints.VERTICAL;
        textFieldConstraint.weighty = 1;
        addTaskForm.setChildConstraints(textFieldConstraint);
        JTextField taskField = addTaskForm.addTextField((String text) -> !text.isEmpty());
        addTaskForm.setActionCallback(() -> list.addRow(taskField.getText()));
        
        bottomRow.add(addTaskForm, textFieldConstraint);

        // ADD REMOVE BUTTON
        JButton removeButton = new JButton("Remove Selection");
        removeButton.addActionListener(e -> list.removeSelectedRow());

        GridBagConstraints removeConstraints = new GridBagConstraints();
        removeConstraints.weightx = 1;
        removeConstraints.anchor = GridBagConstraints.LINE_END;
        bottomRow.add(removeButton, removeConstraints);

        this.add(bottomRow, constraints);
    }

    @Override
    public String getName() {
        return "Tasks";
    }

    @Override
    public void update() { }
}
