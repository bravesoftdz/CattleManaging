package damdariar.gui.swing.forms;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.flexdock.dockbar.DockLabelListener;
import org.flexdock.dockbar.DockbarManager;
import org.flexdock.docking.DockingConstants;
import org.flexdock.docking.DockingManager;
import org.flexdock.view.View;
import org.flexdock.view.Viewport;

import com.jgoodies.uif_lite.component.UIFSplitPane;

import damdariar.beans.AbstractBean;
import damdariar.gui.Main;
import damdariar.gui.swing.DButton;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.forms.gridform.FormGrid;
import damdariar.gui.swing.forms.listeners.AdvanceSearchListener;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.gui.swing.forms.listeners.FormActionListener;
import damdariar.gui.swing.forms.listeners.GridRowSelectionListener;
import damdariar.gui.swing.forms.listeners.TreeRowSelectionListener;
import damdariar.gui.swing.search.AdvancedSearchPanel;
import damdariar.gui.swing.search.AdvancedSearchPanelLayoutManager;
import damdariar.resource.ResourceUtil;

public class AbstractFormContentPane extends DPanel implements DockingConstants,FormActionListener {

	public  static View AppView;
	

	private static final long serialVersionUID = 1L;
	Component  topComponent;
	Component  bottomComponent;
	Component  rightComponent;
	Class<?>   classType;
	private String criteria;
	private Integer parentId;
	public Integer getParentId() {
		return parentId;
	}

	boolean isDetailForm = false;
	String  linkedProperty = "";

	public Class<?> getClassType() {
		return classType;
	}
	
	public static String getLinkedProperty(Class<?> cls){
		return AbstractBean.getLinkedPropertyName(cls);
	}
	public AbstractFormContentPane(Class<?> classType,Integer parentId,String linkName,boolean isDetailForm){
		setLayout(new BorderLayout());
		this.isDetailForm = isDetailForm;
		linkedProperty = linkName != null ? linkName : "";
		this.parentId = parentId;
		this.classType = classType;
		if(parentId == null)
			parentId = -1;
		this.criteria = isDetailForm ? AbstractBean.getMasterDetailCriteria(classType, parentId) : null;
		buildMainPanel();
	}

	public AbstractFormContentPane(Class<?> classType){
		this(classType,null,"",false);
	}
	
	public void buildMainPanel(){
        
		//build Top View
		try{
        	String formName = "damdariar.gui.swing.forms."+classType.getSimpleName()+"Form";
        	Class<?> formClass = Class.forName(formName);
        	topComponent = (Component) formClass.newInstance();
        	((AbstractForm)topComponent).setDetailFormInfo(isDetailForm,linkedProperty,parentId);
        }catch(Exception e){
        	
        	topComponent = new AbstractForm(classType,isDetailForm,linkedProperty,parentId);
        }
		
        //build Bottom View
		Boolean isTreeBased = AbstractBean.isTreeNeeded(classType);
		
		if(!isTreeBased){
			this.bottomComponent = getFormBottomComponent(false,null);
			this.bottomComponent.setPreferredSize(new Dimension(300,200));
			add(buildHorizontalSplit());
		}
		else{
			
			rightComponent = new FormTree(classType);
//			if(!AbstractBean.isTreeSeparateLoad(classType)){
				ArrayList<DataModelStatusListener> statusListeners = new ArrayList<DataModelStatusListener>();
				statusListeners.add((DataModelStatusListener) rightComponent);
				this.bottomComponent =getFormBottomComponent(true,statusListeners);
				this.bottomComponent.setPreferredSize(new Dimension(300,300));
				((FormGrid)bottomComponent).addGridRowSelectionListener((GridRowSelectionListener)rightComponent);
				((FormTree)rightComponent).addTreeRowSelectionListener((FormGrid)bottomComponent);
				((FormGrid)bottomComponent).setTreeView((FormTree)rightComponent);
	/*		}
			else{
				this.bottomComponent =getFormBottomComponent(true,null);
				this.bottomComponent.setPreferredSize(new Dimension(300,300));
				}*/
				((FormTree)rightComponent).addTreeRowSelectionListener((TreeRowSelectionListener) topComponent);
				((AbstractForm)topComponent).addBeanActionListener((FormTree)rightComponent);
				add(buildHVSplit());
		}
		((FormGrid)bottomComponent).addGridRowSelectionListener((GridRowSelectionListener)topComponent);
		((AbstractForm)topComponent).addBeanActionListener((FormGrid)bottomComponent);
		 new AdvanceSearchThread(classType,linkedProperty,((FormGrid)bottomComponent),Main.advanceSearchView).start();
		/*if(DockingManager.getDockable("AdvanceSearch") == null){
			view.dock(advanceSearchView, EAST_REGION, .1f);
			DockingManager.setMinimized(exitView, true);
		}*/
	}
	
	
	public FormGrid getFormBottomComponent(boolean isTreeBased,List<DataModelStatusListener> statusListeners ){
/*		try{
        	String formName = "damdariar.gui.swing.forms.gridform."+classType.getSimpleName()+"FormGrid";
        	Class<?> formClass = Class.forName(formName);
        	if(isTreeBased){
        		Constructor<?> constructor = formClass.getDeclaredConstructor(List.class);
        		return (FormGrid) constructor.newInstance(statusListeners);
        	}
        	else{
        		return (FormGrid) formClass.newInstance();
        	}
        }catch(Exception e){*/
        	if(isTreeBased)
        		return new FormGrid(classType,statusListeners,criteria);
        	else
        		return new FormGrid(classType,null,criteria,linkedProperty);
        		
      /*  }*/
		
		
		
	}
    
	
	private JComponent buildHorizontalSplit() {
		return buildHorizontalSplit(false);
	}
    private JComponent buildHorizontalSplit(boolean hasTree) {
    	
    	JSplitPane verticalSplit = UIFSplitPane.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, topComponent, bottomComponent);
    	verticalSplit.setOpaque(false);
    	double resizeWeight = ((AbstractForm)topComponent).getResizedWeight();
    	if(resizeWeight > 0)
    		verticalSplit.setResizeWeight(resizeWeight);
    	if(AppView == null){
    		if(!hasTree)
    			return createTopBottomContentPane(verticalSplit);
    	}else
    	{
    		 ((AbstractForm)topComponent).setView(AppView);
    	}
        return verticalSplit;
    }
    
    private JComponent buildHVSplit() {
		DockingManager.setFloatingEnabled(true);
		JComponent comp =  buildHorizontalSplit(true);
		JComponent contentPane =  createContentPane(comp);
        return contentPane/*comp*/;
    }

    private JPanel createTopBottomContentPane(JComponent comp){
    	JPanel p = new JPanel(new BorderLayout());
		p.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Viewport viewport = new Viewport();
		p.add(viewport, BorderLayout.CENTER);
		View startPage = createStartPage(comp);
		viewport.dock(startPage);
		startPage.dock(Main.advanceSearchView, WEST_REGION, .4f);
		DockingManager.setMinimized(Main.advanceSearchView, true);
	   ((AbstractForm)topComponent).setView(startPage);
	   	AppView = startPage;
		return p;
    	
    }
    
	private JPanel createContentPane(JComponent comp) {
		JPanel p = new JPanel(new BorderLayout());
		p.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Viewport viewport = new Viewport();
		p.add(viewport, BorderLayout.CENTER);
		View startPage = createStartPage(comp);
		View view1 = createView("cow.hierarchy", " \u062f\u0631\u062e\u062a \u0648\u0631\u0627\u062b\u062a\u06cc \u062f\u0627\u0645");
		viewport.dock(startPage);
		startPage.dock(view1, WEST_REGION, .15f);
	    startPage.dock(Main.advanceSearchView, WEST_REGION, .4f);
		DockingManager.setMinimized(Main.advanceSearchView, true);
		((AbstractForm)topComponent).setView(startPage);
		AppView = startPage;
		return p;
	}

	private View createView(String id, String text) {
		View view = new View(id, text);
		view.addAction(CLOSE_ACTION);
		view.addAction(PIN_ACTION);
		view.setContentPane((DScrollPane)rightComponent);
		return view;
	}

	private View createStartPage(JComponent comp) {
		String id = "startPage";
		View view = new View(id, null, null);
		view.setTerritoryBlocked(CENTER_REGION, true);
		view.setTitlebar(null);
		view.setContentPane(comp);
		return view;
	}
	public void deleteAction() {
		((AbstractForm)topComponent).deleteAction();
	}

	public void newAction() {
		((AbstractForm)topComponent).newAction();
	}

	public void saveAction() {
		((AbstractForm)topComponent).saveAction();
	}

	@Override
	public void printAction() {
		((FormGrid)bottomComponent).printAction();
	}
	
	
	
	
	public void dispose(){
		if(topComponent != null)
		  ((AbstractForm)topComponent).dispose();
	}
	
	public Integer getFormID(){
		AbstractBean form = ((AbstractForm)topComponent).$Form_Bean;
		if(form != null)
			return form.getID();
		return null;
	}

}

class AdvanceSearchThread extends Thread implements DockLabelListener{
	
	private Class<?> classType;
	private String linkedProperty;
	private AdvanceSearchListener searchListener;
	View    advanceSearchView;

	public static int  width =  480;
	public static int  height = 380;
	public static int  heightTop = 350;
	
	AdvancedSearchPanel searchPanel;
	
	public AdvanceSearchThread(Class<?> classType,String linkedProperty,AdvanceSearchListener searchListener,View advanceSearchView){
		this.classType = classType ;
		this.linkedProperty = linkedProperty;
		this.searchListener = searchListener;
		this.advanceSearchView = advanceSearchView;
		
	}
	
	
	public void run(){
		advanceSearchView.setContentPane(new DPanel());
		DockbarManager manager = DockbarManager.getInstance(Main.instance);
		manager.getLeftBar().getLabel(this.advanceSearchView).addDockLabelListener(this);	
	}

	@Override
	public void mouseEntered() {
			if(advanceSearchView.getContentPane() instanceof DScrollPane)
				return;
		    DPanel  contentPanel = new DPanel();
			DButton      searchButton = new DButton(ResourceUtil.getString("ADVANCESEARCH_BUTTON"));
			advanceSearchView.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);	
			searchButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(searchListener != null)
						searchListener.searchDone(searchPanel.getSearchCriteria());
				}});
			searchPanel = new AdvancedSearchPanel(classType,linkedProperty);
			AdvancedSearchPanelLayoutManager manager = new AdvancedSearchPanelLayoutManager(contentPanel);
			manager.getConstriant().fill = GridBagConstraints.BOTH;
			manager.getConstriant().weighty = 0;
			manager.getConstriant().gridheight = 1;
			manager.getConstriant().ipady = height-100;
			searchPanel.setPreferredSize(new Dimension(width-3, heightTop-60));
			manager.add(searchPanel);
			
			manager.gotoNewLine();
			manager.getConstriant().gridheight = 1;
			manager.getConstriant().ipady = 0;
			manager.getConstriant().insets = new Insets(0,0,0,0);
			manager.getConstriant().weightx = 0;
			manager.getConstriant().weighty = 5;
			manager.getConstriant().fill = GridBagConstraints.CENTER;
			manager.getConstriant().anchor = GridBagConstraints.SOUTH;
			manager.add(searchButton);
			
			DScrollPane scrollPane = new DScrollPane();
			scrollPane.setOpaque(false);
			scrollPane.getViewport().add(contentPanel);
			advanceSearchView.setContentPane(scrollPane);		
	}
	
} 

