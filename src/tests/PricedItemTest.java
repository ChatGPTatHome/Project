package tests;

import model.PricedItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricedItemTest {
    @Test
    public void testParseMaps() {
        // Prepare test data
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Hammer");
        item1.put("price", 25.75);
        item1.put("quantity", 5.0); // using Double for the example as quantity might be incorrectly provided as double

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "Nails");
        item2.put("price", 5.50);
        item2.put("quantity", 50.0); // using Double for the example as quantity might be incorrectly provided as double

        maps.add(item1);
        maps.add(item2);

        // Call the method under test
        List<PricedItem> items = PricedItem.parseMaps(maps);

        // Assertions
        assertEquals(2, items.size(), "There should be two items parsed.");

        PricedItem testItem1 = items.get(0);
        assertEquals("Hammer", testItem1.name());
        assertEquals(25.75, testItem1.price());
        assertEquals(5, testItem1.quantity());

        PricedItem testItem2 = items.get(1);
        assertEquals("Nails", testItem2.name());
        assertEquals(5.50, testItem2.price());
        assertEquals(50, testItem2.quantity());
    }
}
