package damdariar.resource;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceUtil {
	
	public static ResourceBundle applicationBundle;
	
	public static ResourceBundle getResourceManager(){
		if(applicationBundle != null)
			return applicationBundle;
		 String resourceName = ResourceUtil.class.getPackage().getName()+ ".damdariar";		 
		 applicationBundle = ResourceBundle.getBundle(resourceName, new Locale("fa","IR"));
		 return applicationBundle;
		
		
	}
	
	public  static ResourceBundle getResourceBundle(String name){
		 String resourceName = ResourceUtil.class.getPackage().getName()+ "."+name;		 
		 ResourceBundle bundle = ResourceBundle.getBundle(resourceName, new Locale("fa","IR"));
		 return bundle;
	}
	
	public static String   getString(String propertyName){
		if(applicationBundle == null)
			getResourceManager();
		return applicationBundle.getString(propertyName);
		
	}
	
	public static String getPropertyTranslation(String resourceName,
			String propertyName){
		
		try{
			
			return getResourceBundle(resourceName).getString(propertyName);
			
		}catch(Exception e){
			
		}
		return propertyName;
		
	}
	
	public static String getPropertyEditorPreference(String resourceName,
			String propertyName){
		
		try{
		String pref = getResourceBundle(resourceName).getString("EDITOR_"+propertyName);
		if(pref != null && !"".equalsIgnoreCase(pref.trim())){}
			return pref.trim();
			
		   
		}catch(Exception e){
//			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static int getEditorSequence(String resourceName,
			String propertyName){
		
		try{
			String pref = getResourceBundle(resourceName).getString("SEQUENCE_"+propertyName);
			if(pref != null && !"".equalsIgnoreCase(pref.trim())){}
				return Integer.parseInt(pref);
		}catch(Exception e){
		}		
		return  999999999;
		
	}


	public static String getDisplayedProperty(String simpleName,
			String propertyName) {
		// TODO Auto-generated method stub
		return getResourceBundle(simpleName).getString(propertyName+"_table_alias");
		}
	
	public static String getDisplayedProperty(String simpleName) {
		try{
			return getResourceBundle(simpleName).getString("DisplayedPropertyForIdentifier");
		}catch(Exception e){
		}
		return null;
	}
	
	public static String getTreeName(String simpleName) {
		try{
			return getResourceBundle(simpleName).getString("TREENAME");
		}catch(Exception e){
		}
		return "No Tree Name Found";
	}
	
	public static String[] getAllDisplayedProperties(String simpleName) {
		// TODO Auto-generated method stub
		ArrayList<String>  listOfPropertyNames = new ArrayList<String>();
		
	    for(int i = 1; i <= 10 ; i++){
			try{
			String prn =  getResourceBundle(simpleName).getString("DisplayedPropertyForIdentifier"+ (i == 1 ? "" : i));
			if(prn != null && !"".equals(prn.trim()))
				listOfPropertyNames.add(prn);
			}catch(Exception e){
				
			}
				
		 }
	    String[]  stringArray = new String[]{};
		 
	    stringArray = listOfPropertyNames.toArray(stringArray);
		return stringArray;
	
	}

	public static String getTabName(String simpleName) {
		try{
			return getResourceBundle(simpleName).getString("FORMNAME");
			}catch(Exception e){
			   return "?";	
			}
	}
}
