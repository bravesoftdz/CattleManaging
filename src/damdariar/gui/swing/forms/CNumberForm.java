package damdariar.gui.swing.forms;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import damdariar.beans.CNumber;
import damdariar.hibernate.HibernateUtil;

public class CNumberForm extends AbstractForm{
	
	public CNumberForm(){
		super(CNumber.class);
	}

	@Override
	protected boolean beforeSave() {
		Integer obj =  (Integer)editorsMap.get("CPropertyId").getValue();
		Date objDate = (Date) editorsMap.get("startDate").getValue();
		String query = "update C_Number set Active = 0   where end_date is null and c_property_id = " + obj;
//			,end_Date = " + objDate +
			try {
              Session sess = HibernateUtil.getSession();
			  Transaction trx = sess.beginTransaction();
			  sess.createSQLQuery(query).executeUpdate();
			  trx.commit();
			  HibernateUtil.closeSession();
			 
		} catch (Exception e) {
		}
		return true;
	}

}
