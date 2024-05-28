package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Project Model to save and load data of Tab models.
 * 
 * @author Jeremiah Brenio
 */
public class Project {

    // All tab data
    private MaterialTab materialTab;
    private ToolTab toolTab;
    private TaskTab taskTab;
    private CostTab costTab;

    public Project() {
        this.materialTab = new MaterialTab();
        this.toolTab = new ToolTab();
        this.taskTab = new TaskTab();
        this.costTab = new CostTab();
    }

    /**
     * Gets the data from the JSON file and initializes the tabs.
     * It reads the JSON file, converts it into a Map, and then instantiates the
     * tabs using the data.
     * It also adds the materials and tools to the costTab.
     * 
     * @author Jeremiah Brenio
     * 
     * @param file the JSON to get the data from.
     */
    public void getData(File file) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(file);

            // TypeToken is used to get the type of the Map in JSON file
            Type type = new TypeToken<Map<String, Map<String, Object>>>() {
            }.getType();
            Map<String, Map<String, Object>> data = gson.fromJson(reader, type);

            // Instantiate the tabs
            materialTab.instantiate(data.get(materialTab.getKey()));
            toolTab.instantiate(data.get(toolTab.getKey()));
            taskTab.instantiate(data.get(taskTab.getKey()));
            costTab.addCostSource(materialTab.getMaterials()).addCostSource(toolTab.getTools());

            reader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Saves the current project data to a JSON file.
     * Formatted with line breaks and indentation for
     * readability.
     *
     * @author Jeremiah Brenio
     * 
     * @param file The JSON to save the data to.
     */
    public void saveData(File file) {
        // format JSON to look readable
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(file);

            Map<String, Object> data = new HashMap<>();
            data.put(materialTab.getKey(), Collections.singletonMap(materialTab.getKey(), materialTab.getMaterials()));
            data.put(toolTab.getKey(), Collections.singletonMap(toolTab.getKey(), toolTab.getTools()));
            data.put(taskTab.getKey(), Collections.singletonMap(taskTab.getKey(), taskTab.getTasks()));

            // Convert the map to a JSON string
            String json = gson.toJson(data);
            // Write the JSON string to the file
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void pullData() {
        // TODO implement here
    }

}