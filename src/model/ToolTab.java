package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tab model used for storing tools.
 * 
 * @author Hai Duong
 */
public class ToolTab extends Tab {
    private List<PricedItem> tools;

    /**
     * Constructs a ToolTab in a null-state.
     * 
     * @author Hai Duong
     */
    public ToolTab() {
        this.tools = new ArrayList<>();
    }

    /**
     * Gets the tool list.
     * @return the tool list.
     * 
     * @author Hai Duong
     */
    public List<PricedItem> getTools() {
        return this.tools;
    }

    @Override
    public String getKey() {
        return "tools";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void instantiate(Map<String, Object> tab) {
        this.tools.clear();
        this.tools.addAll(PricedItem.parseMaps((List<Map<String, Object>>)tab.get("tools")));
    }
}
