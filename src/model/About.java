package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

/**
 * A model class that provides access to application
 *  and user specific information.
 *
 * @author Anthony Chapkin
 */
public class About {

    /**
     * The current version of the app.
     * Is read from persistent data.
     */
    private double version;

    /**
     * An array of the developer names
     * who created this app. Read from
     * persistent data.
     */
    private String[] developers;

    /**
     * The name of the group who
     * developed this app. Is read
     * from persistent data.
     */
    private String groupName;

    /**
     * Constructor for this About model.
     * Sets fields to default values.
     */
    public About() {
        this.version = 0.0;
        this.developers = new String[]{};
        groupName = "N/A";
    }

    /**
     * Reads persistent data, and updates fields
     * accordingly. Has a built-in file path.
     */
    public void updateAbout() {
        Gson gson = new Gson();
        File file = new File("./src/data/about.json");
        try {
            FileReader reader =  new FileReader(file);
            About about = gson.fromJson(reader, About.class);
            this.version = about.version;
            this.developers = about.developers;
            this.groupName = about.groupName;
            reader.close();
        } catch (Exception e) {
            System.out.printf("About Update: %s\n", e);
        }
    }

    /**
     * Getter method for the apps version.
     *
     * @return the apps version as a double.
     */
    public double getVersion() {
        return this.version;
    }

    /**
     * Getter method for the apps developers names.
     *
     * @return String array of developer names.
     */
    public String[] getDevelopers() {
        return this.developers.clone();
    }

    /**
     * Getter for the name of the group who
     * developed this app.
     *
     * @return the group's name as a String.
     */
    public String getGroupName() {
        return this.groupName;
    }

}
