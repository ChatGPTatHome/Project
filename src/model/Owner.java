package model;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Stores the Owner's information.
 *
 * @author Jeremiah Brenio.
 *
 * @version v1.00
 *
 */
public class Owner {
    
    /** The Owner's name. */
    private String name;

    /** The Owner's email. */
    private String email;

    /** Sets the owner's to N/A by default. */
    public Owner() {
        name = "N/A";
        email = "N/A";
    }

    /** Sets the Owner's information. */
    public void setOwner(String name, String email) {
        this.name = name;
        this.email = email;
        Gson gson = new Gson();
        File file = new File("./src/data/settings.json");
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("No file found.");
        }
    }
    /** Returns the Owner's information. */
    public String getName() {
        return name;
    }

    /** Returns the Owner's information. */
    public String getEmail() {
        return email;
    }

}
