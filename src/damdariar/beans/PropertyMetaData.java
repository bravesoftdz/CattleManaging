package damdariar.beans;

import org.hibernate.type.Type;


public class PropertyMetaData {
	
	String  propertyName;
	String  propertyTranslation;
	boolean isMandatoory;
	Class<?>  beanClass;
	Type    type;
	
	public PropertyMetaData(String propertyName, String propertyTranslation) {
		super();
		this.propertyName = propertyName;
		this.propertyTranslation = propertyTranslation;
	}
	public PropertyMetaData(String propertyName, String propertyTranslation,
			boolean isMandatoory, Class<?> beanClass, Type type) {
		super();
		this.propertyName = propertyName;
		this.propertyTranslation = propertyTranslation;
		this.isMandatoory = isMandatoory;
		this.beanClass = beanClass;
		this.type = type;
	}
	public PropertyMetaData(String propertyName, String propertyTranslation,
			boolean isMandatoory, Class<?> beanClass) {
		super();
		this.propertyName = propertyName;
		this.propertyTranslation = propertyTranslation;
		this.isMandatoory = isMandatoory;
		this.beanClass = beanClass;
	}
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	/**
	 * @return the propertyTranslation
	 */
	public String getPropertyTranslation() {
		return propertyTranslation;
	}
	/**
	 * @param propertyTranslation the propertyTranslation to set
	 */
	public void setPropertyTranslation(String propertyTranslation) {
		this.propertyTranslation = propertyTranslation;
	}
	/**
	 * @return the isMandatoory
	 */
	public boolean isMandatoory() {
		return isMandatoory;
	}
	/**
	 * @param isMandatoory the isMandatoory to set
	 */
	public void setMandatoory(boolean isMandatoory) {
		this.isMandatoory = isMandatoory;
	}
	/**
	 * @return the beanClass
	 */
	public Class<?> getBeanClass() {
		return beanClass;
	}
	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	public String toString(){
		if(propertyTranslation != null && !"".equalsIgnoreCase(propertyTranslation.trim()))
			return propertyTranslation;
		else
			return propertyName;
	}
	
	
	
}
