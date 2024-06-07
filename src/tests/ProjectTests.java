package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import model.CostTab;
import model.Folder;
import model.MaterialTab;
import model.PricedItem;
import model.Project;
import model.TaskTab;
import model.ToolTab;

/**
 * Test class for Project class.
 * Uses a single integration test to assert that
 * Project functions as intended in a use case
 * scenario and all interdependent methods
 * work properly together.
 * Uses JUnit 4.
 *
 * @author Hai Duong
 */
public class ProjectTests {
    /**
     * Creates a project, saves it, and then pulls it to
     * compare any differences between the project before and after pulling.
     * The test will fail if there is a difference.
     *
     * @author Hai Duong
     */
    @Test
    public void createAndPullTest() {
        var materialTab = new MaterialTab();
        var toolTab = new ToolTab();
        var taskTab = new TaskTab();
        var costTab = new CostTab();

        var material1 = new PricedItem("material 1", 2.3, 4);
        var tool1 = new PricedItem("tool 1", 5.67, 5);
        var tasks = Arrays.asList("task 1", "task 2");
        var budget = 42;

        materialTab.getMaterials().add(material1);
        toolTab.getTools().add(tool1);
        taskTab.getTasks().addAll(tasks);
        costTab.setBudget(budget);
        costTab.addCostSource(materialTab.getMaterials()).addCostSource(toolTab.getTools());

        var total = costTab.getCost();

        var project = new Project();

        project.setMaterialTab(materialTab);
        project.setToolTab(toolTab);
        project.setTaskTab(taskTab);
        project.setCostTab(costTab);

        var folder = new Folder();
        folder.createProject("saveAndLoadResultsConsistenttest");
        folder.goNext("saveAndLoadResultsConsistenttest.json");

        project.createProject(folder.getCurrentFileObject());
        project.pullData(folder.getCurrentFileObject());

        assertEquals(materialTab.getMaterials().get(0), material1);
        assertEquals(toolTab.getTools().get(0), tool1);
        assertEquals(taskTab.getTasks(), tasks);
        assertEquals(costTab.getBudget(), budget, 0.005);
        assertEquals(costTab.getCost(), total, 0.005);

        folder.goBack();
        folder.deleteFile("saveAndLoadResultsConsistenttest.json");
    }
}
