package tests;
import model.MaterialTab;
import model.PricedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the MaterialTab class.
 * This class is designed to ensure that the MaterialTab correctly initializes,
 * handles key retrieval, and processes instantiation with given data.
 *
 * @author Windie Le
 */
public class MaterialTabTest {
    private MaterialTab tab;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of MaterialTab to ensure each test starts with a fresh state.
     */
    @BeforeEach
    public void setUp() {
        tab = new MaterialTab();
    }

    /**
     * Test initial state of MaterialTab.
     * Ensures that the materials list is not null and is initially empty.
     */
    @Test
    public void testInitialState() {
        assertNotNull(tab.getMaterials(),"Material list should not be null");
        assertTrue(tab.getMaterials().isEmpty(), "Material list should be empty");
    }

    /**
     * Test getKey method.
     * Ensures that the correct key is returned.
     */
    @Test
    public void testGetKey() {
        assertEquals(tab.getKey(), "materials");
    }

    /**
     * Test instantiate method by simulating input data.
     * Checks that the materials list is properly populated with data provided through a simulated map,
     * mimicking the way data might be loaded from JSON or another data source.
     */
    @Test
    public void testInstantiate() {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> materialData = new ArrayList<>();
        Map<String, Object> itemData = new HashMap<>();
        itemData.put("name", "Steel");
        itemData.put("price", 150.00);
        itemData.put("quantity", 20.0);

        materialData.add(itemData);

        data.put("materials", materialData);

        tab.instantiate(data);

        List<PricedItem> materials = tab.getMaterials();
        assertFalse(materials.isEmpty(), "Material list should not be empty after instantiation");
        assertEquals(1, materials.size(), "Material list should contain one item");
        assertEquals( "Steel", materials.get(0).name(), "Check material name");
        assertEquals(150.00, materials.get(0).price(),  "Check material price");
        assertEquals(20, materials.get(0).quantity(), "Check material quantity");
    }
}
