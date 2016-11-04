package damdariar.gui.swing.forms;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import damdariar.beans.CMilk;
import damdariar.hibernate.HibernateUtil;

public class CMilkForm extends AbstractForm{
	
	public CMilkForm(){
		super(CMilk.class);
	}

	@Override
	protected boolean beforeSave() {
		Object obj = editorsMap.get("CPropertyId").getValue();
		String query = "update c_milk cm  set cm.active = 0 where cm.c_property_id =" + obj + " and  cm.active = 1";		
		 try {
             Session sess = HibernateUtil.getSession();
			 Transaction tr = sess.beginTransaction();
		     sess.createSQLQuery(query).executeUpdate();
		     tr.commit();
			 
		} catch (Exception e) {
		}
		
		return super.beforeSave();
		
	}
	
	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		Float milkS =(Float) editorsMap.get("milkLiterS").getValue();
		Float milkZ = (Float)editorsMap.get("milkLiterZ").getValue();
		Float milkSh= (Float)editorsMap.get("milkLiterSh").getValue();
		Float Total = (milkS != null ? milkS : 0 )+ (milkZ != null ? milkZ : 0 ) + (milkSh != null ? milkSh : 0 );
		editorsMap.get("milkTotal").setValue(Total);
	}

}
