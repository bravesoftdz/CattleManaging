package damdariar.gui.property;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import damdariar.beans.CDefaultValues;
import damdariar.hibernate.HibernateUtil;
import damdariar.util.Util;

public class GUIDefaultValues {
	
	public static Map<String,Object>       defaultValuesMap  = new HashMap<String, Object>();
	
	
	public static void loadDefaultValue(){
		String  df = "No Default Value Fount";
	}
	
	public static void loadDefaultValues(){
		
		List<CDefaultValues> defaultValues  =   (List<CDefaultValues>) HibernateUtil.getListOfObjects(CDefaultValues.class);
		for(CDefaultValues defValue:defaultValues){
			String key = getKey(defValue.getClassName(), defValue.getEditorName());
			Object value  = defValue.getDefaultValue();
			if(value != null)
				value = getValue((Blob) value);
			defaultValuesMap.put(key,value);	
		}
		
		
		
	}
	
	private static Object getValue(Blob value) {
		
		try{
			ByteArrayInputStream bais = new ByteArrayInputStream(Util.toByteArray(value));
	        ObjectInputStream ois = new ObjectInputStream(bais);
	        Object obj = ois.readObject();
	        ois.close();
	        return obj;
		}catch(Exception e){
			return null;
		}
	}
	
	
	public static void put(String className,String editorName,Object value){
		defaultValuesMap.put(getKey(className,editorName),value);
	}

	public static Object get(String className,String editorName){
		return defaultValuesMap.get(getKey(className,editorName));
	}	
	public static String getKey(String className,String editorName){
		return className+"||"+editorName;
	}

	public static void loadDefaultValue(Class<?>[] classes) {
		// TODO Auto-generated method stub
		
	}
   
}
