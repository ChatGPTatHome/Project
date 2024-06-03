
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
 * CustomTreeCellRenderer class to render the tree nodes with custom
 * icons.
 * 
 * @author Jeremiah Brenio
 */
public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    private Icon folderIcon;

    public CustomTreeCellRenderer() {
        super();

        folderIcon = UIManager.getIcon("FileView.directoryIcon");
    }

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