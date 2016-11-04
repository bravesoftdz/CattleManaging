package damdariar.beans;

import java.util.Collection;
import java.util.Map;

public class BeanMapUtil {

	
	Map<Integer,AbstractBean>  idToBeanMap;
	Class<?>            classType;
	
	
	public AbstractBean[]   getBeans(){
		
		if(idToBeanMap != null)
			return (AbstractBean[]) idToBeanMap.values().toArray();
			
		return null;
	}
	
	public Collection<AbstractBean>   getBeansCollection(){
		
		if(idToBeanMap != null)
			return  idToBeanMap.values();
			
		return null;
	}


	public BeanMapUtil(Map<Integer, AbstractBean> idToBeanMap) {
		super();
		this.idToBeanMap = idToBeanMap;
	}


	/**
	 * @return the classType
	 */
	public Class<?> getClassType() {
		return classType;
	}


	/**
	 * @param classType the classType to set
	 */
	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	public BeanMapUtil(Map<Integer, AbstractBean> idToBeanMap,
			 Class<?> classType) {
		super();
		this.idToBeanMap = idToBeanMap;
		this.classType = classType;
	}
	
	
}
