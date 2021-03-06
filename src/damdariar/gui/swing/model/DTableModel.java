package damdariar.gui.swing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import damdariar.beans.AbstractBean;
import damdariar.gui.editor.DateEditor;
import damdariar.gui.editor.MultiDateEditor;
import damdariar.gui.swing.forms.FormUtility;
import damdariar.gui.swing.forms.editorvisibility.GridEditorVisibility;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.hibernate.HibernateUtil;
import damdariar.resource.ResourceUtil;

public class DTableModel extends DefaultTableModel{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int[]    realIndex;
	public int[] getRealIndex() {
		return realIndex;
	}

	public void setRealIndex(int[] realIndex) {
		this.realIndex = realIndex;
	}

	String[] propertyNames;
	String[] propertyTranlations;
	String identifierName;
	HashMap<Integer, AbstractBean> beanMap;
	List<Object> listOfObjs;	
	Class<?> classType;
	
	@SuppressWarnings("unused")
	String identifierTranslation;
	@SuppressWarnings("unused")
	Type identifierType;
	@SuppressWarnings("unused")
	Type[] types;
	LoadDataModel modelLoader;
	LoadDataModel restoredModel;
	HashMap<Integer, AbstractBean> restorBeanMap;
	List<Object> restoreListOfObjs;


	 JTable table;

	 String linkedProperty;	
	
	public DTableModel(){
		
	}
	public DTableModel(Class<?> cls){
		init(cls,null,null,null);
	
	}
	
	public DTableModel(Class<?> cls,List<DataModelStatusListener> dataModelStatusListeners){
		init(cls,dataModelStatusListeners,null,null);
	
	}
	
	public DTableModel(Class<?> cls,List<DataModelStatusListener> dataModelStatusListeners,String criteria){
		this(cls,dataModelStatusListeners,criteria,"");
	
	
	}
	
	public DTableModel(Class<?> cls,List<DataModelStatusListener> dataModelStatusListeners,String criteria,String linkedProperty){
		this.linkedProperty = linkedProperty;
		init(cls,dataModelStatusListeners,null,criteria);
	
	
	}
	

	
	public void init(Class<?> cls,List<DataModelStatusListener> dataModelStatusListeners,Query query,String criteria){
		
		this.classType = cls;
		modelLoader = new LoadDataModel(this,query,criteria);
		modelLoader.setDataModelStatusListener(dataModelStatusListeners);
		modelLoader.start();
		init(cls);
     	Vector<String> columnNames = new Vector<String>();
		for(int i = 0 ; i < propertyNames.length ; i ++){
			columnNames.add(propertyTranlations[i]);
		}
		super.setColumnIdentifiers(columnNames);
	}
	

	public void remove(AbstractBean beanObj){
		AbstractBean beanTobeDeleted = beanMap.get(beanObj.getID());
		int deletedRowIndex = listOfObjs.indexOf(beanTobeDeleted);
		listOfObjs.remove(beanTobeDeleted);
		beanMap.remove(beanTobeDeleted.getID());
		try{
			fireTableRowsDeleted(deletedRowIndex, deletedRowIndex);
		}catch(Exception e)
		{
			
		}
	}
	
	public void removeRows(List<AbstractBean>  toBeDeletedBeans){
		for(AbstractBean beanObj :toBeDeletedBeans)
		{
			remove(beanObj);
			HibernateUtil.delete(beanObj);
		}
	}
	
	public void add(AbstractBean beanObj){
		try{
			if(beanMap.containsKey(beanObj.getID())){
				int updatedRowIndex = listOfObjs.indexOf(beanMap.get(beanObj.getID()));
			
				fireTableRowsUpdated(updatedRowIndex, updatedRowIndex);
				
			}
			else{
				listOfObjs.add(beanObj);
				beanMap.put(beanObj.getID(),beanObj);
				fireTableRowsInserted(listOfObjs.size()-1,listOfObjs.size()-1);
				
			}
		}catch(Exception e){
			
		}
	}

	public void init(Class<?> cls){
		GridEditorVisibility visibilityObj =FormUtility.getGridEditorVisibilityClass(cls);
		ClassMetadata metaData = HibernateUtil.getMetaData(cls);
		propertyNames = metaData.getPropertyNames();
		List<String> properties = new ArrayList<String>();
		for(int i = 0;i < propertyNames.length ; i++){
			if(!visibilityObj.isVisible(propertyNames[i]) || propertyNames[i].equalsIgnoreCase(linkedProperty))
					continue;
			properties.add(propertyNames[i]);	
		}
		String[] temp=new String[properties.size()];
		properties.toArray(temp);
		propertyNames = temp;
		realIndex = new int[temp.length];
		
		propertyTranlations = new String[propertyNames.length];
		types = metaData.getPropertyTypes();
		for (int i = 0; i < propertyTranlations.length; i++) {
			propertyTranlations[i]  = ResourceUtil.getPropertyTranslation(cls.getSimpleName(), propertyNames[i]);
			realIndex[i] = i;
		}
		
		identifierName = metaData.getIdentifierPropertyName();
		identifierType = metaData.getIdentifierType();
		identifierTranslation = ResourceUtil.getPropertyTranslation(cls.getSimpleName(),identifierName);
	}
	
	
	@Override
	public Object getValueAt(int row, int column) {
		 if(row >= listOfObjs.size())
			return null;
		 AbstractBean beanObj = (AbstractBean)listOfObjs.get(row);
		 if(!beanMap.containsKey(beanObj.getID()))
			 beanMap.put(beanObj.getID(), beanObj);
		 
		 Object value  = AbstractBean.getPropertyValue(beanObj, propertyNames[/*realIndex[*/column/*]*/]);
		 if(value != null){
			 if(value instanceof String && ((String)value).startsWith(MultiDateEditor.SIGNATURE))
			     return MultiDateEditor.getDisplay((String)value);
			 else if(value instanceof Date)
			     return DateEditor.getDisplay((Date)value);
		 }
		 if(isIdentifier(column))
			 return getDisplayPropertyForID(row,column,(Integer)value);
		 else
			 return value;
		 
	}
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public int getColumnCount() {
		return propertyNames.length;
	}

	@Override
	public int getRowCount() {
		if(listOfObjs != null)
			return listOfObjs.size();
		return 0;
	}
	
	
	public AbstractBean getBeanByRow(int row){
		
		AbstractBean beanObj = (AbstractBean)listOfObjs.get(row);
		return beanMap.get(beanObj.getID());
	}
	
	public Integer getModelRow(AbstractBean beanObj){
		return listOfObjs.indexOf(beanObj);
	}
	

	public void addDataModelStatusListener(DataModelStatusListener modelListener){
		if(modelLoader != null)
			modelLoader.addDataModelStatusListener(modelListener);
	}

	/**
	 * @return the beanMap
	 */
	public HashMap<Integer, AbstractBean> getBeanMap() {
		return beanMap;
	}

	/**
	 * @return the listOfObjs
	 */
	public List<Object> getListOfObjs() {
		return listOfObjs;
	}

	public LoadDataModel getModelLoader() {
		return modelLoader;
	}

	public void changeModel(Query query,DataModelStatusListener modelStatusListener) {
		if(restoredModel == null){
			restoredModel = modelLoader;
			restorBeanMap = beanMap;
			restoreListOfObjs = listOfObjs;
		}
		modelLoader = new LoadDataModel(this,query);
		modelLoader.addDataModelStatusListener(modelStatusListener);
		modelLoader.start();
	} 
	
	public void restoreOriginalModel(){
		
		if(restoredModel != null)
		{	
			
			restoredModel.fireStatus(DataModelStatusListener.MODEL_LOADING_STARTED);
			beanMap =restorBeanMap;
			listOfObjs = restoreListOfObjs;
			restoredModel.fireStatus(DataModelStatusListener.MODEL_LOADING_FINISHED);
			modelLoader = restoredModel;
			fireTableDataChanged();
			restoredModel = null;
			
		}
		
			
	}

	public boolean isIdentifier(int column){
		return AbstractBean.isIdentifier(classType, propertyNames[column]);
	}
	
	public Class<?> getIdentifierClass(int column){
		return AbstractBean.getIdentifierClass(classType, propertyNames[column]);
	}
	
	public Object getDisplayProperty(int row,int column){
		
		Integer id = (Integer) getValueAt(row, column);
		if(id != null){
			Class<?> cls = getIdentifierClass(column);
			String propertyName = ResourceUtil.getDisplayedProperty(cls.getSimpleName());
			if(propertyName != null && !"".equalsIgnoreCase(propertyName.trim()))
			{
				AbstractBean bean = (AbstractBean) HibernateUtil.getObject(cls, id);
				return AbstractBean.getPropertyValue(bean, propertyName);
			}
			else
				return id;
		}
		return "";
	}
	
	public Object getDisplayPropertyForID(int row,int column,Integer id){
		
		if(id != null){
			Class<?> cls = getIdentifierClass(column);
			String propertyName = ResourceUtil.getDisplayedProperty(cls.getSimpleName());
			if(propertyName != null && !"".equalsIgnoreCase(propertyName.trim()))
			{
				AbstractBean bean = (AbstractBean) HibernateUtil.getObject(cls, id);
				return AbstractBean.getPropertyValue(bean, propertyName);
			}
			else
				return id;
		}
		return "";
	}

	public String[] getPropertyNames() {
		return propertyNames;
	}

	public boolean isAllData() {
		if(restoredModel == null){
			return true;
		}
		if(restoredModel != null && restoredModel.equals(modelLoader))
			return true;
		return false;
	}

	public Class<?> getClassType() {
		return classType;
	}
}

