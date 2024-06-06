package tests;

import model.ToolTab;
import model.PricedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for ToolTab.
 * This class provides unit tests for the ToolTab class to ensure that it properly initializes,
 * handles the retrieval of its key, and correctly processes instantiation with tools data.
 *
 * @author Windie Le
 */
public class ToolTabTest {
    private ToolTab toolTab;

    /**
     *  Sets up a new instance of ToolTab before each test method is executed.
     */
    @BeforeEach
    public void setUp() {
        toolTab = new ToolTab();
    }

    /**
     * Test the initial state of ToolTab.
     * Verifies that the tool list is properly initialized and empty, confirming readiness for data.
     */
    @Test
    public void testInitialState() {
        assertNotNull(toolTab.getTools(), "Tools list should be initialized but is null.");
        assertTrue(toolTab.getTools().isEmpty(), "Tools list should be empty upon initialization.");
    }

    /**
     * Test getKey method.
     * Ensure returns the correct key associated with the tools data.
     */
    @Test
    public void testGetKey() {
        assertEquals("tools", toolTab.getKey(), "getKey should return 'tools'.");
    }

    /**
     * Test adding tools and retrieving them.
     * Ensure it correctly parses and loads tools from a given map of data.
     */
    @Test
    public void testInstantiate() {
        List<Map<String, Object>> toolsList = new ArrayList<>();
        Map<String, Object> tool1 = new HashMap<>();
        tool1.put("name", "Hammer");
        tool1.put("price", 15.25);
        tool1.put("quantity", 10.0);

        Map<String, Object> tool2 = new HashMap<>();
        tool2.put("name", "Screwdriver");
        tool2.put("price", 9.75);
        tool2.put("quantity", 20.0);

        toolsList.add(tool1);
        toolsList.add(tool2);

        Map<String, Object> tabData = new HashMap<>();
        tabData.put("tools", toolsList);

        toolTab.instantiate(tabData);

        List<PricedItem> retrievedTools = toolTab.getTools();
        assertNotNull(retrievedTools, "Tools list should not be null after instantiation.");
        assertEquals(2, retrievedTools.size(), "There should be two tools stored.");

        PricedItem retrievedTool1 = retrievedTools.get(0);
        assertEquals("Hammer", retrievedTool1.name());
        assertEquals(15.25, retrievedTool1.price());
        assertEquals(10, retrievedTool1.quantity());

        PricedItem retrievedTool2 = retrievedTools.get(1);
        assertEquals("Screwdriver", retrievedTool2.name());
        assertEquals(9.75, retrievedTool2.price());
        assertEquals(20, retrievedTool2.quantity());
    }
}
