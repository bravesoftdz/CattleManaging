package damdariar.gui.swing.model;

import java.util.HashMap;
import java.util.List;

import damdariar.beans.AbstractBean;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;

public class ApplicationDataModel {
	
	
	LoadDataModel  modelLoader;
	public ApplicationDataModel( LoadDataModel modelLoader) {
		super();
		this.modelLoader = modelLoader;
	}
	/**
	 * @return the beanMap
	 */
	public HashMap<Integer, AbstractBean> getBeanMap() {
		return modelLoader.getBeanMap();
	}

	/**
	 * @return the listOfFormData
	 */
	public List<Object> getListOfFormData() {
		return modelLoader.getListOfFormData();
	}


	public LoadDataModel getModelLoader() {
		return modelLoader;
	}
	
	public void addDataModelStatusListener(DataModelStatusListener modelListener){
		
		modelLoader.addDataModelStatusListener(modelListener);
	}

}
