package view;

import model.ImportExport;
import model.Models;

public class ImportExportScreen extends Screen{

    ImportExport importExport;

    public ImportExportScreen(Models models){
        super(models);
        importExport = getModel(ImportExport.class);
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
