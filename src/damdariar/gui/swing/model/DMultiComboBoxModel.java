package damdariar.gui.swing.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

import org.hibernate.Hibernate;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import damdariar.beans.AbstractBean;
import damdariar.hibernate.HibernateUtil;

public class DMultiComboBoxModel extends DefaultComboBoxModel{
	
	String propertyName;
	Class<?> cls;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**		 * The TreeSet which holds the combobox's data (ordered no duplicates)
	*/

//	private TreeSet<Object> values = null;
	private Map<Integer,AbstractBean> searchMap;
	
	public DMultiComboBoxModel(List<?> items){
		
		this(items,false,null);
	}
	
	public DMultiComboBoxModel(List<?> items,String propertyName){
		
		this(items,false,propertyName);
	}
	public DMultiComboBoxModel(List<?> items,boolean isMandatory,String propertyName)
	{
		
		super();
		try{
		this.propertyName = propertyName;
		if(!isMandatory)
			super.addElement(null);
//		this.values = new TreeSet<Object>();
		searchMap = new HashMap<Integer, AbstractBean>();
	/*	for (i = 0, c = items.size(); i < c; i++)
			values.add(items.get(i).toString());*/
		Iterator<?> it = items/*values*/.iterator();
		while (it.hasNext()){
			AbstractBean bean = (AbstractBean)it.next();
			addElement(bean/*.toString()*/);
			if(cls == null){
				cls = bean.getClass();
			}
			
			searchMap.put(bean.getID(), bean);
		}
		}catch(Exception e){
			
		}
	}
	public void addNewElement(Object object){
		addElement(object);
		searchMap.put(((AbstractBean)object).getID(), (AbstractBean)object);
		
	}
	public void addElement(Object object) {
		int size=getSize();
		for(int i=1;i<size;i++){
			if(propertyName != null || !"".equalsIgnoreCase(propertyName.trim()))
				if(AbstractBean.compareTo(((AbstractBean)getElementAt(i)),((AbstractBean)object),propertyName)>0) {
					insertElementAt(object, i);
					return;
				}
		}
		super.addElement(object);
	}
	
	public Integer searchElement(String property){
		ClassMetadata metadata = HibernateUtil.getMetaData(cls);
		Type type = metadata.getPropertyType(propertyName);
		if(cls != null && (type == Hibernate.INTEGER || type == Hibernate.STRING || type == Hibernate.FLOAT)){
			String name = cls.getSimpleName(); 
			if(type == Hibernate.STRING){
			  List<Integer> 	id= HibernateUtil.getSession().createQuery(" Select "+metadata.getIdentifierPropertyName()+" from " + name + " where "+propertyName + " =  '"+property+"'").list();
			  if(id != null && id.size() > 0)
				  return id.get(0);
			  else{
			   List<Integer> 	ids= HibernateUtil.getSession().createQuery(" Select "+metadata.getIdentifierPropertyName()+" from " + name + " where "+propertyName + " like '"+property+"%'").list();
			   if(ids != null && ids.size() > 0)
					  return ids.get(0);
			   else
				      return null;
			  }
			}
			else{
				List<Integer> 	id= HibernateUtil.getSession().createQuery(" Select "+metadata.getIdentifierPropertyName()+" from " + name + " where "+propertyName + " =  "+property+"").list();
				if(id != null && id.size() > 0)
					  return id.get(0);
			   else
				      return null;
				
			}
			
		}
		return null;
	}
	
	public DMultiComboBoxModel(final Object items[])
	{
		this(Arrays.asList(items),false,null);
	}
	
	public DMultiComboBoxModel(final Object items[],boolean isMandatory)
	{
		this(Arrays.asList(items),isMandatory,null);
	}

	/**
	 * @return the searchMap
	 */
	public Map<Integer, AbstractBean> getSearchMap() {
		return searchMap;
	}

	/**
	 * @param searchMap the searchMap to set
	 */
	public void setSearchMap(Map<Integer, AbstractBean> searchMap) {
		this.searchMap = searchMap;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
