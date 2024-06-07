package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tab model used for storing tasks.
 * 
 * @author Hai Duong
 */
public class TaskTab extends Tab {

    /**
     * List of Strings representing tasks.
     */
    private List<String> tasks;

    /**
     * Constructs a TaskTab in a null-state.
     * 
     * @author Hai Duong
     */
    public TaskTab() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the task list.
     * @return the task list.
     * 
     * @author Hai Duong
     */
    public List<String> getTasks() {
        return this.tasks;
    }

    /**
     * Gets they key that belongs to the desired section of the map equivalent
     * of JSON data. In this case the key is the String "tasks" for TaskTab.
     *
     * @return the String key.
     *
     * @author Hai Duong
     */
    @Override
    public String getKey() {
        return "tasks";
    }

    /**
     * Instantiates the members of tab classes so that they are no longer
     * in null state.
     *
     * @param tab Map equivalent of JSON data to parse.
     *
     * @author Hai Duong.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void instantiate(Map<String, Object> tab) {
        this.tasks.clear();
        this.tasks.addAll((List<String>)tab.get("tasks"));
    }
}
