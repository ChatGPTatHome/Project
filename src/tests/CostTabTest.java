package tests;

import model.CostTab;
import model.PricedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostTabTest {

    private CostTab costTab;

    @BeforeEach
    public void setUp(){
        costTab = new CostTab();
    }

    /**
     *Test initial state of CostTab.
     */
    @Test
    public void testInitialState(){
        assertEquals(0,costTab.getBudget(), "Initial cost budget should be 0.");
        assertEquals(0, costTab.getCost(), "Initial total cost should be 0, indicating no cost sources.");
    }

    /**
     * Test setting and getting the budget.
     */
    @Test
    public void testSetAndGetBudget(){
        costTab.setBudget(500);
        assertEquals(500, costTab.getBudget(), "The budget should be set to 5000.");
    }

    /**
     * Test getKey method.
     */
    @Test
    public void testGetKey(){
        assertEquals("Cost", costTab.getKey(), "getKey should return 'Cost'.");
    }

    /**
     * Test adding cost sources and calculating costs.
     */
    @Test
    public void testAddCostSourceAndGetCost(){
        List<PricedItem> costSource1 = new ArrayList<>();
        costSource1.add(new PricedItem("flower",10, 10 )); //Total cost is 100
        costSource1.add(new PricedItem("Hammer", 15.00, 2));  // Total cost 30

        List<PricedItem> costSource2 = new ArrayList<>();
        costSource2.add(new PricedItem("Paint", 25.00, 3));   // Total cost 75

        costTab.addCostSource(costSource1);
        costTab.addCostSource(costSource2);

        assertEquals(205, costTab.getCost(), "Total cost should be 205");
    }

    /**
     * Test calculating the remaining budget.
     */
    @Test
    public void testGetRemaining(){
        costTab.setBudget(200);
        List<PricedItem> costSource = new ArrayList<>();
        costSource.add(new PricedItem("Screwdriver", 20.00, 5)); //Total cost is 100

        costTab.addCostSource(costSource);

        assertEquals(100, costTab.getRemaining(), "Remaining budget should be 100.");
    }

    /**
     * Test the instantiate method.
     */
    @Test
    public void testInstantiate() {
        Map<String, Object> tabData = new HashMap<>();
        tabData.put("budget", 1000.00);

        costTab.instantiate(tabData);
        assertEquals(1000.00, costTab.getBudget(), "Budget should be set to 1000.00 through instantiate.");
    }
}
