package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hai Duong
 */
public class CostTab extends Tab {
    List<List<PricedItem>> costSources;

    /**
     * Constructs a CostTab in a null-state.
     */
    public CostTab() {
        this.costSources = new ArrayList<List<PricedItem>>();
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public void instantiate(Map<String, Object> tab) {}

    /**
     * Adds a list source to find PricedItem objects.
     * 
     * @param costSource the list containing PricedItem objects.
     * @return this CostTab.
     */
    public CostTab addCostSource(List<PricedItem> costSource) {
        this.costSources.add(costSource);

        return this;
    }

    /**
     * Calculates the cost of all PricedItem objects found in the
     * cost sources.
     * 
     * @return the cost of all PricedItem objects.
     */
    public double getCost() {
        double total = 0;

        for (List<PricedItem> costSource : this.costSources) {
            for (PricedItem item : costSource) {
                total += item.price() * item.quantity();
            }
        }

        return total;
    }
}
