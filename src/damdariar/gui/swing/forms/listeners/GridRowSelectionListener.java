package damdariar.gui.swing.forms.listeners;

import java.util.List;

import damdariar.beans.AbstractBean;

public interface  GridRowSelectionListener {
	
	public void gridRowSelectionChanged(AbstractBean beanObj);
	public void girdRowsDeleted(List<AbstractBean> deletedBeans);

}
