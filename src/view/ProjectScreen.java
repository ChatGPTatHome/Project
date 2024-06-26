package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Provides a GUI for managing various aspects of a project through a tabbed
 * pane interface.
 * This class extends Screen and allows the user to interact with different
 * facets
 * of a project including tasks, materials, tools, and costs.
 * The interface is built around a JTabbedPane that holds various tabs for
 * different
 * sections of the project data. Each tab is associated with a specific aspect
 * of the project
 * and allows for detailed management and interaction.
 * The class also includes a back button to navigate back to the home.
 *
 * @author Windie Le
 * @author Anthony Chapkin - Minor additions.
 * @author Hai Duong - Minor additions.
 * @version 1.0
 */
public class ProjectScreen extends Screen {

    /**
     * Holds the tabs.
     */
    private JTabbedPane tabbedPane;

    /**
     * Button to return to the home screen.
     */
    private JButton backButton;

    /**
     * Main application frame.
     */
    private MainFrame mainFrame;

    /**
     * Tab for managing tasks.
     */
    private TaskTab taskTab;

    /**
     * Tab for managing materials.
     */
    private MaterialTab materialTab;

    /**
     * Tab for managing tools.
     */
    private ToolTab toolTab;

    /**
     * Tab for managing costs.
     */
    private CostTab costTab;

    /**
     * Project data model.
     */
    private Project project;

    /**
     * Screen for the task tab.
     */
    private TaskTabScreen taskTabScreen;

    /**
     * Screen for the material tab.
     */
    private MaterialTabScreen matTabScreen;

    /**
     * Screen for the tool tab.
     */
    private ToolTabScreen toolTabScreen;

    /**
     * Screen for the cost tab.
     */
    private CostTabScreen costTabScreen;

    /**
     * Constructs a new ProjectScreen which sets up the UI components and
     * initializes
     * interaction mechanisms between the tabs and the main application frame.
     *
     * @param models    the data model collection this screen will manipulate and
     *                  display.
     * @param mainFrame the main application frame to HomeScreen will return when
     *                  the back button is pressed.
     *
     * @author Windie Le
     */
    public ProjectScreen(Models models, MainFrame mainFrame) {
        super(models);

        this.mainFrame = mainFrame;

        this.project = this.getModel(Project.class);

        this.taskTab = this.getModel(TaskTab.class);
        this.materialTab = this.getModel(MaterialTab.class);
        this.toolTab = this.getModel(ToolTab.class);
        this.costTab = this.getModel(CostTab.class);

        //Set layout for this screen
        setLayout(new BorderLayout());

        // Initialize the user interface components
        initializeUI();
    }

    /**
     * Initializes and configures the UI components of the screen, including setting up
     * the tabbed pane and back button functionalities.
     *
     * @author Windie Le
     */
    private void initializeUI() {
        tabbedPane = new JTabbedPane();
        backButton = new JButton();

        // Setup individual screens for each project aspect
        this.taskTabScreen = new TaskTabScreen(this.taskTab);
        this.matTabScreen = new MaterialTabScreen(this.materialTab);
        this.toolTabScreen = new ToolTabScreen(this.toolTab);
        this.costTabScreen = new CostTabScreen(this.costTab);

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Tasks", taskTabScreen);
        tabbedPane.addTab("Materials", matTabScreen);
        tabbedPane.addTab("Tools", toolTabScreen);
        tabbedPane.addTab("Costs", costTabScreen);

        add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        // Create Back Button
        backButton = new JButton("<-back");
        backButton.setSize(new Dimension(5, 5));
        buttonPanel.add(backButton);
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(backButton, BorderLayout.WEST);

        // Setup action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                project.saveData();// Save project data when navigating back
                mainFrame.focusCard("Home"); // Focus the home screen on the main frame
                mainFrame.toggleMenuBar(true); // Toggle the menu bar visibility
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
    }

    /**
     * Return name of the screen
     * 
     * @return this Screens name as
     *         a String "Project".
     *
     * @author Anthony Chapkin
     */
    @Override
    public String getName() {
        return "Project";
    }

    /**
     * Update content of screen
     *
     * @author Hai Duong
     */
    @Override
    public void update() {
        this.taskTabScreen.update();
        this.matTabScreen.update();
        this.toolTabScreen.update();
        this.costTabScreen.update();
    }
}
