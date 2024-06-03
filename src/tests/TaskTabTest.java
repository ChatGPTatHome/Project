package tests;

import model.TaskTab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TaskTabTest {
    private TaskTab taskTab;

    @BeforeEach
    public void setUp() {
        taskTab = new TaskTab();
    }

    /**
     * Test the initial state of TaskTab.
     */
    @Test
    public void testInitialState() {
        assertNotNull(taskTab.getTasks(), "Task list should be initialized but is null.");
        assertTrue(taskTab.getTasks().isEmpty(), "Task list should be empty upon initialization.");
    }

    /**
     * Test getKey method.
     */
    @Test
    public void testGetKey() {
        assertEquals("tasks", taskTab.getKey(), "getKey should return 'tasks'.");
    }

    /**
     * Test adding tasks and retrieving them.
     */
    @Test
    public void testAddAndRetrieveTasks() {
        List<String> testTasks = new ArrayList<>();
        testTasks.add("Clean the kitchen");
        testTasks.add("Make paper flower");

        // Simulate setting tasks through instantiation method
        Map<String, Object> tabData = new HashMap<>();
        tabData.put("tasks", testTasks);
        taskTab.instantiate(tabData);

        List<String> retrievedTasks = taskTab.getTasks();
        assertNotNull(retrievedTasks, "Task list should not be null after adding tasks.");
        assertEquals(2, retrievedTasks.size(), "There should be two tasks stored.");
        assertTrue(retrievedTasks.contains("Clean the kitchen"), "Task list should include 'Clean the kitchen'.");
        assertTrue(retrievedTasks.contains("Make paper flower"), "Task list should include 'Make paper flower'.");
    }

    /**
     * Test the behavior of instantiate method with an empty task list.
     */
    @Test
    public void testInstantiateWithEmptyList() {
        Map<String, Object> tabData = new HashMap<>();
        tabData.put("tasks", new ArrayList<String>());

        taskTab.instantiate(tabData);
        assertTrue(taskTab.getTasks().isEmpty(), "Task list should be empty after instantiating with an empty list.");
    }
}
