package view.components;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import model.PricedItem;

/**
 * PricedItemTable is a JLabel which displays a JTable that
 * displays one PricedItem per row.
 * 
 * @author Hai Duong
 */
public class PricedItemTable extends JScrollPane {    
    private List<PricedItem> list;
    private JTable table;
    
    /**
     * Constructs a JTable from the given list of PricedItem objects.
     * 
     * @param itemSource the list of PricedItem objects.
     */
    public PricedItemTable(List<PricedItem> itemSource) {
        this.list = itemSource;
        this.table = new JTable(new AbstractTableModel() {            
            @Override
            public int getRowCount() {
                return itemSource.size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Name";
                    case 1:
                        return "Price";
                    case 2:
                        return "Quantity";
                    default:
                        return "null";
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                PricedItem item = itemSource.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return item.name();
                    case 1:
                        return item.price();
                    case 2:
                        return item.quantity();
                    default:
                        return "null";
                }
            }
        });
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setViewportView(table);
    }

    /**
     * Updates the JTable to show any changes.
     */
    private void update() {
        ((AbstractTableModel)this.table.getModel()).fireTableDataChanged();
    }
    
    /**
     * Adds a PricedItem to the List and upates the table.
     * 
     * @param name the name of the PricedItem.
     * @param price the price of the PricedItem.
     * @param quantity the quantity of the PricedItem.
     * @throws NumberFormatException if any string cannot be converted to their proper type.
     */
    public void addRow(String name, String price, String quantity) throws NumberFormatException {
        this.addRow(name, Double.valueOf(price), Integer.valueOf(quantity));
    }
    
    /**
     * Adds a PricedItem to the List and upates the table.
     * @param name the name of the PricedItem.
     * @param price the price of the PricedItem.
     * @param quantity the quantity of the PricedItem.
     */
    public void addRow(String name, double price, int quantity) {
        this.addRow(new PricedItem(name, price, quantity));
    }
    
    /**
     * Adds a PricedItem to the List and upates the table.
     * 
     * @param item the PricedItem to add.
     */
    public void addRow(PricedItem item) {
        this.list.add(item);
        this.update();
    }

    /**
     * Removes and returns a PricedItem from the list given an index.
     * 
     * @param index the index of the PricedItem to remove in the list.
     * @return the removed PricedItem.
     */
    public PricedItem removeRow(int index) {
        PricedItem item = this.list.remove(index);
        this.update();

        return item;
    }
    
    /**
     * Removes the selected row in the JTable. If no row is selected
     * then this method does nothing.
     * 
     * @return The removed PricedItem or null if nothing was removed.
     */
    public PricedItem removeSelectedRow() {
        int index = this.table.getSelectedRow();
        if (index != -1)
            return this.removeRow(index);
            
        return null;
    }
}
