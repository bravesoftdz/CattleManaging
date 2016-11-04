package damdariar.gui.swing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;

import damdariar.beans.AbstractBean;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.hibernate.HibernateUtil;

public class LoadDataModel extends Thread {



	private DTableModel model;
	private List<DataModelStatusListener> modelStatusListeners;
	private Query query;
	private String criteria;

	public LoadDataModel(DTableModel model) {
		this.model = model;
	}
	
	public LoadDataModel(DTableModel model,Query query) {
		this.model = model;
		this.query = query;
	}

	
	public LoadDataModel(DTableModel model,Query query,String criteria) {
		this.model = model;
		this.query = query;
		this.criteria = criteria;
	}
	@SuppressWarnings("unchecked")
	public void run() {
		fireStatus(DataModelStatusListener.MODEL_LOADING_STARTED);
		model.beanMap = new HashMap<Integer, AbstractBean>();
		model.listOfObjs = query != null ? executeQuery() : (List<Object>) HibernateUtil.getListOfObjects(model.classType,criteria);
		model.fireTableDataChanged();
		fireStatus(DataModelStatusListener.MODEL_LOADING_FINISHED);
		if(query != null)
			 HibernateUtil.closeSession(); 
	}
	
	public List<Object>  executeQuery(){
		List<Object> list = new ArrayList<Object>();
		try{
			list = query.list();
		}catch(Exception e){
			
		}
		return list;
	}
	
	public void fireStatus(int statusType){
		
		if(modelStatusListeners != null)
			for(int i=0;i<modelStatusListeners.size();i++)
			   modelStatusListeners.get(i).statusChanged(statusType, model.listOfObjs);
	}

	
	public void addDataModelStatusListener(DataModelStatusListener modelListener){
		if(modelStatusListeners == null)
			modelStatusListeners = new ArrayList<DataModelStatusListener>();
		modelStatusListeners.add(modelListener); 
	}
	
	public void setDataModelStatusListener(List<DataModelStatusListener> modelListeners){
		
		modelStatusListeners = modelListeners;
	}

	public List<Object> getListOfFormData() {
		return model.listOfObjs;
	}

	public HashMap<Integer, AbstractBean> getBeanMap() {
		return model.beanMap;
	}

	
	
}