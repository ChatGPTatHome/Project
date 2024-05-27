package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

/**
 *This class provides access to application-specific information.
 */
public class About {
    private double version;
    private String[] developers;
    private String groupName;

    public About() {
        this.version = 0.0;
        this.developers = new String[]{};
        groupName = "N/A";
    }

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
            e.printStackTrace();
            System.out.println("No file found.");
        }
    }

    public double getVersion() {
        return this.version;
    }

    public String[] getDevelopers() {
        return this.developers.clone();
    }

    public String getGroupName() {
        return this.groupName;
    }

}
