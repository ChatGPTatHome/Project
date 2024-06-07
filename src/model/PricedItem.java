package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data class for priced items (materials and tools).
 * 
 * @author Hai Duong
 */
public record PricedItem(String name, double price, int quantity) {
    /**
     * Parse a map that links string keys to strings (name), doubles (price), or integers (quantity).
     * 
     * @param maps A List of Maps for parsing.
     * @return A list of PricedItems.
     *
     * @author Hai Duong
     */
    public static List<PricedItem> parseMaps(List<Map<String, Object>> maps) {
        List<PricedItem> output = new ArrayList<>();

        for (Map<String, Object> map : maps) {
            output.add(new PricedItem(
                (String)map.get("name"), 
                (Double)map.get("price"), 
                ((Double)map.get("quantity")).intValue()
            ));
        }

        return output;
    }
}