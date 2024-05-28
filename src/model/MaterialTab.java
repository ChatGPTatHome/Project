package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tab model used for storing materials.
 * 
 * @author Hai Duong
 */
public class MaterialTab extends Tab {
    private List<PricedItem> materials;

    /**
     * Constructs a MaterialTab in a null-state.
     * 
     * @author Hai Duong
     */
    public MaterialTab() {
        this.materials = new ArrayList<>();
    }

    /**
     * Gets the material list.
     * @return the material list.
     * 
     * @author Hai Duong
     */
    public List<PricedItem> getMaterials() {
        return this.materials;
    }

    @Override
    public String getKey() {
        return "materials";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void instantiate(Map<String, Object> tab) {
        this.materials.clear();
        this.materials.addAll(PricedItem.parseMaps((List<Map<String, Object>>)tab.get("materials")));
    }
}
