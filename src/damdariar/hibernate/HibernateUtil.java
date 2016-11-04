package damdariar.hibernate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import damdariar.beans.AbstractBean;
import damdariar.resource.ResourceUtil;

public class HibernateUtil {
	
	static SessionFactory factory=HibernateSessionFactory.getSessionFactory();
	static Session session ;
	static List<Session> activeSessions = new ArrayList<Session>();
	
	
	public static SessionFactory getFactory(){
		if(factory != null)
			return factory;
		factory =  HibernateSessionFactory.getSessionFactory();
		return factory;
	}
	public static Session getSession(){
	  if(factory == null)	
		   factory =  HibernateSessionFactory.getSessionFactory();
	 if(session == null || (session != null && (!session.isOpen() || !session.isConnected())))
	 {      session = factory.openSession();
	 		activeSessions.add(session);
	 }
	 return session;
	}
	

	
	public static void closeSession(){
		/*if(session.isOpen()){
		  session.flush();
		  session.close();
		}
		session = null;*/
	}
	
	public static void closeCurrentSession(){
		for(int i =  activeSessions.size()-1 ; i >= 0 ;  i--){
			
			Session session = activeSessions.get(i);
			try{
				  session.flush();
			}catch(Exception e){
				
			}
			try{
				  session.close();
				  
			}catch(Exception e){
			}
			activeSessions.remove(i);
		}
		
		try{
			if(objSession != null){
				objSession.close();
				objSession = null;
			}
		}catch(Exception ee){
			
		}
	}
	
	public static void flushCurrentSession(){
		/*try{
		if(session != null && session.isConnected())
			session.flush();
		}catch(Exception e){
			
		}*/
	}
	
	public static void flushSession(){
		try{
			session.flush();
		}catch(Exception e){
			
		}
	}
	
	
	public static void close(){
		try{
			closeCurrentSession();
			factory.close();
			session = null;
			factory = null;
		}catch(Exception e){
			
		}
	}
	
	
	public static void    saveOrUpdate(Object obj){

			getSession();
			Transaction trx = session.beginTransaction();
			session.saveOrUpdate(obj);
			trx.commit();
			try{
				session.flush();
			}catch(Exception e){
			}
			closeSession();
	
	}
	

	
	
	public static void    delete(Object obj){
		    getSession();
			Transaction trx = session.beginTransaction();
			session.delete(obj);
			trx.commit();
			try{
				session.flush();
			}catch(Exception e){
			}
			closeSession();
		
		
	}
	
	static Session objSession;
	public static Object  getObject(Class<?> classType,Integer id){
		if(objSession == null)
			objSession = factory.openSession();
		Object obj = null;
		try{
		    obj = objSession.load(classType,id);
		}catch(Exception e){
			objSession = factory.openSession();
			obj = objSession.load(classType,id);
		}
		return obj;
	}
	
	
	public static Object  getObject(Class<?> classType,String where){
		getSession();
		DNamingStrategy namingStrategy =  new DNamingStrategy();
		String tableName =namingStrategy.classToTableName(classType.getSimpleName());
		List<?> obj = session.createSQLQuery("select * from  "+tableName + " WHERE " +where).addEntity(classType).list();
		closeSession();
		if(obj != null && obj.size() > 0)
			return obj.get(0);
		return null;
	}
	
	public static List<?>  getListOfObjects(Class<?> classType){
		return getListOfObjects(classType,null);
	}
	
	public static List<?>  getListOfObjects(Class<?> classType,String sqlCriteria){

		if(AbstractBean.isTreeNeeded(classType) && !AbstractBean.isTreeSeparateLoad(classType))
			return getHierarchialData(classType);
		try{
		getSession();
		List<?> listOfObjs=null;
		if(sqlCriteria != null)
		{
			listOfObjs = session.createQuery("From "+classType.getSimpleName() + " WHERE " +sqlCriteria).list();
		}
		else
		{	
		   Criteria criteria = session.createCriteria(classType);
		   listOfObjs = criteria.list();
		}
		closeSession();
		return listOfObjs;
		}
		catch(Exception e)
		{
			
		}
		
		return null;
		
		
	}
	
	
	public static List<?>  getComboBoxModel(Class<?> classType,String sqlCriteria){

		try{
			Session session = factory.openSession();//getSession();
			List<?> listOfObjs=null;
			if(sqlCriteria != null)
			{
				listOfObjs = session.createQuery("From "+classType.getSimpleName() + " WHERE " +sqlCriteria).list();
			}
			else
			{	
			   Criteria criteria = session.createCriteria(classType);
			   listOfObjs = criteria.list();
			}
			activeSessions.add(session);
			return listOfObjs;
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	


	
	@SuppressWarnings("unchecked")
	public static Object[]  getListOfValues(String className,String propertyName){
		ArrayList<Object>  list = null;
		getSession();
		Iterator results = session.createQuery(
		        "select "+className+"."+propertyName+" from "+className+"  "+className+
		        "order by "+className+"."+propertyName)
		        .list()
		        .iterator();
		closeSession();
		while ( results.hasNext() ) {
			if(list == null)
				list = new ArrayList<Object>();
		     list.add(results.next());		   
		}
		
		if(list != null)
			return list.toArray();
		
		return null;
	}
	
	public static ClassMetadata getMetaData(Class<?> cls){
		
		getFactory();
		ClassMetadata objMetaData = factory.getClassMetadata(cls);
		return   objMetaData;
		
	}
	
	
	public static  String[]  getPropertyNames(Class<?> cls){
		
		ClassMetadata metaData = getMetaData(cls);
		return metaData.getPropertyNames();	
		
	}
	
	public static Type[]  getPropertyType(Class<?> cls){
		
		ClassMetadata metaData = getMetaData(cls);
		return metaData.getPropertyTypes();	
		
	}
	
	@SuppressWarnings("unchecked")
	public static Object[]  getPropertyValues(Object obj){
		Class cls = obj.getClass();
		ClassMetadata metaData = getMetaData(cls);
		return metaData.getPropertyValues(obj, EntityMode.POJO);	
		
	}

	
	@SuppressWarnings("unchecked")
	public static String[]  checkValidity(Object obj){
		ArrayList<String> listOfEmptyProperties = null;
		
		Class cls = obj.getClass();
		ClassMetadata metaData = getMetaData(cls);
		boolean[]  nullabalities = metaData.getPropertyNullability();
		Object[] values = metaData.getPropertyValues(obj, EntityMode.POJO);
		String[] names = metaData.getPropertyNames();
		for(int i = 0 ; i < values.length ; i++)
		{
			if(!nullabalities[i] && values[i] == null){
				if(listOfEmptyProperties == null)
					listOfEmptyProperties = new ArrayList<String>();
				listOfEmptyProperties.add(names[i]);
			}
			
		}
		
		if(listOfEmptyProperties != null)
			return (String[]) listOfEmptyProperties.toArray();
		return null;
			
		
	}
	
	public static  void  getDisplayedProperty(Class<?> cls){
		
		String displayedProperty =   ResourceUtil.getDisplayedProperty(cls.getSimpleName());
	
//		cls.getDeclaredMethod(name, parameterTypes)
//		if(displayedProperty)
		
	}
	
	public static void saveForHierarchialClasses(Object obj){
		
		
	}
	
	
	public static List<?>  getLeafNodes(Class<?> classType){
		String keyColumn  =  AbstractBean.getKeyColumnName(classType);
		String tableName  =  AbstractBean.getTableName(classType);
		String select = "Select   " + keyColumn +" from " +  tableName + " WHERE rgt = lft + 1";
		getSession();
		List<?> list = session.createSQLQuery(select).addScalar(keyColumn,Hibernate.INTEGER).list();
		closeSession();
		return list;
	}
	
	
	public  static List<?>  getHierarchialData(Class<?> classType){
		
//		if(classType == CProperty.class){
			String cpropertySelect = " select * "+
			  " from c_property   start with father_id  is  null " +
			  " connect by prior c_property_id = father_id order by nvl(c_property_id,father_id) ";
			
		
			getSession();
			List<?> list = session.createSQLQuery(cpropertySelect).addEntity(classType).list();
			closeSession();
			return list;
			
		/*String keyColumn  =  getMetaData(classType).getIdentifierPropertyName();
		String tableName  =  classType.getSimpleName();
		
		String  select =  "FROM " + tableName + " AS treeCollection WHERE treeCollection."+keyColumn+ " IN ("+  
						  "select node."+keyColumn + " FROM " + tableName + " AS node ," +
				            tableName + " AS parent "+
				          " WHERE ( node.lft BETWEEN parent.lft AND parent.rgt ) " +
				          " AND( parent."+keyColumn+" = 0 )) " +
				          " ORDER BY treeCollection.lft ";
		

		getSession();
		List<?> list = session.createQuery(select).list();
		closeSession();
		return list;*/
		
	}  
	
	
	public  static  void saveNode(Object node){
	try{
		Class<?> classType =  node.getClass();
		String   keyColumn  =  HibernateUtil.getMetaData(classType).getIdentifierPropertyName();
		List<?> leafIds = getLeafNodes(classType);
		String  parentPropertyName = AbstractBean.getTreeParentPropertyName(classType);
		Integer parentId = (Integer)AbstractBean.getPropertyValue(node,parentPropertyName );
		Integer objID = ((AbstractBean)node).getID();
		if(objID != null && objID == 0)
			return;
		if(parentId == null ){
			parentId = 0;
			AbstractBean.setPropertyValue(node,parentPropertyName,parentId);
		}
		if(objID != null){
			Integer dbID = getIDValue(classType.getSimpleName(),keyColumn,parentPropertyName,objID);
			if(dbID.equals(parentId)){
				saveAdjustedNode(node);
				return;
			}
			
		}
		if(leafIds != null && leafIds.contains(parentId))
			saveToLeafNode(node,parentId,parentPropertyName,keyColumn);
		else
			saveToParentNode(node,parentId,parentPropertyName,keyColumn);
		}catch(Exception e){
			
		}
		
	}
	
	public static Integer getIDValue(String className,String keyColumnName,String idPropertyName,Integer pk){
		String idHQL        =  " SELECT "+ idPropertyName +" FROM "+ className +
								" WHERE " + keyColumnName + " = " + pk;
		Integer id = (Integer) HibernateUtil.getSession().createQuery(idHQL).uniqueResult();
		return  id;
	}
	
	public  static  void saveToLeafNode(Object node,Integer parentID,String parentPropertyName,String keyColumn){
		Class<?> classType =  node.getClass();
		String tableName  =  classType.getSimpleName();
		String lft        =  " SELECT  lft FROM "+tableName +
							 " WHERE " + keyColumn + " = " + parentID;
		List<?> list = HibernateUtil.getSession().createQuery(lft).list();
		int lftValue =  (list != null && list.size() > 0) ? (Integer)list.get(0) : 1;
		
		
		getSession();
		Transaction tx = session.beginTransaction();
			String updateHql = "Update "+tableName + " set rgt = rgt + 2 WHERE  rgt > " + lftValue;
			session.createQuery(updateHql).executeUpdate();
			updateHql = "Update "+tableName + " set lft = lft + 2 WHERE  lft > " + lftValue;
			session.createQuery(updateHql).executeUpdate();
		tx.commit();
		closeSession();
		
		AbstractBean.setPropertyValue(node,"rgt",lftValue+2);
		AbstractBean.setPropertyValue(node,"lft",lftValue+1);
		saveAdjustedNode(node);
		
	}
	public  static  void saveToParentNode(Object node,Integer parentID,String parentPropertyName,String keyColumn){
		Class<?> classType =  node.getClass();
		String tableName  =  classType.getSimpleName();
		String rgt =    " SELECT  rgt FROM "+tableName +
							 " WHERE " + keyColumn + " = " + parentID;
		List<?> list = HibernateUtil.getSession().createQuery(rgt).list();
		int rgtValue =  (list != null && list.size() > 0) ? (Integer)list.get(0) : 2;
		
		getSession();
		Transaction tx = session.beginTransaction();
			String updateHql = "Update "+tableName + " set rgt = rgt + 2 WHERE  rgt >= " + rgtValue;
			session.createQuery(updateHql).executeUpdate();
			updateHql = "Update "+tableName + " set lft = lft + 2 WHERE  lft >= " + rgtValue;
			session.createQuery(updateHql).executeUpdate();
		tx.commit();
		closeSession();
		
		AbstractBean.setPropertyValue(node,"rgt",rgtValue+1);
		AbstractBean.setPropertyValue(node,"lft",rgtValue);
		saveAdjustedNode(node);
		
	}
	
	
	
	public static void    saveAdjustedNode(Object obj){
			getSession();
			session.saveOrUpdate(obj);
			closeSession();
	
	}
	
	public  static  void deleteNode(Object node){
		Class<?> classType =  node.getClass();
		String tableName  =  classType.getSimpleName();
		
		String  parentPropertyName = AbstractBean.getTreeParentPropertyName(classType);
		Integer parentId = (Integer)AbstractBean.getPropertyValue(node,parentPropertyName );
		Integer nodeID = ((AbstractBean)node).getID();
		
		Integer leftVal  = (Integer)	AbstractBean.getPropertyValue(node, "lft");
		Integer rightVal = (Integer)	AbstractBean.getPropertyValue(node, "rgt");
		
		getSession();
		Transaction tx = session.beginTransaction();
		String updateHql = "Update "+tableName + " set rgt = rgt - 1,lft = lft -1  WHERE lft BETWEEN " +leftVal + " AND "+ rightVal;
		session.createQuery(updateHql).executeUpdate();
		
		updateHql = "Update "+tableName + " set rgt = rgt - 2 WHERE  rgt > " + rightVal;
		session.createQuery(updateHql).executeUpdate();
		
		updateHql = "Update "+tableName + " set lft = lft - 2 WHERE  lft > " + rightVal;
		session.createQuery(updateHql).executeUpdate();
		
		updateHql = "Update "+tableName + " set " + parentPropertyName + " = " + 0/*parentId*/ + " WHERE " +  parentPropertyName + " = " + nodeID;
		session.createQuery(updateHql).executeUpdate();
		
		tx.commit();
		closeSession();

		
	}

	
	
	

}