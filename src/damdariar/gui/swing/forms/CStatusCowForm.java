package damdariar.gui.swing.forms;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import damdariar.beans.CProperty;
import damdariar.beans.CStatusCow;
import damdariar.hibernate.HibernateUtil;

public class CStatusCowForm extends AbstractForm {

	public CStatusCowForm(){
		super(CStatusCow.class);
	}

	@Override
	protected boolean beforeSave() {
		Object obj = editorsMap.get("CPropertyId").getValue();
		Date objDate = (Date) editorsMap.get("startDate").getValue();
		String query = "update C_Status_Cow set Active = false  where end_date is null and c_property_id = "+ obj ;
		//, end_Date = " + objDate + "
		 try {
        	  Session sess = HibernateUtil.getSession();
			  Transaction trx = sess.beginTransaction();
			  sess.createSQLQuery(query).executeUpdate();
			  trx.commit();
			  HibernateUtil.closeSession();
			  
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		 //Object c_property_id = getForm_Bean().getPropertValues("CPropertyId");
		return super.beforeSave();
		
		
	}
	

}
