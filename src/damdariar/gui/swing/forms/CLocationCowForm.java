package damdariar.gui.swing.forms;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibm.icu.util.Calendar;

import damdariar.beans.CLocationCow;
import damdariar.hibernate.HibernateUtil;

public class CLocationCowForm extends AbstractForm{
	
	public CLocationCowForm(){
		super(CLocationCow.class);
	}

	@Override
	protected void afterSave() {
		Date objDate = (Date)editorsMap.get("startDate").getValue();
		Timestamp ts = new Timestamp(objDate.getTime());
		Integer cowID = (Integer)editorsMap.get("CPropertyId").getValue();
		String str = "update c_location_cow lc set lc.active = 0,lc.end_date = " + ts +" where lc.c_property_id =" + cowID + "  and lc.active = 1 and lc.end_date is null and "+ "lc.c_location_cow_id !="+$Form_Bean.getID()+ "and lc.start_date > "+ ts ; ;
		 try {
             Session sess = HibernateUtil.getSession();
			  Transaction trx = sess.beginTransaction();
			  sess.createSQLQuery(str).executeUpdate();
			 /* trx.commit();
			  HibernateUtil.closeSession();*/
			 
		} catch (Exception e) {
		}
		HibernateUtil.flushCurrentSession();
		super.afterSave();
	}
	
}
