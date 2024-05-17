package view;

import model.Models;

public class ImportExportScreen extends CardPanel {

    public ImportExportScreen(Models models) {
        super(models);
    }

    /**
     * Updates the home screen view.
     */
    @Override
    public void update() {
    }

    /**
     * Gets the name for the home screen.
     * @return The name of the home screen.
     */
    @Override
    public String getName() {
        return "Home";
    }
}
