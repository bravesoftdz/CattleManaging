package damdariar.beans;

import java.lang.reflect.Method;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.Type;

import damdariar.hibernate.DNamingStrategy;
import damdariar.hibernate.HibernateUtil;
import damdariar.resource.ResourceUtil;

public abstract class AbstractBean  {
	
	static IdentifierMap<String,Class<?>>  IDMAP = new IdentifierMap<String,Class<?>>();
	static Map<Class<?>,TreeConfig>        TREEMAP = new HashMap<Class<?>, TreeConfig>();
	static Map<Class<?>,String>            MASTERDETAIL = new HashMap<Class<?>,String> () ;
	
	protected static String name;
	public    static String getIdentifierClassMethodName = "getIdentifierClass";   
	public    static String isIdentifierMethodName = "isIdentifier";
	public    static Class<?>[]  parameterType = new Class[]{String.class}; 
	
	
	public static boolean isIdentifier(Class<?> cls,String propertyName){
		return IDMAP.containsKey(cls, propertyName);
	}
	
	public static boolean isDetailForm(Class<?> cls){
		return MASTERDETAIL.containsKey(cls);
	}
	
	public static String getDetailFormPropertyName(Class<?> cls){
		return MASTERDETAIL.get(cls);
	}
	
	public static String getMasterDetailCriteria(Class<?> detail,int parentId){
		String propertyName = MASTERDETAIL.get(detail);
		String where = propertyName+ " = " + parentId;
		return where;
	}
	
	public static boolean isTreeSeparateLoad(Class<?> cls){
		return false;
//		return TREEMAP.get(cls).isSeparteLoad();
		
	}
	
	public static boolean isTreeNeeded(Class<?> cls){
		if(cls == CProperty.class)
			return true;
/*		if(TREEMAP.containsKey(cls))
			return true;*/
		return false;
	}
	
	public  static boolean isParentValid(String linkName,Class<?> parent){
		return HibernateUtil.getMetaData(parent).getIdentifierPropertyName().equalsIgnoreCase(linkName);
	}
	
/*	public static boolean isTreeSeparateLoad(Class<?> cls){
		
	}*/
	
	public static String getTreeParentPropertyName(Class<?> cls){
	   return 	TREEMAP.get(cls).getParentName();
	}
	
	@SuppressWarnings("unchecked")
	public  Integer getID(){
		
		try{
			Class<?> cls = this.getClass();
			String methodName = "get"+HibernateUtil.getMetaData(cls).getIdentifierPropertyName();
			Method  identifierMethod =  cls.getMethod(methodName, null);
			Integer id = (Integer) identifierMethod.invoke(this, null);
		return id;
		}catch(Exception e){
			e.printStackTrace();
		}
	   
		return -1;
	};
	
	@SuppressWarnings("unchecked")
	public  void setID(Integer id){
		
		try{
			   String idName = HibernateUtil.getMetaData(this.getClass()).getIdentifierPropertyName();
			   Class<?> cls = getClass();
			   Method  valueGetterMethod = setPropertyMethod(cls, idName, new Class<?>[]{Integer.class});
			   valueGetterMethod.invoke(this,new Object[]{id});
			
		   }catch(Exception e){
			   
		   }
		
	};
	
	public  Integer getParentID(){
		return (Integer)getPropertyValue(this, TREEMAP.get(this.getClass()).getParentName());
	};
	
	public  void setParentID(Integer ID){
		try{
			   Class<?> cls = getClass();
			   Method  valueGetterMethod = setPropertyMethod(cls, TREEMAP.get(this.getClass()).getParentName(), new Class<?>[]{Integer.class});
			   valueGetterMethod.invoke(this,new Object[]{ID});
			
		   }catch(Exception e){
			   
		   }
	};
	
   public static Class<?> getIdentifierClass(Class<?> cls,String propertyName){
	   Object val = IDMAP.get(cls,propertyName);
		if(val != null)
			return  (Class<?>)val;	
		return null;
	}
   
   public static Class<?> getIdentifierClass(Object obj,String propertyName){
	   if(obj == null)
		   return null;
	   Object unproxifiedObject = obj;
	   if (obj instanceof HibernateProxy){
	         HibernateProxy proxy = (HibernateProxy) obj;
	         unproxifiedObject = proxy.getHibernateLazyInitializer().getImplementation();
	   }
	   Class<?> clsType = unproxifiedObject instanceof Class<?> ?  ((Class<?>)unproxifiedObject) : unproxifiedObject.getClass();
	   Object val = IDMAP.get(clsType,propertyName);
		if(val != null)
			return  (Class<?>)val;	
		return null;
	}
   
   public static Object[]  getPropertValues(String propertName){
	   
	   return HibernateUtil.getListOfValues(name, propertName);
   }
   
   public static Object[]  getPropertValues(String className,String propertName){
	   
	   return HibernateUtil.getListOfValues(className, propertName);
	   
   }
   
   
   public static String  getPropertyNameTranslation(String propertyName){
	   return ResourceUtil.getResourceBundle(name).getString(propertyName);
	   
   }
   
   public static String  getPropertyNameTranslation(String className,String propertyName){
	   return ResourceUtil.getResourceBundle(name).getString(propertyName);
	   
   }

   @SuppressWarnings("unchecked")
   public static Object  getPropertyValue(Object beanObject,String propertyName){
	   try{
		   Class cls = beanObject.getClass();
		   Method  valueGetterMethod = getPropertyMethod(cls, propertyName);
		   Object  value = valueGetterMethod.invoke(beanObject, null);
		   return  value;
	   }catch(Exception e){
		   
	   }
	   return null;
   }
   
   public static  Method    getPropertyMethod(Class<?> cls,String propertyName){
	 try{
	  String methodName=  "get"+((propertyName.charAt(0))+"").toUpperCase()+propertyName.substring(1);
	  return cls.getMethod(methodName, null);
	 }catch(Exception e){
		 
	 }
	 return null;
	   
   }
   
   public static  Method    setPropertyMethod(Class<?> cls,String propertyName,Class<?> paraTypes[]){
		 try{
		  String methodName=  "set"+((propertyName.charAt(0))+"").toUpperCase()+propertyName.substring(1);
		  return cls.getMethod(methodName, paraTypes);
		 }catch(Exception e){
			 
		 }
		 return null;
		   
	   }
   
   
   public static String getColumnName(String propertyName){
	   
	   return DNamingStrategy.INSTANCE.propertyToColumnName(propertyName);
   }
   
   public static String getTableName(Class<?> cls){
	   
	   return getTableName(cls.getName());
   }
   
   public static String getTableName(String className){
	   
	   return DNamingStrategy.INSTANCE.classToTableName(className);
   }
   
   public static String getKeyColumnName(Class<?> cls){
	   
	   try{
		   String keyColumn  =  HibernateUtil.getMetaData(cls).getIdentifierPropertyName();
		   return DNamingStrategy.INSTANCE.propertyToColumnName(keyColumn);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return null;
   }
   
   
public static BeanMapUtil  getIdentifiersMap(List<?> listOfObjs,String propertyName){

	   Map<Integer,AbstractBean>  idToBeanMap = new HashMap<Integer, AbstractBean>();   
       Class<?>  cls = null;
       
	   Iterator<?> it = listOfObjs.iterator();
	   while(it.hasNext()){
		   AbstractBean beanObject = (AbstractBean)it.next();
		   if(cls == null)
			   cls = getIdentifierClass( beanObject , propertyName);
		   Integer fk = (((Integer)getPropertyValue(beanObject, propertyName)));
		  try{
		   idToBeanMap.put(beanObject.getID(),(AbstractBean)HibernateUtil.getObject(cls, fk));
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	   }
	   
	   if(idToBeanMap.size() > 0)
		   return new BeanMapUtil(idToBeanMap,cls);
	 
	   return null;
	   
   }

public static void setPropertyValue(Object beanObject, String propertyName, Object value) {
	try{
		   Class<?> typeCls = null;
		   Class<?> cls = beanObject.getClass();
		   if(value == null){
			   Type type = HibernateUtil.getMetaData(cls).getPropertyType(propertyName);
			   if(type == Hibernate.STRING)
				   typeCls = String.class; 
			   if(type == Hibernate.INTEGER)
				   typeCls = Integer.class;
			   if(type == Hibernate.FLOAT)
				   typeCls = Float.class;
			   if(type == Hibernate.DOUBLE)
				   typeCls = Double.class;
			   if(type == Hibernate.DATE)
				   typeCls = Date.class;
			   if(type == Hibernate.BLOB)
				   typeCls = Blob.class;
			   if(type == Hibernate.BOOLEAN)
				   typeCls = Boolean.class;
		   }
		   Method  valueGetterMethod = setPropertyMethod(cls, propertyName, new Class<?>[]{value != null ? value.getClass() : typeCls});
		   valueGetterMethod.invoke(beanObject,new Object[]{value});
		
	   }catch(Exception e){
		   
	   }
}

@SuppressWarnings("unchecked")
public static int compareTo(AbstractBean bean1,AbstractBean bean2,String propertyName) {
	Object value1 = getPropertyValue(bean1, propertyName);
	Object value2 = getPropertyValue(bean2, propertyName);
	if(value1 != null && value2 != null && value1 instanceof Comparable<?>)
		return ((Comparable)value1).compareTo(value2);
	return 1;
}

@Override
public String toString() {
			String displayProperty = ResourceUtil.getDisplayedProperty(this.getClass().getSimpleName());
			if(displayProperty != null)
			{	
				Object val =  getPropertyValue(this, displayProperty);
				if(val != null )
					return val.toString();
				else 
					return " \u0646\u0627 \u0645\u0634\u062e\u0635";
			
			}
			else
				return super.toString();
}

public static String getLinkedPropertyName(Class<?> cls) {
	// TODO Auto-generated method stub
	return MASTERDETAIL.get(cls);
}

}

class TreeConfig{
	
	String parentName;
	boolean isSeparteLoad = false;
	public TreeConfig(String parentName, boolean isSeparteLoad) {
		super();
		this.parentName = parentName;
		this.isSeparteLoad = isSeparteLoad;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public boolean isSeparteLoad() {
		return isSeparteLoad;
	}
	public void setSeparteLoad(boolean isSeparteLoad) {
		this.isSeparteLoad = isSeparteLoad;
	}
	
	
	
	
	
}
