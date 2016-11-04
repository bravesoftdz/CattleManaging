package damdariar.gui.swing.model;

import javax.swing.tree.DefaultMutableTreeNode;

import damdariar.beans.AbstractBean;

public class DTreeNode extends DefaultMutableTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DTreeNode() {
		super();
	}

	public DTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}

	public DTreeNode(Object userObject) {
		super(userObject);
	}
	
	public Integer getParentID(){
		AbstractBean bean = (AbstractBean) getUserObject();
		return  bean.getParentID();
	}
	
	public Integer getID(){
		AbstractBean bean = (AbstractBean) getUserObject();
		return  bean.getID();
	}

	@Override
	public boolean equals(Object obj) {
		return getUserObject().equals(((DTreeNode)obj).getUserObject());
	}


}
