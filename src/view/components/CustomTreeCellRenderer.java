package view.components;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;

import java.awt.Component;
import java.io.File;

/**
 * This class extends the DefaultTreeCellRenderer class and overrides the
 * getTreeCellRendererComponent method to customize the rendering of tree nodes.
 * It sets a custom icon for directory nodes in the tree.
 * The class also provides a constructor that initializes the folderIcon with
 * the
 * default directory icon from the UIManager.
 * 
 * @author Jeremiah Brenio
 */
public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    /** The icon for directory nodes in the tree. */
    private Icon folderIcon;

    /**
     * Constructs a CustomTreeCellRenderer object.
     * Initializes the folderIcon with the default directory icon from the
     * UIManager.
     *
     * @author Jeremiah Brenio
     */
    public CustomTreeCellRenderer() {
        super();

        folderIcon = UIManager.getIcon("FileView.directoryIcon");
    }

    /**
     * Returns the component used to render the tree cell.
     * Overrides the getTreeCellRendererComponent method of the
     * DefaultTreeCellRenderer class.
     * 
     * @param tree     the JTree instance being rendered
     * @param value    the value of the tree node being rendered
     * @param selected true if the node is selected, false otherwise
     * @param expanded true if the node is expanded, false otherwise
     * @param leaf     true if the node is a leaf, false otherwise
     * @param row      the row index of the node being rendered
     * @param hasFocus true if the node has focus, false otherwise
     * @return the component used to render the tree cell
     * 
     * @author Jeremiah Brenio
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        /*
         * The node will be a String, the file name.
         * Since we have the Tree, we will walk up the tree to get the full path.
         */
        Object userObject = node.getUserObject();
        // System.err.println("NODE IS: " + userObject);
        if (userObject instanceof String) {
            StringBuilder filePath = new StringBuilder(userObject.toString());
            TreeNode parentNode = node.getParent();
            while (parentNode != null) {
                filePath.insert(0, parentNode.toString() + File.separator);
                if (parentNode.getParent() != null) {
                    parentNode = parentNode.getParent();
                } else {
                    break;
                }
            }
            filePath.insert(0, "src/data");
            // System.err.println("NODE PATH IS: " + filePath.toString());
            File file = new File(filePath.toString());
            if (file.isDirectory()) {
                setIcon(folderIcon);
            }
        }
        return this;
    }
}