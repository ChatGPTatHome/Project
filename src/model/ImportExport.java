package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Model class that handles persistent data for app
 * settings and user info.
 *
 * @author Anthony Chapkin
 */
public class ImportExport {

    /** User's name. */
    private String name;

    /** User's email. */
    private String email;

    /**
     * Constructor for this ImportExport model.
     * Sets the name and email to default values.
     */
    public ImportExport() {
        name = "N/A";
        email = "N/A";
    }

    /**
     * Getter method for user's name.
     *
     * @return user's name as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for user's email.
     *
     * @return user's email as a String.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter method for user's name.
     *
     * @param name User's name as a String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for user's email.
     *
     * @param email User's email as a String.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Writes the current state of this model to
     * persistent data. Has a built-in file path.
     */
    public void updateSettings() {
        Gson gson = new Gson();
        File file = new File("./src/data/settings.json");
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.close();
        } catch (Exception e) {
            System.out.printf("ImportExport Update: %s\n", e);
        }
    }

    /**
     * Reads persistent data and updates the fields accordingly.
     * Has an in-built file path to fetch data from.
     */
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
            System.out.printf("ImportExport Pull: %s\n", e);
        }
    }

    /**
     * Writes the current state of this model to a file
     * specified by a file path. If no file exists, creates one.
     *
     * @param filePath The file path of the file to be written
     *                 data to. Is a String.
     */
    public void pushData(String filePath) {
        Gson gson = new Gson();
        File file = new File(filePath + "/settings.JSON");
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.close();
        } catch (Exception e) {
            System.out.printf("ImportExport Push: %s\n", e);
        }
    }

    /**
     * Reads data from a specified file, and updates
     * fields accordingly.
     *
     * @param filePath The file path of a file used to update fields.
     *                 Is a String, and file type should be a JSON.
     * @return true or false that file was read and used to update fields.
     */
    public boolean pullData(String filePath) {
        Gson gson = new Gson();
        File file = new File(filePath);
        try {
            FileReader reader =  new FileReader(file);
            ImportExport importExport = gson.fromJson(reader, ImportExport.class);
            this.name = importExport.name;
            this.email = importExport.email;
            reader.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}