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

    @Override
    public String getKey() {
        return "tasks";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void instantiate(Map<String, Object> tab) {
        this.tasks.clear();
        this.tasks.addAll((List<String>)tab.get("tasks"));
    }
}
