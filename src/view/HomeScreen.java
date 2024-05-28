package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import model.CostTab;
import model.Folder;
import model.ImportExport;
import model.MaterialTab;
import model.Models;
import model.Project;
import model.TaskTab;
import model.ToolTab;

/**
 * Displays a Screen to set ownership of the App
 * and Create Folders & Projects.
 * 
 * @author Hai Duong - Created UI and functions for creating owner settings
 *         Windie Le - Created UI design for HomeScreen Project selection
 *         Jeremiah Brenio - Created functions for creating folders and projects
 */
public class HomeScreen extends Screen {

    private ImportExport importExport;
    private Project project;
    private MainFrame mainFrame;
    private Folder folder;

    // To update working directory
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;

    /**
     * Constructs the HomeScreen.
     * 
     * @param models    the models to be used
     * @param mainFrame the main frame to be used
     */
    public HomeScreen(Models models, MainFrame mainFrame) {
        super(models);
        this.mainFrame = mainFrame;
        this.importExport = getModel(ImportExport.class);
        this.importExport.pullData();
        this.project = getModel(Project.class);
        this.project.setMaterialTab(models.getModel(MaterialTab.class));
        this.project.setToolTab(models.getModel(ToolTab.class));
        this.project.setTaskTab(models.getModel(TaskTab.class));
        this.project.setCostTab(models.getModel(CostTab.class));
        this.folder = getModel(Folder.class);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createProjectListPanel();
        createModifyPanel();
        createEntryFields();
    }

    /**
     * Creates the panel that will contain the project list.
     * 
     * @author Windie Le
     */
    private void createProjectListPanel() {
        // Project List Panel
        JLabel titleLabel = new JLabel("Project Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane treeScroller = new JScrollPane(createJTree());
        treeScroller.setPreferredSize(new Dimension(250, 50));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(titleLabel, BorderLayout.NORTH);
        listPanel.add(treeScroller, BorderLayout.CENTER);

        add(listPanel);
    }

    /**
     * Creates the project directory for the user to select a project.
     * 
     * @author Jeremiah Brenio
     */
    private JTree createJTree() {

        // Create JTree to display the project list
        root = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(root);

        // Retrieve JSON files from the data folder
        retrieveJSONFiles(folder.getFileObject(), root, model);

        JTree tree = new JTree(model);
        tree.setRootVisible(false);

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Get the node at the location of the double-click
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (node != null && node.isLeaf()) {
                        // Get the file path by walking up the tree from the node
                        StringBuilder filePath = new StringBuilder();
                        filePath.append(node.getUserObject().toString());
                        TreeNode parent = node.getParent();
                        while (parent != null) {
                            filePath.insert(0, parent.toString() + "/");
                            parent = parent.getParent();
                        }
                        filePath.insert(0, "src/data");
                        filePath.append(".json");
                        System.err.println("Selected file path: " + filePath.toString());
                        project.pullData(new File(filePath.toString()));
                        mainFrame.focusCard("Project");
                    }
                }
            }

        });

        return tree;
    }

    private void retrieveJSONFiles(File dir, DefaultMutableTreeNode parent, DefaultTreeModel model) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                // Create a new node and add it to the parent node
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                // node.setCellRenderer(UIManager.getIcon("FileView.directoryIcon"));
                // To Do: Add folder icon
                model.insertNodeInto(node, parent, parent.getChildCount());
                retrieveJSONFiles(file, node, model);
            } else if (file.getName().toLowerCase().endsWith(".json")) {
                // Don't show the about.json and settings.json files
                String blackList = file.getName().toLowerCase();
                if (!blackList.equals("about.json") && !blackList.equals("settings.json")) {
                    // Remove JSON extension from the file name and add it to the parent node.
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                            file.getName().substring(0, file.getName().length() - 5));
                    model.insertNodeInto(node, parent, parent.getChildCount());
                }
            }
        }
    }

    /**
     * Creates the panel that will contain the buttons to create new folders and
     * projects.
     * 
     * @author Jeremiah Brenio
     */
    private void createModifyPanel() {
        // Modify Panel
        JPanel modifyPanel = new JPanel();
        modifyPanel.setLayout(new BoxLayout(modifyPanel, BoxLayout.Y_AXIS));

        // New Folder Button
        JButton folderButton = new JButton("New Folder");
        folderButton.addActionListener(e -> {
            String folderName = JOptionPane.showInputDialog("Enter folder name:");
            if (folderName != null && !folderName.trim().isEmpty()) {
                // Create new folder
                File newFolder = new File(folder.getFileObject(), folderName);
                if (!newFolder.exists()) {
                    newFolder.mkdir();
                    update();
                }
            }
        });
        modifyPanel.add(folderButton);

        // New Project Button
        JButton projectButton = new JButton("New Project");
        projectButton.addActionListener(e -> {
            String projectName = JOptionPane.showInputDialog("Enter project name:");
            if (projectName != null && !projectName.trim().isEmpty()) {
                // Create new JSON file
                folder.createProject(projectName);
                update();
            }
        });
        modifyPanel.add(projectButton);

        // Add the modify panel to the right side of the screen
        this.add(modifyPanel, BorderLayout.NORTH);
    }

    /**
     * Creates the entry fields for the user to input their name and email.
     * 
     * @author Hai Duong
     */
    private void createEntryFields() {
        // Entry Fields Panel
        JPanel entryFields = new JPanel();
        entryFields.setLayout(new BoxLayout(entryFields, BoxLayout.Y_AXIS));

        JPanel nameEntry = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameEntry.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(10);
        nameEntry.add(nameField);

        JPanel emailEntry = new JPanel(new FlowLayout(FlowLayout.CENTER));
        emailEntry.add(new JLabel("email:"));
        JTextField emailField = new JTextField(10);
        emailEntry.add(emailField);

        entryFields.add(nameEntry);
        entryFields.add(emailEntry);

        // Submit Button Centered
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            this.importExport.setName(nameField.getText());
            this.importExport.setEmail(emailField.getText());
        });
        buttonPanel.add(submitButton);

        add(entryFields);
        add(buttonPanel);
    }

    /**
     * Updates the tree with the current JSON files and folders.
     * 
     * @author Jeremiah Brenio
     */
    @Override
    public void update() {
        root.removeAllChildren();
        retrieveJSONFiles(folder.getFileObject(), root, model);
        model.reload();
    }

    @Override
    public String getName() {
        return "Home";
    }
}
