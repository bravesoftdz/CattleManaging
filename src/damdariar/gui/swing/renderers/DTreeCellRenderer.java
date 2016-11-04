package damdariar.gui.swing.renderers;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import damdariar.images.ImageUtil;
import damdariar.resource.ResourceUtil;

public class DTreeCellRenderer extends DefaultTreeCellRenderer{

	private static final long serialVersionUID = 1L;
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		JLabel label =  (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		if(row == 0)
			label.setText(ResourceUtil.getTreeName(((DefaultMutableTreeNode)value).getUserObject().getClass().getSimpleName())+ "         ");
		label.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		if(leaf)
			setLeafIcon(ImageUtil.getImageIcon("cow2.png"));
		else
			setIcon(ImageUtil.getImageIcon("cow-parent.jpg"));
		return label;
	}

}
