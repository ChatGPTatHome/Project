package view.components;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * ListJListSyncer keeps both a List and a JList in sync 
 * with each other. Adding/removing to the given list should be done
 * through the methods of this class to ensure they're synced.
 * 
 * @author Hai Duong
 */
public class ListJListSyncer<T> extends JScrollPane {
    private DefaultListModel<T> listModel;
    private JList<T> listPanel;
    private List<T> list;
    
    /**
     * Constructs a ListJList Syncer with the given list.
     * 
     * @param list the list to sync with a JList.
     */
    public ListJListSyncer(List<T> list) {
        this.list = list;
        this.listPanel = new JList<>();
        
        this.listModel = new DefaultListModel<>();
        this.listModel.addAll(this.list);

        this.listPanel.setModel(this.listModel);

        this.listPanel.setModel(this.listModel);
        this.listPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setViewportView(this.listPanel);
    }

    /**
     * Adds the given element to the given list and JList.
     * 
     * @param element the element to add.
     */
    public void addRow(T element) {
        this.list.add(element);
        this.listModel.addElement(element);
    }

    /**
     * Removes the element at the given index from the
     * given list and JList.
     * 
     * @param element the element that was removed.
     */
    public T removeRow(int index) {
        this.list.remove(index);
        return this.listModel.remove(index);
    }

    /**
     * Removes the selected row from the given list
     * and JList.
     * 
     * @return the element that was removed or null if no element was removed.
     */
    public T removeSelectedRow() {
        int index = this.listPanel.getSelectedIndex();
        if (index != -1)
            return this.removeRow(index);
            
        return null;
    }

    /**
     * Resyncs the list model with list.
     * 
     * @author Hai Duong
     */
    public void update() {
        this.listModel.clear();
        this.listModel.addAll(this.list);
    }
}
