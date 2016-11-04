package damdariar.gui.swing.forms.gridform;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import damdariar.beans.AbstractBean;
import damdariar.gui.Main;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DRotatedButton;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.forms.listeners.BeanActionListener;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.gui.swing.forms.listeners.GridRowSelectionListener;
import damdariar.gui.swing.forms.listeners.TreeRowSelectionListener;
import damdariar.gui.swing.layout.GridLayoutManager;
import damdariar.gui.swing.model.ApplicationDataModel;
import damdariar.gui.swing.search.AdvanceSearch;

public class FormBottomComponent extends DScrollPane implements BeanActionListener, TreeRowSelectionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FormGrid grid;
	AdvanceSearch  search;
	DPanel         lowerPane;
	DScrollPane scrollPane;
	
	String showSearchButton = " \u062c\u0633\u062a\u062c\u0648\u06cc \u067e\u06cc\u0634\u0631\u0641\u062a\u0647";
	String hideSearchButton = " \u067e\u0646\u0647\u0627\u0646 \u06a9\u0631\u062f\u0646 \u062c\u0633\u062a\u062c\u0648\u06cc \u067e\u06cc\u0634\u0631\u0641\u062a\u0647";
	DRotatedButton  searchButton = new DRotatedButton(showSearchButton,true);
	
	int searchButtonWidth = 26;
	int searchButtonHeight = 70;
	
	
	static int  xStep  = 10;
	static int  yStep  = 10;
	static int  width =  AdvanceSearch.width;
	static int  height = AdvanceSearch.height;
	
	
	GridLayoutManager layoutManager;
	private Class<?> classType;
	private String linkedProperty;
	
	public FormBottomComponent(Class<?> classType){
		build(classType,null,null);
		
	}
	
	public FormBottomComponent(Class<?> classType,String criteria){
		build(classType,null,criteria);
		
	}
	public FormBottomComponent(Class<?> classType,String criteria,String linkedProperty){
		this.linkedProperty = linkedProperty;
		build(classType,null,criteria);
		
	}
	
	
	public FormBottomComponent(Class<?> classType,List<DataModelStatusListener> dataModelStatusListeners){
		build(classType,dataModelStatusListeners,null);
		
	}
	
	public FormBottomComponent(Class<?> classType,List<DataModelStatusListener> dataModelStatusListeners,String criteria){
		build(classType,dataModelStatusListeners,criteria);
		
	}
	
	public void  build(Class<?> classType,List<DataModelStatusListener> dataModelStatusListeners,String criteria){
		this.classType = classType;
		lowerPane = new DPanel(new BorderLayout());
//		layoutManager = new GridLayoutManager(lowerPane);
		grid = new FormGrid(classType,dataModelStatusListeners,criteria,linkedProperty);
		search = new AdvanceSearch(classType,linkedProperty);
		search.setAlwaysOnTop(true);
		search.addSearchListener(grid);
		search.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				searchButton.setText(showSearchButton);
			}});
//		search.setResizable(false);
		addSearchButton();
		addGrid();
		getViewport().setView(lowerPane);
		getViewport().setOpaque(false);
		boolean isTreeNeeded = AbstractBean.isTreeNeeded(classType);
		grid.setPreferredSize(new Dimension(isTreeNeeded ? (Main.PREFERRED_SIZE.width-184) : (Main.PREFERRED_SIZE.width-49) ,300));
		/*Preferred*/
		
//		lowerPane.setPreferredSize(new Dimension(isTreeNeeded ? (Main.PREFERRED_SIZE.width/*-193*/) : (Main.PREFERRED_SIZE.width/*-59*/) ,300));
//		setPreferredSize(new Dimension(isTreeNeeded ? (Main.PREFERRED_SIZE.width/*-193*/) : (Main.PREFERRED_SIZE.width/*-59*/) ,300));
		
	}
	

	public void addSearchButton(){
		
		
	/*	layoutManager.getConstriant().gridx = 0;
		layoutManager.getConstriant().gridy = 0;
		layoutManager.getConstriant().gridwidth  = 1;
		layoutManager.getConstriant().gridheight = 2;
		layoutManager.getConstriant().weightx = 0;
		layoutManager.getConstriant().fill = GridBagConstraints.VERTICAL;*/
		searchButton.setPreferredSize(new Dimension(26,150));
		/*layoutManager.add(searchButton);*/
		DPanel verticalPanel = new DPanel();
		verticalPanel.add(searchButton);
		lowerPane.add(verticalPanel,BorderLayout.EAST);
		searchButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(searchButton.getText().equals(showSearchButton)){
					searchButton.setText(hideSearchButton);
					showCenterScreen();
				}
				else{
					search.setVisible(false);
					searchButton.setText(showSearchButton);
					
				}
			}
			
		});
		
		
	}
	
	public void addGrid(){
		
		
		lowerPane.add(grid,BorderLayout.CENTER);
		/*layoutManager.getConstriant().gridx = 3;
		layoutManager.getConstriant().weightx = 1;
		layoutManager.getConstriant().gridy = 0;
		layoutManager.getConstriant().gridwidth  = 4;
		layoutManager.getConstriant().gridheight = 4;
		layoutManager.getConstriant().fill = GridBagConstraints.HORIZONTAL;
		grid.setPreferredSize(new Dimension(325,300));
		layoutManager.add(grid);*/
		
		
	}
	
	
	
	public  void showCenterScreen ()
	{
		search.pack();
		search.setSize(width,0);
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension wSize = new Dimension(width,height);
		search.setLocation(((sSize.width-wSize.width)/2), ((sSize.height-wSize.height)/2));
		search.toFront();
		search.setVisible(true);
		(new Timer(this)).start();
	}	//	showCenterScreen
	
	
	

	public void addGridRowSelectionListener(GridRowSelectionListener gridRowSelectionListener){
		grid.addGridRowSelectionListener(gridRowSelectionListener);
	}


	public void delete(AbstractBean bean) {
		// TODO Auto-generated method stub
		if(grid != null)
			grid.delete(bean);
	}

	public void save(AbstractBean bean) {
		if(grid != null)
			grid.save(bean);
	}
	
	public ApplicationDataModel getApplicationDataModel(){
		
		return grid.getApplicationDataModel();
	}


	@Override
	public void treeRowSelectionChanged(AbstractBean beanObj) {
		grid.treeRowSelectionChanged(beanObj);
		
	}

	public void printAction() {
		grid.printAction();
		
	}

	public void dispose() {
		if(search != null){
			search.setVisible(false);
			search.dispose();
		}
		
	}


	

}

class Timer extends Thread {

	private FormBottomComponent bottomComponent;
 	Timer(FormBottomComponent bottomComponent){
		
		this.bottomComponent = bottomComponent;
	}
	public void run(){
		try{
			while(bottomComponent.search.getHeight() < bottomComponent.height){
				bottomComponent.search.setSize(bottomComponent.width  ,
						bottomComponent.search.getSize().height + bottomComponent.yStep);
			 sleep(10);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

