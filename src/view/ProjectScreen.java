package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProjectScreen extends JPanel{
    private JTabbedPane tabbedPane;
    private JButton backButton;
    public ProjectScreen() {
        super(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        tabbedPane = new JTabbedPane();
        backButton = new JButton();

        TaskTab taskTab = new TaskTab();
        TaskTabScreen taskTabScreen = new TaskTabScreen(taskTab);

        MaterialTab materialTab = new MaterialTab();
        MaterialTabScreen MatTabScreen = new MaterialTabScreen(materialTab);

        ToolTab toolTab = new ToolTab();
        ToolTabScreen toolTabScreen = new ToolTabScreen(toolTab);

        CostTab costTab = new CostTab();
        CostTabScreen costTabScreen = new CostTabScreen(costTab);



        // Add tabs
        tabbedPane.addTab("Tasks", taskTabScreen);
        tabbedPane.addTab("Materials", MatTabScreen);
        tabbedPane.addTab("Tools", toolTabScreen);
        tabbedPane.addTab("Costs", costTabScreen);
//        tabbedPane.addTab("<Back",homeScreen);

        add(tabbedPane, BorderLayout.CENTER);

        // Create Back Button
        backButton = new JButton("Back");
        backButton.setBounds(10,10,10,10);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back button clicked"); // Placeholder action
            }
        });

        add(backButton, BorderLayout.NORTH);
    }


}
