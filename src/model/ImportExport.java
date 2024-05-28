package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ImportExport {

    /** The Owner's name. */
    private String name;

    /** The Owner's email. */
    private String email;

    public ImportExport() {
        name = "N/A";
        email = "N/A";
    }

    // Writes the settings to json
    public void updateSettings() {
        Gson gson = new Gson();
        File file = new File("./src/data/settings.json");
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getSettings() {

    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void pullData() {
        Gson gson = new Gson();
        File file = new File("./src/data/settings.json");
        try {
            FileReader reader =  new FileReader(file);
            ImportExport importExport = gson.fromJson(reader, ImportExport.class);
            this.name = importExport.name;
            this.email = importExport.email;
            reader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public boolean pullData(String filePath) {
        Gson gson = new Gson();
        File file = new File(filePath);
        try {
            FileReader reader =  new FileReader(file);
            ImportExport importExport = gson.fromJson(reader, ImportExport.class);
            this.name = importExport.name;
            this.email = importExport.email;
            reader.close();
        } catch (Exception e) {
            //System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public void pushData(String filePath) {
        Gson gson = new Gson();
        File file = new File(filePath + "/settings.JSON");
            try {
                FileWriter writer = new FileWriter(file);
                gson.toJson(this, writer);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }

}
