package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

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
import view.components.CustomTreeCellRenderer;

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
    private JTree tree;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    Set<Integer> expandedRows;

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
        // hide the about.json, settings.json, and ph.png from root data folder
        folder.setFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.getPath().toLowerCase().equals("src\\data\\about.json"))
                    return false;
                if (file.getPath().toLowerCase().equals("src\\data\\settings.json"))
                    return false;
                if (file.getPath().toLowerCase().equals("src\\data\\ph.png"))
                    return false;
                if (file.getPath().toLowerCase().equals("src\\data\\gson_license.txt"))
                    return false;

                return true;
            }
        });

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
        folder.goRoot();
        retrieveJSONFiles(folder.getCurrentFileObject(), root, model);

        tree = new JTree(model);
        tree.setRootVisible(false);

        // Set it so that empty folders still display folder icon.
        tree.setCellRenderer(new CustomTreeCellRenderer());

        // Keep track which rows were expanded
        expandedRows = new HashSet<>();
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                int row = tree.getRowForPath(event.getPath());
                expandedRows.add(row);
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                int row = tree.getRowForPath(event.getPath());
                expandedRows.remove(row);
            }
        });

        // Add a mouse listener to the tree to detect activity on a project menu
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (tree.getPathForLocation(e.getX(), e.getY()) == null) {
                    tree.clearSelection();
                    folder.goRoot();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Get the node at the location of the double-click
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (node != null) {
                        // Get the file path by walking up the tree from the node
                        StringBuilder filePath = new StringBuilder();
                        filePath.append(node.getUserObject().toString());
                        TreeNode parent = node.getParent();
                        while (parent != null) {
                            filePath.insert(0, parent.toString() + "/");
                            parent = parent.getParent();
                        }
                        filePath.insert(0, "src/data");
                        // System.err.println("Selected file path: " + filePath.toString());
                        File selectedFile = new File(filePath.toString());
                        if (selectedFile.isDirectory()) {
                            folder.setCurrentFileObject(selectedFile);
                        } else {
                            folder.setCurrentFileObject(new File(filePath.toString() + ".json"));
                        }
                    }
                } else if (e.getClickCount() == 2) {
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
                        // System.err.println("Selected file path: " + filePath.toString());
                        if (new File(filePath.toString()).isFile()) {
                            project.pullData(new File(filePath.toString()));
                            mainFrame.focusCard("Project");
                            mainFrame.toggleMenuBar(false);
                        }
                    }
                }
            }
        });

        return tree;
    }

    /**
     * Recursively retrieves JSON files from the data folder and adds them to the
     * JTree
     * 
     * @author Jeremiah Brenio
     * 
     * @param dir    - Current File
     * @param parent - Parent Node
     * @param model  - Tree Model
     */
    private void retrieveJSONFiles(File dir, DefaultMutableTreeNode parent, DefaultTreeModel model) {
        folder.setCurrentFileObject(dir);
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                // Create a new node and add it to the parent node
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                model.insertNodeInto(node, parent, parent.getChildCount());
                retrieveJSONFiles(file, node, model);
            } else if (file.getName().toLowerCase().endsWith(".json")) {
                // Remove JSON extension from the file name and add it to the parent node.
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                        file.getName().substring(0, file.getName().length() - 5));
                model.insertNodeInto(node, parent, parent.getChildCount());
            }
        }
        folder.goRoot();
    }

    /**
     * Creates the panel that will contain the buttons to create new folders and
     * projects and delete them.
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
            if (tree.getLastSelectedPathComponent() == null) {
                String folderName = JOptionPane.showInputDialog("Enter folder name:");
                if (folderName != null && !folderName.trim().isEmpty()) {
                    // Create new folder
                    folder.goRoot();
                    folder.createFolder(folderName);
                    update();
                }
            } else if (tree.getLastSelectedPathComponent() instanceof DefaultMutableTreeNode) {
                String folderName = JOptionPane.showInputDialog("Enter folder name:");
                if (folderName != null && !folderName.trim().isEmpty()) {
                    // Create new folder
                    folder.createFolder(folderName);
                    update();
                }
            }

        });
        modifyPanel.add(folderButton);

        // New Project Button
        JButton projectButton = new JButton("New Project");
        projectButton.addActionListener(e -> {
            if (tree.getLastSelectedPathComponent() == null) {
                String projectName = JOptionPane.showInputDialog("Enter project name:");
                if (projectName != null && !projectName.trim().isEmpty()) {
                    // Create new JSON file
                    folder.goRoot();
                    project.createProject(folder.createProject(projectName, false));
                    update();
                }
            } else if (tree.getLastSelectedPathComponent() instanceof DefaultMutableTreeNode) {
                String projectName = JOptionPane.showInputDialog("Enter project name:");
                if (projectName != null && !projectName.trim().isEmpty()) {
                    // Create new JSON file
                    project.createProject(folder.createProject(projectName, false));
                    update();
                }
            }
        });
        modifyPanel.add(projectButton);

        // Delete Button
        JButton deleteButton = new JButton("REMOVE FILE");
        deleteButton.addActionListener(e -> {
            // If file is not selected, do nothing
            if (tree.getLastSelectedPathComponent() == null) {
                return;
            }
            String selected = folder.getCurrentFileObject().getPath();
            // System.err.println("Current Dir: " +
            // folder.getCurrentFileObject().getPath());
            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete: " + selected + "?",
                    "Delete File", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                folder.getCurrentFileObject().delete();
                update();
            }
        });
        modifyPanel.add(deleteButton);

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
        File currentFile = folder.getCurrentFileObject();
        root.removeAllChildren();
        folder.goRoot();
        retrieveJSONFiles(folder.getCurrentFileObject(), root, model);
        model.reload();

        for (int row : expandedRows) {
            tree.expandRow(row);
        }

        folder.setCurrentFileObject(currentFile);
    }

    @Override
    public String getName() {
        return "Home";
    }
}
