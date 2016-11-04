package damdariar.gui.swing.forms;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import damdariar.beans.AbstractBean;
import damdariar.gui.Main;
import damdariar.gui.swing.DLabel;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.forms.listeners.BeanActionListener;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.gui.swing.forms.listeners.GridRowSelectionListener;
import damdariar.gui.swing.forms.listeners.TreeRowSelectionListener;
import damdariar.gui.swing.model.DTreeModel;
import damdariar.gui.swing.model.DTreeNode;
import damdariar.gui.swing.renderers.DTreeCellRenderer;
import damdariar.hibernate.HibernateUtil;
import damdariar.images.ImageUtil;

public class FormTree extends DScrollPane implements TreeSelectionListener,GridRowSelectionListener,BeanActionListener,DataModelStatusListener{
	
	  
	  private static final long serialVersionUID = 1L;
	  
	  JTree        tree;
	  List<TreeRowSelectionListener> treeRowSelectionListenerLists;
	  Map<Integer,DTreeNode>         nodesMap;
	  ModelBuilder                   treeModelBuilder;
	  Class<?> classType ;
	  DPanel   initialPanel = Main.initialPanel;
	  FormTree(Class<?> classType){
		this.classType = classType;
	    tree = new JTree();
	    tree.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	    tree.setCellRenderer(new DTreeCellRenderer());
	    treeModelBuilder = new ModelBuilder(this);
//		getViewport().add(tree);
		tree.addTreeSelectionListener(this);
		getViewport().add(initialPanel);
	  }
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path =e.getNewLeadSelectionPath();
		if(path == null || path.getLastPathComponent() == null)
			return;
			
		DTreeNode treeNode = (DTreeNode) path.getLastPathComponent();
		fireRowSelectionChanged(((AbstractBean)treeNode.getUserObject()));
	}
	@Override
	public void girdRowsDeleted(List<AbstractBean> deletedBeans) {
		
	}
	@Override
	public void gridRowSelectionChanged(AbstractBean beanObj) {
		tree.setSelectionPath(new TreePath(nodesMap.get(beanObj.getID()).getPath()));
	} 
	
	public void addTreeRowSelectionListener(TreeRowSelectionListener treeRowListener){
		if(treeRowSelectionListenerLists == null)
			treeRowSelectionListenerLists = new ArrayList<TreeRowSelectionListener>();
		treeRowSelectionListenerLists.add(treeRowListener);
		
	}
	
	public void fireRowSelectionChanged(AbstractBean beanObj){
		
		if(treeRowSelectionListenerLists != null)
			for(int i = 0;i < treeRowSelectionListenerLists.size() ; i++)
				treeRowSelectionListenerLists.get(i).treeRowSelectionChanged(beanObj);
				
	}
	@Override
	public void delete(AbstractBean bean) {
		DTreeNode node  = nodesMap.get(bean.getID());
		DTreeNode parent = nodesMap.get(0);
		for(int i = 0 ; i < node.getChildCount() ; i++){
			DTreeNode childNode = (DTreeNode) node.getChildAt(i);
			childNode.setParent(parent);
		    ((AbstractBean)childNode.getUserObject()).setParentID(null);
		    HibernateUtil.saveOrUpdate((AbstractBean)childNode.getUserObject());
		}
		tree.repaint();
	}
	@Override
	public void save(AbstractBean bean) {
		Integer id = bean.getID();
		DTreeNode  node = nodesMap.get(id);
		if(node != null){
			DTreeNode oldParent = (DTreeNode)node.getParent();
			Integer   currentParentNode = bean.getParentID();
			DTreeNode currentParent = nodesMap.get(currentParentNode != null ? currentParentNode : 0);
			if(!currentParent.equals(oldParent)){
				
				oldParent.remove(node);
				currentParent.add(node);
				tree.repaint();
			}
		}
		else{
			node = new DTreeNode(bean);
			DTreeNode parentNode ;
			if(bean.getParentID() != null)
				 parentNode = nodesMap.get(bean.getParentID());
			else
				 parentNode = nodesMap.get(0);
			parentNode.add(node);
			nodesMap.put(id, node);
			tree.repaint();
			
			
		}
		
	}
	@Override
	public void statusChanged(int statusType, List<Object> listOfObjs) {
		if(statusType == DataModelStatusListener.MODEL_LOADING_FINISHED && treeModelBuilder.listOfObjs == null){
			   treeModelBuilder.listOfObjs = listOfObjs;
			   treeModelBuilder.start();
		}
		
	}
	
	public String toString(){
		
		return "Form Tree";
	}

}

class ModelBuilder extends Thread{
	private FormTree formTree;
	List<Object> listOfObjs;
	AbstractBean rootBean;
	DTreeNode root;
	
	@Override
	public void run() {
		try{
			Class<?>  bean = Class.forName(formTree.classType.getName());
			rootBean  = (AbstractBean) bean.newInstance();
			rootBean.setID(new Integer(0));
		}catch(Exception e){
			
			
		}
		root = new DTreeNode(rootBean);
		DefaultTreeModel  model = new DTreeModel(root);
		formTree.tree.setModel(model);
		formTree.nodesMap = new HashMap<Integer, DTreeNode>(); 
		formTree.nodesMap.put(root.getID(),root);
		addChild();
		formTree.tree.expandRow(formTree.tree.getRowCount()-1);
//		formTree.getViewport().remove(formTree.initialPanel);
//		formTree.getViewport().add(formTree.tree);
		formTree.getViewport().setView(formTree.tree);
//		formTree.revalidate();
	}

	
	public void  addChild(){
		
		for(int i = 0 ; i < listOfObjs.size() ; i ++){
			AbstractBean currentNode = (AbstractBean) listOfObjs.get(i);
			if(formTree.nodesMap.get(currentNode.getID()) != null)
				continue;
			if(currentNode.getParentID() == null || currentNode.getParentID() == 0 )
			{
				DTreeNode node = new DTreeNode(currentNode);
				formTree.nodesMap.put(currentNode.getID(),node);
				this.root.add(node);
			}
			else if(currentNode.getParentID() != null && currentNode.getParentID() != 0){
				DTreeNode node = new DTreeNode(currentNode);
				DTreeNode rootNode =  formTree.nodesMap.get(currentNode.getParentID());
				if(rootNode == null){
					rootNode = findRoot(i+1,currentNode.getParentID());
					if(rootNode == null)
						continue;
				}
				formTree.nodesMap.put(currentNode.getID(),node);
				rootNode.add(node);
				
			}
			
		}
		
	}
	
	public DTreeNode findRoot(int startIndex,Integer parentId){
		DTreeNode rootNode;
		for(int j = startIndex ; j <  listOfObjs.size() ; j++){
			if(((AbstractBean) listOfObjs.get(j)).getID().equals(parentId)){
				rootNode = new DTreeNode((AbstractBean) listOfObjs.get(j));
				formTree.nodesMap.put(parentId,rootNode);
				DTreeNode rootRootNode = formTree.nodesMap.get(rootNode.getParentID() != null ? rootNode.getParentID() : 0);
				if(rootRootNode != null){
					rootRootNode.add(rootNode);
					return rootNode;
				}
				else{
					rootRootNode = findRoot(j+1,rootNode.getParentID());
					rootRootNode.add(rootNode);
					return rootNode;
				}
			}
		}
		
		return null;
	}

	ModelBuilder(FormTree formTree){
		
		this.formTree = formTree;
	}
	
}
