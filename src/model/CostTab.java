package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Cost model used for calculating costs.
 * 
 * @author Hai Duong
 */
public class CostTab extends Tab {
    double budget;
    List<List<PricedItem>> costSources;

    /**
     * Constructs a CostTab in a null-state.
     * 
     * @author Hai Duong
     */
    public CostTab() {
        this.budget = 0;
        this.costSources = new ArrayList<List<PricedItem>>();
    }

    /**
     * Gets the budget.
     * 
     * @return the current budget.
     *
     * @author Hai Duong
     */
    public double getBudget() {
        return this.budget;
    }

    /**
     * Sets the budget to the given amount.
     * 
     * @param budget the new budget value
     *
     * @author Hai Duong
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Gets they key that belongs to the desired section of the map equivalent
     * of JSON data. In this case the key is "Cost" for CostTab.
     *
     * @return the String key "Cost".
     *
     * @author Hai Duong
     */
    @Override
    public String getKey() {
        return "Cost";
    }

    /**
     * Instantiates the members of tab classes so that they are no longer
     * in null state.
     *
     * @param tab Map equivalent of JSON data to parse.
     *
     * @author Hai Duong.
     */
    @Override
    public void instantiate(Map<String, Object> tab) {
        this.costSources.clear();
        this.budget = (double)tab.get("budget");
    }

    /**
     * Adds a list source to find PricedItem objects.
     * 
     * @param costSource the list containing PricedItem objects.
     * @return this CostTab.
     * 
     * @author Hai Duong
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
     * 
     * @author Hai Duong
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

    /**
     * Calculates and returns the amount of budget left with
     * current costs.
     *
     * @return double that represents the leftover budget.
     *
     * @author Hai Duong
     */
    public double getRemaining() {
        return this.budget - this.getCost();
    }
}
