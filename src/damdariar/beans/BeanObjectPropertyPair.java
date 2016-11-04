package damdariar.beans;

public class BeanObjectPropertyPair {
	
	PropertyMetaData  propertyMetaData;
	AbstractBean      bean;
	
	
	public BeanObjectPropertyPair(PropertyMetaData propertyMetaData,
			AbstractBean      bean) {
		super();
		this.propertyMetaData = propertyMetaData;
		this.bean = bean;
	}
	/**
	 * @return the propertyMetaData
	 */
	public PropertyMetaData getPropertyMetaData() {
		return propertyMetaData;
	}
	/**
	 * @param propertyMetaData the propertyMetaData to set
	 */
	public void setPropertyMetaData(PropertyMetaData propertyMetaData) {
		this.propertyMetaData = propertyMetaData;
	}
	/**
	 * @return the bean
	 */
	public AbstractBean getBean() {
		return bean;
	}
	/**
	 * @param bean the bean to set
	 */
	public void setBean(AbstractBean bean) {
		this.bean = bean;
	}

}
