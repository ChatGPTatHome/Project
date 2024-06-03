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
     */
    public double getBudget() {
        return this.budget;
    }

    /**
     * Sets the budget to the given amount.
     * 
     * @param budget the new budget value
     * @author Hai Duong
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String getKey() {
        return "Cost";
    }

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

    public double getRemaining() {
        return this.budget - this.getCost();
    }
}
