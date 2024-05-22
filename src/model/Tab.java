package model;

import java.util.Map;

/**
 * Model abstract class to interface different Tab sub models.
 * Tab classes/models should start in a null state and be in
 * non-null state when Tab.instantiate() is called
 * 
 * @author Hai Duong
 */
public abstract class Tab {
    /**
     * Gets they key that belongs to the desired section of the map equivalent
     * of JSON data.
     */
    public abstract String getKey();

    /**
     * Instantiates the members of tab classes so they are no longer
     * in null state.
     * 
     * @param tab Map equivalent of JSON data to parse.
     */
    public abstract void instantiate(Map<String, Object> tab);
}
