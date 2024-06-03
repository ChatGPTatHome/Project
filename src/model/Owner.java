package model;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Paths;

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
        Gson gson = new Gson();
        File file = new File("./src/data/settings.json");
        try {
            FileReader reader =  new FileReader(file);
            Owner owner = gson.fromJson(reader, Owner.class);
            this.name = owner.name;
            this.email = owner.email;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No file found.");
        }
    }

    /** Sets the Owner's information. */
    public void setOwner(String name, String email) {
        this.name = name;
        this.email = email;
        Gson gson = new Gson();
        File file = new File(Paths.get(System.getProperty("user.dir"), "data", "settings.json").toString());
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
