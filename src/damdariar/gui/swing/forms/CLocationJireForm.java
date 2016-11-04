package damdariar.gui.swing.forms;

import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import damdariar.beans.CLocationJire;
import damdariar.hibernate.HibernateUtil;

public class CLocationJireForm extends AbstractForm{

	CLocationJireForm() {
		super(CLocationJire.class);
	}

	@Override
	protected void afterSave() {
		Date objDate = (Date)editorsMap.get("startDate").getValue();
		Timestamp ts = new Timestamp(objDate.getTime());
		Integer locationID = (Integer)editorsMap.get("CLocationBaseId").getValue();
		String str = "update c_location_jire lc set lc.active = 0, lc.end_date = " + ts +" where lc.C_Location_Base_Id =" + locationID + "  and lc.active = 1 and lc.end_date is null and "+ "lc.c_location_jire_id !="+$Form_Bean.getID()+ "and lc.start_date > "+ ts ;
//				
		 try {
             Session sess = HibernateUtil.getSession();
			  Transaction trx = sess.beginTransaction();
			  sess.createSQLQuery(str).executeUpdate();
			  trx.commit();
			  HibernateUtil.closeSession();
			 
		} catch (Exception e) {
		}
		HibernateUtil.flushCurrentSession();
		
		super.afterSave();
	}

	@Override
	protected boolean beforeSave() {
		Date objDate = (Date)editorsMap.get("startDate").getValue();
		if(objDate == null){
			JOptionPane.showMessageDialog(null," \u062a\u0627\u0631\u06cc\u062e \u0634\u0631\u0648\u0639 \u0631\u0627 \u0645\u0634\u062e\u0635 \u0646\u06a9\u0631\u062f\u0647 \u0627\u06cc\u062f"," \u062f\u0627\u0645\u06cc\u0627\u0631",JOptionPane.CANCEL_OPTION);
			return false;
		}
		return true;
	}

	
	
}
