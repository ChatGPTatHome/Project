package view;

import model.ImportExport;
import model.Models;

import javax.swing.*;

public class ImportExportScreen extends Screen{

    ImportExport importExport;

    public ImportExportScreen(Models models){
        super(models);
        importExport = getModel(ImportExport.class);

        JLabel label = new JLabel("Path:");
        this.add(label);

        JButton exportButton = new JButton("Import Settings");
        exportButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = chooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                label.setText(path);
            }
        });
        this.add(exportButton);

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
