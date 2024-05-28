package view;

import model.ImportExport;
import model.Models;

import javax.swing.*;
import java.awt.*;

public class ImportExportScreen extends Screen{

    ImportExport importExport;

    public ImportExportScreen(Models models){
        super(models);
        this.importExport = getModel(ImportExport.class);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelExportPath = new JLabel();

        JButton exportButton = new JButton("Export Settings");
        exportButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = chooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                labelExportPath.setText(path);
                importExport.pushData(path);
            }
        });
        panel.add(exportButton);
        panel.add(labelExportPath);
        panel.add(new Label());

        JLabel labelImportPath = new JLabel();

        JButton importButton = new JButton("Import Settings");
        importButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
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
        panel.add(labelImportPath);

        this.add(panel);

    }



    /**
     * Updates the ImportExport screen view.
     */
    @Override
    public void update() {}

    /**
     * Gets the name for the ImportExport screen.
     * @return The name of the ImportExport screen.
     */
    @Override
    public String getName() {
        return "ImportExport";
    }

}
