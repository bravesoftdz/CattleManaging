package damdariar.gui;

import java.awt.ComponentOrientation;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import damdariar.beans.AbstractBean;
import damdariar.gui.swing.forms.AbstractFormContentPane;
import damdariar.hibernate.HibernateUtil;
import damdariar.resource.ResourceUtil;

public class DTabbedPane extends JTabbedPane implements ChangeListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<?>[] classes;
	int lastSelectedIndex = 0;

	public DTabbedPane() {
		super();
		config();
	}
	
	public DTabbedPane(int tabPlacement ,Class<?>[] classes){
		this(tabPlacement);
		this.classes = classes;
		init();
		addChangeListener(this);
		
	}
	
	public void init(){
		
		if(classes != null)
			if(classes.length != 0){
				AbstractFormContentPane contentPane = new AbstractFormContentPane(classes[0]);
				String tabName = ResourceUtil.getTabName(classes[0].getSimpleName());
				addTab(tabName, contentPane);
			}
			
			for(int i = 1 ; i < classes.length ; i++){
				String tabName = ResourceUtil.getTabName(classes[i].getSimpleName());
				addTab(tabName, null);
			}
			
		
	}

	public DTabbedPane(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
		config();
	}

	public DTabbedPane(int tabPlacement) {
		super(tabPlacement);
		config();
	
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		
		AbstractFormContentPane lastCom =  ((AbstractFormContentPane)getComponentAt(lastSelectedIndex));
		
		int index =  getSelectedIndex();
		String  linkName     =	AbstractBean.getDetailFormPropertyName(classes[index]);
		Integer formID = null;
		if(linkName != null){
			boolean isParentValid = AbstractBean.isParentValid(linkName, lastCom.getClassType());
			if(!isParentValid && linkName != null ){
				String lastComParentName = AbstractBean.getDetailFormPropertyName(lastCom.getClassType());
				if(linkName.equals(lastComParentName))
					formID  = lastCom.getParentId();
				
			}	
			else if (isParentValid  && linkName != null){
				formID = lastCom.getFormID();
			}
		}
		boolean isDetailForm  = linkName != null ? true : false;
		if(!isDetailForm){
			HibernateUtil.closeCurrentSession();
		}
		lastCom.dispose();
		if(getComponentAt(index) == null){
			
			AbstractFormContentPane contentPane = new AbstractFormContentPane(classes[index],formID,linkName,isDetailForm);
			setComponentAt(index, contentPane);
		}
		
		if(!isDetailForm)
			setComponentAt(lastSelectedIndex, null);
		lastSelectedIndex = index;
		
		
		
	}
	
	public void config(){
		
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//		setFont(new Font(getFont().getName(),Font.BOLD,getFont().getSize()));
	}

	public Class<?>[] getClasses() {
		return classes;
	}

}
