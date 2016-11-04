package damdariar.beans;

import java.util.HashMap;
import java.util.Map;

public class IdentifierMap<K,V>  {

	 Map<String,V>  identifierMap = new  HashMap<String, V>();


	public V put(Class<?> cls,K key, V value) {
		// TODO Auto-generated method stub
		return identifierMap.put(cls.getSimpleName()+"_"+key, value);
	}
	
	public V get(Class<?> cls,Object key) {
		// TODO Auto-generated method stub
		return identifierMap.get(cls.getSimpleName()+"_"+key);
		
	}
	
	public boolean containsKey(Class<?> cls,Object key){
		return identifierMap.containsKey(cls.getSimpleName()+"_"+key);
		
		
	}
	
	public boolean containsValue(Object value){
		return identifierMap.containsValue(value);
	}
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
