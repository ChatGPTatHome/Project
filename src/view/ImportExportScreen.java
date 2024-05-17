package view;

import model.ImportExport;
import model.Models;

import javax.swing.*;
import java.awt.*;

public class ImportExportScreen extends Screen{

    ImportExport importExport;

    public ImportExportScreen(Models models){
        super(models);
        importExport = getModel(ImportExport.class);

        JLabel labelExportPath = new JLabel("ExportPath:");
        this.add(labelExportPath);

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
        this.add(exportButton);

        JLabel labelImportPath = new JLabel("ImportPath:");
        this.add(labelImportPath);

        JButton importButton = new JButton("Import Settings");
        importButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int r = chooser.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                labelImportPath.setText(path);
                importExport.pullData(path);
                importExport.updateSettings();
            }
        });
        this.add(importButton);

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
