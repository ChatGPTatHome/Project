package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProjectScreen extends Screen {

    private JTabbedPane tabbedPane;
    private JButton backButton;
    private MainFrame mainFrame;
    private TaskTab taskTab;
    private MaterialTab materialTab;
    private ToolTab toolTab;
    private CostTab costTab;

    public ProjectScreen(Models models, MainFrame mainFrame) {
        super(models);

        this.mainFrame = mainFrame;
        this.taskTab = this.getModel(TaskTab.class);
        this.materialTab = this.getModel(MaterialTab.class);
        this.toolTab = this.getModel(ToolTab.class);
        this.costTab = this.getModel(CostTab.class);

        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        tabbedPane = new JTabbedPane();
        backButton = new JButton();

        //TaskTab taskTab = new TaskTab();
        TaskTabScreen taskTabScreen = new TaskTabScreen(this.taskTab);

        MaterialTab materialTab = new MaterialTab();
        MaterialTabScreen MatTabScreen = new MaterialTabScreen(this.materialTab);

        //ToolTab toolTab = new ToolTab();
        ToolTabScreen toolTabScreen = new ToolTabScreen(this.toolTab);

        //CostTab costTab = new CostTab();
        CostTabScreen costTabScreen = new CostTabScreen(this.costTab);



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
                //System.out.println("Back button clicked"); // Placeholder action
                mainFrame.focusCard("Home");
            }
        });

        add(backButton, BorderLayout.NORTH);
    }

    @Override
    public String getName() {
        return "Project";
    }

    @Override
    public void update() {

    }

}
