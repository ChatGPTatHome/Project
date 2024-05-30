package view;

import model.ImportExport;
import model.Models;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * A view class for user's to import and export persistent data.
 * Is a Screen.
 *
 * @author Anthony Chapkin
 */
public class ImportExportScreen extends Screen {

    /**
     * Model class that reads and writes persistent
     * data such as settings.
     */
    ImportExport importExport;

    /**
     * JLabel used to show where a file was
     * exported to on the user's device.
     */
    JLabel labelExportPath;

    /**
     * JLabel used to show which file was
     * imported from the user's device.
     */
    JLabel labelImportPath;

    /**
     * Constructor for this ImportExportScreen.
     * Initializes fields, creates GUI, and wires listeners.
     *
     * @param models A Models class used to initialize model fields.
     */
    public ImportExportScreen(Models models) {
        super(models);
        this.importExport = getModel(ImportExport.class);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        this.labelExportPath = new JLabel();

        JButton exportButton = new JButton("Export Settings");
        exportButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(new File("."));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = chooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                labelExportPath.setText(path);
                importExport.pushData(path);
            }
        });
        panel.add(exportButton);
        panel.add(this.labelExportPath);
        panel.add(new Label());

        this.labelImportPath = new JLabel();

        JButton importButton = new JButton("Import Settings");
        importButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(new File("."));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int r = chooser.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                if (importExport.pullData(path)) {
                    labelImportPath.setText(path);
                } else {
                    labelImportPath.setText("Incorrect file type or format");
                }
                importExport.updateSettings();
            }
        });
        panel.add(importButton);
        panel.add(this.labelImportPath);

        this.add(panel);

    }

    /**
     * Clears the import and export paths.
     */
    @Override
    public void update() {
        this.labelExportPath.setText("");
        this.labelImportPath.setText("");
    }

    /**
     * Gets the name for the ImportExport screen.
     * 
     * @return the name of the ImportExport screen.
     */
    @Override
    public String getName() {
        return "ImportExport";
    }
}