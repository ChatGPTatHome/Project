package view;

import model.Models;

public class ProjectScreen extends Screen{

    public ProjectScreen(Models models){
        super(models);
    }

    /**
     * Updates the Project screen view.
     */
    @Override
    public void update() {}

    /**
     * Gets the name for the Project screen.
     * @return The name of the Project screen.
     */
    @Override
    public String getName() {
        return "Project";
    }

}
