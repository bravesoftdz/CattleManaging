package damdariar.gui.editor;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import damdariar.beans.AbstractBean;
import damdariar.beans.BeanMapUtil;
import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DButton;
import damdariar.gui.swing.DComboBox;
import damdariar.gui.swing.DTextField;
import damdariar.gui.swing.forms.FormConfig;
import damdariar.gui.swing.forms.listeners.PropertyDisplaySelectionListener;
import damdariar.gui.swing.model.DMultiComboBoxModel;
import damdariar.gui.swing.renderers.IdentifierListCellRenderer;
import damdariar.hibernate.HibernateUtil;
import damdariar.images.ImageUtil;
import damdariar.resource.ResourceUtil;
import damdariar.util.AdvanceKeyListener;

public class DMultiDisplayCombo extends JComponent implements EditorI,ActionListener,PropertyDisplaySelectionListener,PopupMenuListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DComboBox   dataCombo = new DComboBox();
	Map<Class<?>,List<?>>  dataMap = new HashMap<Class<?>, List<?>>();
	Class<?>  baseClass;
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;
	private Class<?> refererClass;
	
	DButton    propertySelectionButton ;
	String[] displayPropertis ;
	String[] propertyTranlations;
	MultiPropertySelectUtil propertyUtil;
	Loader    loader;
	
	public DMultiDisplayCombo(Class<?> refererClass,Class<?>  cls, PropertyMetaData metaData){
		this.refererClass = refererClass;
		this.baseClass = cls;
		this.propertyMetaData = metaData; 
		init(cls);
	}
	
	public DMultiDisplayCombo(Class<?>  cls){
		baseClass = cls;
		init(cls);
	}
	

	JPopupMenu searchPopUp;
	DTextField searchTextField ; 
	KeyListener advanceKeyListener;
	public void showSearchPopup(){
		if(searchPopUp == null){
			searchPopUp = new JPopupMenu();
			searchPopUp.setOpaque(false);
			searchTextField = new DTextField(16);
			searchPopUp.add(searchTextField);
			searchTextField.requestFocus();
			advanceKeyListener = new AdvanceKeyListener(searchTextField);
			searchTextField.addVetoableChangeListener(new VetoableChangeListener(){

				@Override
				public void vetoableChange(PropertyChangeEvent evt)
						throws PropertyVetoException {
					String value = (String)evt.getNewValue();
					if(value == null || value.trim().length() == 0 )
						return;
					ComboBoxModel model = dataCombo.getModel();
					if(model instanceof DMultiComboBoxModel){
						Integer id = ((DMultiComboBoxModel)model).searchElement((String) evt.getNewValue());
						if(id != null)
							setValue(id);
						else
							dataCombo.setSelectedIndex(0);
					}
				}
				
				
			});
		}
		
		Point location = dataCombo.getLocationOnScreen();
		searchPopUp.setLocation(location.x,location.y - 25);
		searchPopUp.setVisible(true);
	}
	int  lastSelectedIndex = -1; 
	public void init(Class<?>  cls){
/*		dataCombo.setEditable(true);
		dataCombo.addPopupMenuListener(this);*/
		setLayout(new BorderLayout());
		loadModel(cls);
		add(dataCombo,BorderLayout.CENTER);
		dataCombo.addKeyListener(new KeyAdapter(){
			
//			@Override
			public void handlekeyTyped(KeyEvent evt){
				showSearchPopup();
				if(!Character.isLetterOrDigit(evt.getKeyChar()) &&
				  evt.getKeyCode() == KeyEvent.VK_SPACE &&
				  evt.getKeyCode() == KeyEvent.VK_BACK_SPACE &&
				  evt.getKeyCode() == KeyEvent.VK_DELETE
				  ){
					evt.consume();
					return;
				}
				if(lastSelectedIndex == -1)
					lastSelectedIndex = dataCombo.getSelectedIndex();
				String text = searchTextField.getText();
				if(text == null)
					text = "";
				if(Character.isLetterOrDigit(evt.getKeyChar()))
					searchTextField.setText(text+evt.getKeyChar());
				else if(evt.getKeyCode() == KeyEvent.VK_SPACE )
					searchTextField.setText(text+" ");
				else if(evt.getKeyChar() == '\b' && searchTextField.getText().length() != 0)
					searchTextField.setText(searchTextField.getText().substring(0,searchTextField.getText().length()-1));
				else
					searchTextField.setText("");
				advanceKeyListener.keyTyped(evt);
			}
			
			@Override
			public void keyReleased(KeyEvent evt){
			     if(searchPopUp != null && searchPopUp.isVisible() && (evt.getKeyCode() == KeyEvent.VK_ESCAPE ||
			    	evt.getKeyCode() == KeyEvent.VK_ENTER))
			     {
			    	 evt.consume();
			    	 searchTextField.setText("");
			    	 searchPopUp.setVisible(false);
			    	 if(evt.getKeyCode() == KeyEvent.VK_ESCAPE )//&&
//			    	 lastSelectedIndex != dataCombo.getSelectedIndex() && lastSelectedIndex != -1)
//			    	 {
			    		 dataCombo.setSelectedIndex(lastSelectedIndex);
//			    	 }
			    	 lastSelectedIndex = -1;
			     }
			     else {
			    	 handlekeyTyped(evt);
			     }
			}
		});
		dataCombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					fireVetoableChange(propertyMetaData.getPropertyName(), null, getValue());
				}catch(Exception ee){
					
				}
			}
			
		});
		try{
			dataCombo.removeKeyListener(dataCombo.getKeyListeners()[0]);
		}catch(Exception e){
			
		}
		
	
		
	}
	
	public void addMouseListener(final MouseListener mouseListener){
		if(dataCombo != null)
			dataCombo.addMouseListener(mouseListener);
	}
	public void loadModel (Class<?> cls){
		
		displayPropertis = ResourceUtil.getAllDisplayedProperties(cls.getSimpleName());
		propertyTranlations = new String[displayPropertis.length];
		for (int i = 0; i < propertyTranlations.length; i++) {
			propertyTranlations[i]  = ResourceUtil.getPropertyTranslation(cls.getSimpleName(), displayPropertis[i]);
		}
		dataCombo.setRenderer(new IdentifierListCellRenderer(displayPropertis[0]));
		if(displayPropertis.length > 1)
		{
			propertySelectionButton = new DButton();
			propertySelectionButton.setFocusable(false);
			propertySelectionButton.setIcon(ImageUtil.getImageIcon("down.gif"));
			propertySelectionButton.setMouseOverIcon(ImageUtil.getImageIcon("down24_over.gif"));
			add(propertySelectionButton,BorderLayout.LINE_END);
			propertyUtil = new MultiPropertySelectUtil(propertySelectionButton,this,displayPropertis,propertyTranlations);
			propertySelectionButton.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					propertyUtil.showPropertySelectionWindow(e.getXOnScreen(),e.getYOnScreen());
				}});
		}
		
		
		(loader = new Loader(cls,dataMap,dataCombo,propertyMetaData)).start();
			
	}
	
	
		public Integer getID(){
			
			if(dataCombo.getSelectedIndex() < 1)
				return null;
			return ((AbstractBean)(dataCombo.getSelectedItem())).getID();
			

		}
		
		@Override
		protected void finalize() throws Throwable {
			if(propertyUtil != null)
			{
				propertyUtil.dispose();
			}
		}

		public String getJoinClause(){
			if(dataCombo.getSelectedIndex() < 1 || (refererClass.equals(baseClass)))
				return null;
			
		
			IdentifierListCellRenderer renderer = (IdentifierListCellRenderer) dataCombo.getRenderer();
			return getJoinClause(baseClass,refererClass,renderer.getPropertyName());
			
		}
		
		public Object  getForeignValue(){
			if(dataCombo.getSelectedIndex() < 1)
				return null;
			AbstractBean  bean   = ((AbstractBean)(dataCombo.getSelectedItem()));
			IdentifierListCellRenderer renderer = (IdentifierListCellRenderer) dataCombo.getRenderer();
			return AbstractBean.getPropertyValue(bean, renderer.getPropertyName());
		}
		
		public String  getColumnName(){
			String tableName = AbstractBean.getTableName(baseClass);
			IdentifierListCellRenderer renderer = (IdentifierListCellRenderer) dataCombo.getRenderer();
			return " "+tableName + "."+AbstractBean.getColumnName(renderer.getPropertyName())+" ";
			
		}
		public String  getJoinClause(Class<?> cls1 ,Class<?> cls2,String propertyName){
			
			
           
			String  tableName1    =     AbstractBean.getTableName(cls1);
			String  tableName2    = 	AbstractBean.getTableName(cls2);
			String  columnName = 		AbstractBean.getColumnName(getEditorProperty().getPropertyName());
			String  keyColumnName =     AbstractBean.getKeyColumnName(cls1);
			
			
			String join = " inner join " + tableName1 +"  "+ tableName1+
			              "  on " + tableName1 + "." + keyColumnName +  "=" +
			                       tableName2 + "." + columnName + "  ";  

			return join;
		}
		
		
		public void setEnabled(boolean enabled){
			
			dataCombo.setEnabled(enabled);
			if(propertySelectionButton != null)
				propertySelectionButton.setEnabled(enabled);
		}
	
	public Object getValue() {
		return getID();
	}

	public void setValue(Object value) {
		if(value == null && dataCombo.getModel() instanceof DMultiComboBoxModel ){
			dataCombo.setSelectedIndex(0);
			return;
		}
		if(loader != null )
			try{
			  loader.join();
			}catch(Exception e){
				
			}
		DMultiComboBoxModel model = (DMultiComboBoxModel)dataCombo.getModel();
		if(model != null && model.getSearchMap() != null && value != null){
			AbstractBean bean = model.getSearchMap().get(value);
			if(bean != null)
				dataCombo.setSelectedItem(bean);
			else 
				dataCombo.setSelectedIndex(0);
		}
		else 
			dataCombo.setSelectedIndex(0);
	}

	public PropertyMetaData getEditorProperty() {
		return this.propertyMetaData;
	}

	public void setEditorProperty(PropertyMetaData property) {
		this.propertyMetaData = property;
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		getID();
		
	}

	public void refresh() {
		Integer ID =getID();
		removeAll();
		reInitializationCollections();
		init(baseClass);
		setValue(ID);
		
	}
	
	
	public void addNewElement(Object dataObject){
		ComboBoxModel model = dataCombo.getModel();
		if(model != null && model instanceof DMultiComboBoxModel)
			((DMultiComboBoxModel)model).addNewElement(dataObject);
	}
	public void reInitializationCollections(){
		dataMap.clear();

	}
	
	@Override
	public int getEditorSequence() {
		// TODO Auto-generated method stub
		return sequenceNo;
	}

	@Override
	public void setEditorSequence(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public void propertySelectionChanged(String propertyName) {
		 dataCombo.setRenderer(new IdentifierListCellRenderer(propertyName));
		((DMultiComboBoxModel)dataCombo.getModel()).setPropertyName(propertyName);
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		dataCombo.setEditable(true);
		
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		dataCombo.setEditable(false);
		
	}
	






}


class  DisplayPropertyListener implements ActionListener{
	
	DisplayPropertyListener(){
		
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}  

class  DataComboFocusListener implements FocusListener{
	DComboBox displayedPropertiesCombo;
	String[] displayPropertis;
	
	
	DataComboFocusListener(){
		
		
	}

	public DataComboFocusListener(DComboBox displayedPropertiesCombo,
			String[] displayPropertis) {
		// TODO Auto-generated constructor stub
		this.displayedPropertiesCombo = displayedPropertiesCombo;
		this.displayPropertis = displayPropertis;
		
	}

	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}

class Loader extends Thread {
	
	Map <Class<?>,List<?>>  dataMap;
	Class<?> classType;
	DComboBox  dataCombo;
	private PropertyMetaData propertyMetaData;
	
	public Loader(Class<?> cls,Map <Class<?>,List<?>>  dataMap,DComboBox  dataCombo, PropertyMetaData propertyMetaData){
		classType = cls;
		this.dataMap = dataMap;
		this.dataCombo = dataCombo;
		this.propertyMetaData = propertyMetaData;
	}
		
	public void run() {
		try{
		dataMap.put(classType,HibernateUtil.getComboBoxModel(classType,FormConfig.getComboBoxFilter(classType, propertyMetaData.getPropertyName())));
		dataCombo.setModel(new DMultiComboBoxModel(dataMap.get(classType),((IdentifierListCellRenderer)dataCombo.getRenderer()).getPropertyName()));
		}catch(Exception e){
			
		}
	}
} 


class SubLoader extends Thread {
	
	Map <Class<?>,List<?>>  dataMap;
	List<?> beans;
	String propertyName;
	
	public SubLoader(List<?> beans,
	String propertyName,Map <Class<?>,List<?>>  dataMap){
        this.beans = beans;
        this.propertyName = propertyName;
		this.dataMap = dataMap;		
	}
		
	public void run() {
		
		BeanMapUtil bmu = AbstractBean.getIdentifiersMap(beans, propertyName);
		dataMap.put(bmu.getClassType(),new ArrayList<Object>(bmu.getBeansCollection()));
	}
} 



class  DisplayComboPropertyPair{
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((DisplayComboPropertyPair)obj).displayCombo.equals(this.displayCombo) && 
			   ((DisplayComboPropertyPair)obj).propertyName.equals(this.propertyName) &&
			   ((DisplayComboPropertyPair)obj).classType.equals(this.classType);
	}
	public DisplayComboPropertyPair(String propertyName,
			DComboBox displayCombo) {
		this.propertyName = propertyName;
		this.displayCombo = displayCombo;
	}
	
	public DisplayComboPropertyPair(String propertyName,
			DComboBox displayCombo,Class<?> classType) {
		this.propertyName = propertyName;
		this.displayCombo = displayCombo;
		this.classType = classType;
	}
	DComboBox   displayCombo;
	String      propertyName;
	Class<?> classType;
	/**
	 * @return the displayCombo
	 */
	public DComboBox getDisplayCombo() {
		return displayCombo;
	}
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * @return the classType
	 */
	public Class<?> getClassType() {
		return classType;
	}
}