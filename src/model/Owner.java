package model;

/**
 * @author Jeremiah Brenio.
 *
 * @version v1.00
 * 
 *          Stores the Owner's information.
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
