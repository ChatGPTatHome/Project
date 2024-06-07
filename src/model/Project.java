package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * The Project class contains multiple tabs for managing
 * materials, tools, tasks, and costs.
 * The class uses the Gson library for JSON serialization and deserialization.
 * 
 * @author Jeremiah Brenio
 */
public class Project {

    /** The material tab for managing materials */
    private MaterialTab materialTab;
    /** The tool tab for managing tools */
    private ToolTab toolTab;
    /** The task tab for managing tasks */
    private TaskTab taskTab;
    /** The cost tab for managing costs */
    private CostTab costTab;

    /** The current file associated with the project */
    private File currFile;

    /**
     * Constructs a new Project object with empty tabs.
     * Initializes the materialTab, toolTab, taskTab, and costTab.
     * 
     * @author Jeremiah Brenio
     */
    public Project() {
        this.materialTab = new MaterialTab();
        this.toolTab = new ToolTab();
        this.taskTab = new TaskTab();
        this.costTab = new CostTab();
    }

    /**
     * Sets the material tab for the project.
     * 
     * @param materialTab the material tab to set
     * @author Jeremiah Brenio
     */
    public void setMaterialTab(MaterialTab materialTab) {
        this.materialTab = materialTab;
    }

    /**
     * Sets the tool tab for the project.
     * 
     * @param toolTab the tool tab to set
     * @author Jeremiah Brenio
     */
    public void setToolTab(ToolTab toolTab) {
        this.toolTab = toolTab;
    }

    /**
     * Sets the task tab for the project.
     * 
     * @param taskTab the task tab to set
     * @author Jeremiah Brenio
     */
    public void setTaskTab(TaskTab taskTab) {
        this.taskTab = taskTab;
    }

    /**
     * Sets the cost tab for the project.
     * 
     * @param costTab the cost tab to set
     * @author Jeremiah Brenio
     */
    public void setCostTab(CostTab costTab) {
        this.costTab = costTab;
    }

    /**
     * Gets the data from the JSON file and initializes the tabs.
     * It reads the JSON file, converts it into a Map, and then instantiates the
     * tabs using the data.
     * It also adds the materials and tools to the costTab.
     * 
     * @param file the JSON file to get the data from
     * @author Jeremiah Brenio
     */
    public void pullData(File file) {
        currFile = file;
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
            costTab.instantiate(data.get(costTab.getKey()));
            costTab.addCostSource(materialTab.getMaterials()).addCostSource(toolTab.getTools());

            reader.close();
        } catch (Exception e) {
            System.err.println("FAILED TO LOAD DATA!!!");
            System.err.println(e.toString());
        }
    }

    /**
     * Saves the current project data to a JSON file.
     * The JSON file is formatted with line breaks and indentation for readability.
     *
     * @author Jeremiah Brenio
     */
    public void saveData() {
        // format JSON to look readable
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(currFile);

            Map<String, Object> data = new HashMap<>();
            data.put(materialTab.getKey(), materialTab);
            data.put(toolTab.getKey(), toolTab);
            data.put(taskTab.getKey(), taskTab);
            data.put(costTab.getKey(), costTab);

            // Convert the map to a JSON string
            String json = gson.toJson(data);
            // Write the JSON string to the file
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            System.err.println("FAILED TO SAVE DATA!!!");
            System.err.println(e.toString());
        }
    }

    /**
     * Creates a new project with empty tabs.
     * Saves the project to a JSON file.
     * The JSON file is formatted with line breaks and indentation for readability.
     * 
     * @param file the JSON file to save the data to
     * @author Jeremiah Brenio
     */
    public void createProject(File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(file);

            Map<String, Object> data = new HashMap<>();
            data.put(materialTab.getKey(), materialTab);
            data.put(toolTab.getKey(), toolTab);
            data.put(taskTab.getKey(), taskTab);
            data.put(costTab.getKey(), costTab);

            // Convert the map to a JSON string
            String json = gson.toJson(data);
            // Write the JSON string to the file
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            System.err.println("FAILED TO CREATE PROJECT!!!");
            System.err.println(e.toString());
        }
    }

    /**
     * Creates a new project with empty tabs.
     * Saves the project to a JSON file.
     * The JSON file is formatted with line breaks and indentation for readability.
     * 
     * @param writer the FileWriter to save the data to
     * @author Jeremiah Brenio
     */
    public void createProject(FileWriter writer) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Map<String, Object> data = new HashMap<>();
            data.put(materialTab.getKey(), materialTab);
            data.put(toolTab.getKey(), toolTab);
            data.put(taskTab.getKey(), taskTab);
            data.put(costTab.getKey(), costTab);

            // Convert the map to a JSON string
            String json = gson.toJson(data);
            // Write the JSON string to the file
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            System.err.println("FAILED TO CREATE PROJECT!!!");
            System.err.println(e.toString());
        }
    }
}