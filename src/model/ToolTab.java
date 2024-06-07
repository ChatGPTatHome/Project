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

    /**
     * List of PriceItem objects representing tools.
     */
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

    /**
     * Gets they key that belongs to the desired section of the map equivalent
     * of JSON data. In this case the key is "tools" for this ToolTab.
     *
     * @return the String key.
     *
     * @author Hai Duong
     */
    @Override
    public String getKey() {
        return "tools";
    }

    /**
     * Instantiates the members of tab classes so that they are no longer
     * in null state.
     *
     * @param tab Map equivalent of JSON data to parse.
     *
     * @author Hai Duong.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void instantiate(Map<String, Object> tab) {
        this.tools.clear();
        this.tools.addAll(PricedItem.parseMaps((List<Map<String, Object>>)tab.get("tools")));
    }
}
