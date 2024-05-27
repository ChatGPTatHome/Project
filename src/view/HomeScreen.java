//package view;
//
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.*;
//
//import model.ImportExport;
//import model.Models;
//
///**
// * @author Hai Duong, Jeremiah Brenio.
// *
// * @version v1.00
// *
// *          Displays a JPanel to set ownership of the App.
// */
//public class HomeScreen extends Screen {
//    /**
//     * Constructs a HomeScreen.
//     *
//     * @param owner The owner object of this app.
//     */
//
//    private ImportExport importExport;
//
//    public HomeScreen(Models models) {
//        super(models);
//
//        this.importExport = getModel(ImportExport.class);
//        importExport.pullData();
//
//        this.setLayout(new BorderLayout());
//
//        JPanel home = new JPanel();
//        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));
//
//        JPanel entries = new JPanel();
//        entries.setLayout(new BoxLayout(entries, BoxLayout.X_AXIS));
//
//        JPanel nameEntry = new JPanel();
//        nameEntry.add(new JLabel("Name:"));
//
//        JTextField nameField = new JTextField(10);
//        nameEntry.add(nameField);
//
//        JPanel emailEntry = new JPanel();
//        emailEntry.add(new JLabel("email:"));
//        JTextField emailField = new JTextField(10);
//        emailEntry.add(emailField);
//
//        entries.add(nameEntry);
//        entries.add(emailEntry);
//
//        //-------
//        JLabel titleLabel = new JLabel("Project");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//
//        String [] projects = {"Project_1", "Project_2"};
//
//        JList list= new JList<>(projects);
////        list.setPreferredSize(new Dimension(20, 20));
////        list.setVisibleRowCount(5);
//
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(new JScrollPane(list));
//        panel.add(titleLabel, BorderLayout.NORTH);
//        add(panel);
//        revalidate();
//
//        list.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2){
//                    int index = list.locationToIndex(e.getPoint());
//                    if (index >= 0) {
//                        String item = list.getModel().getElementAt(index).toString();
//                        if ("Project_1".equals(item)) {
//                            System.out.println("Hi");
//                        } else if ("Project_2".equals(item)) {
//                            System.out.println("Hello");
//                        }
//                    }
//
//                }
//            }
//        });
//
//        JButton button = new JButton("Submit");
//        button.addActionListener(e -> {
//            this.importExport.setName(nameField.getText());
//            this.importExport.setEmail(emailField.getText());
//        });
//        home.add(button);
//        home.add(panel);
//
//        this.add(home, BorderLayout.CENTER);
//    }
//
//    /**
//     * Updates the home screen view.
//     */
//    @Override
//    public void update() {
//    }
//
//    /**
//     * Gets the name for the home screen.
//     * @return The name of the home screen.
//     */
//    @Override
//    public String getName() {
//        return "Home";
//    }
//}


//***************
package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import model.ImportExport;
import model.Models;

/**
 * Displays a JPanel to set ownership of the App.
 */
public class HomeScreen extends Screen {

    private ImportExport importExport;
    private JPanel mainPanel;
    private ProjectScreen projectScreen;
    private JPanel cards;
    private MainFrame mainFrame;

    public HomeScreen(Models models, MainFrame mainFrame) {
        super(models);

        this.mainFrame = mainFrame;

        this.importExport = getModel(ImportExport.class);
        importExport.pullData();

        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        cards = new JPanel(new CardLayout());
        // Project List Panel
        JLabel titleLabel = new JLabel("Project");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] projects = {"Project_1", "Project_2", "Project_3", "Project_4", "Project_5", "Project_6", "Project_7"};
        JList<String> projectList = new JList<>(projects);
        projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectList.setVisibleRowCount(2);
        JScrollPane listScroller = new JScrollPane(projectList);
        listScroller.setPreferredSize(new Dimension(250, 50));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(titleLabel, BorderLayout.NORTH);
        listPanel.add(listScroller, BorderLayout.CENTER);

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

        mainPanel.add(listPanel);
        mainPanel.add(entryFields);
        mainPanel.add(buttonPanel);

        //projectScreen = new ProjectScreen();
        cards.add(mainPanel, "Main Panel");
        //cards.add(projectScreen, "Project Screen");
        add(cards, BorderLayout.CENTER);
        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = projectList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String item = projectList.getModel().getElementAt(index).toString();
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        //cl.show(cards, "Project Screen");
                    }
                }
            }


        });
    }

    @Override
    public void update() {
    }

    @Override
    public String getName() {
        return "Home";
    }
}

