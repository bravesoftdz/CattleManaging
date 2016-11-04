package damdariar.gui.swing.forms;

import org.hibernate.Session;

import damdariar.beans.CPropertySine;
import damdariar.hibernate.HibernateUtil;

public class CPropertySineForm extends AbstractForm{

	CPropertySineForm() {
		super(CPropertySine.class);
	}

	@Override
	protected void afterSave() {
		Integer cowID = (Integer)editorsMap.get("CPropertyId").getValue();
		String str = "update  c_property_sine ps  set ps.active = 0 where ps.active = 1 and ps.c_property_sine_id <>" +$Form_Bean.getID() +"and ps.c_property_id = " + cowID;
		try {
      	  Session sess = HibernateUtil.getSession();
			  org.hibernate.Transaction tr = sess.beginTransaction();
			  sess.createSQLQuery(str).executeUpdate();
			  tr.commit();
//			  sess.getTransaction().commit(); 
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.afterSave();
		
	}

	@Override
	protected boolean beforeSave() {
		
		
		return super.beforeSave();
	}

	
}
