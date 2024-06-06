package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import model.Folder;
import view.components.HyperlinkLabel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.nio.file.Files;

/**
 * AcknowledgementsScreen displays any open souce projects used
 * to develop ProjectHub.
 * 
 * @author Hai Duong
 */
public class AcknowledgementsScreen extends Screen {
    public AcknowledgementsScreen() {
        final String[] gsonLicense = new String[1];
        try {
            gsonLicense[0] = Files.readString(new Folder().goNext("gson_license.txt").toPath());
        } catch (Exception e) {
            e.printStackTrace();
            gsonLicense[0] = "Unavailable";
        }
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 1;
        constraints.weighty = 0.05;
        
        this.add(new JLabel("The following open souce libraries were used to make ProjectHub:"), constraints);

        JPanel libraryContainer = new JPanel(new GridLayout(0, 3));
        
        libraryContainer.add(new JLabel("Name"));
        libraryContainer.add(new JLabel("License"));
        libraryContainer.add(new JLabel("Notes"));

        libraryContainer.add(new JSeparator());
        libraryContainer.add(new JSeparator());
        libraryContainer.add(new JSeparator());

        libraryContainer.add(new JLabel("Gson"));
        libraryContainer.add(new HyperlinkLabel("Apache-2.0", e -> {
            JFrame frame = new JFrame("Apache License 2.0");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextArea textArea = new JTextArea(0, 0);
            textArea.setText(gsonLicense[0]);
            textArea.setCaretPosition(0);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            
            panel.add(textArea);
            frame.add(new JScrollPane(panel));
            
            frame.pack();
            frame.setVisible(true);
        }));
        libraryContainer.add(new JLabel("No modifications were made"));

        constraints.gridy = 1;
        constraints.weighty = 1;
        this.add(libraryContainer, constraints);
    }
    
    @Override
    public String getName() {
        return "Acknowledgements";
    }

    @Override
    public void update() {
        
    }
}
