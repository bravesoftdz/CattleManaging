package damdariar.gui.swing.forms;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.List;

import org.hibernate.Hibernate;

import damdariar.beans.CTalghih;
import damdariar.beans.CZayeman;
import damdariar.hibernate.HibernateUtil;


public class CTalghihForm extends AbstractForm {
	
	public CTalghihForm(){
		super(CTalghih.class);
	}
	
	
	CTalghihForm(String[] tobeRemovedEditors) {
		super(CTalghih.class,tobeRemovedEditors);
	}
	
	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		super.vetoableChange(evt);
		if(evt.getPropertyName().equalsIgnoreCase("CPropertyId")){
			Integer id = (Integer) evt.getNewValue();
			if(id != null){
				List<Integer> list = HibernateUtil.getSession().createSQLQuery("select CP.dore FROM C_Property CP where CP.C_Property_Id = " + id).addScalar("dore",Hibernate.INTEGER).list();
				if(list != null && list.size() != 0){
					Integer dore = list.get(0);
					if(dore != null)
						editorsMap.get("dore").setValue(dore);
					else
						editorsMap.get("dore").setValue(null);
				}
			}
			else {
				editorsMap.get("dore").setValue(null);
			}
		}
	}
}
