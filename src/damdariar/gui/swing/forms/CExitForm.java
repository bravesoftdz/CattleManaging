package damdariar.gui.swing.forms;

import damdariar.beans.CExit;

public class CExitForm extends AbstractForm{
	
	
	public CExitForm(){
		super(CExit.class);
	}
	
	public CExitForm(String[] tobeRemovedEditors) {
		super(CExit.class,tobeRemovedEditors);
	}


	@Override
	protected void afterSave() {
/*		super.afterSave();
        Integer obj = (Integer)editorsMap.get("CPropertyId").getValue();
        String query = "update C_Property set active = 0 where active = 1 and C_Property_Id = " + obj ;
       
        try {
        	  Session sess = HibernateUtil.getSession();
			  Transaction trx = sess.beginTransaction();
			  sess.createSQLQuery(query).executeUpdate();
			  trx.commit();
			  HibernateUtil.closeSession();
			 
		} catch (Exception e) {
			// TODO: handle exception
		}*/
	}
}
