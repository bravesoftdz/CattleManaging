package damdariar.gui.swing.renderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import damdariar.beans.AbstractBean;

public class IdentifierListCellRenderer extends DefaultListCellRenderer{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String propertyName;
	public IdentifierListCellRenderer(String propertyName){
		
		this.propertyName = propertyName;
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.DefaultListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		JLabel label =  (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if(value == null){
		    label.setText("  ");
			return label;
		}
		try{
		Object display = value.getClass().getDeclaredMethod("get"+((propertyName.charAt(0))+"").toUpperCase()+propertyName.substring(1), null).invoke(value, null);
		if(display != null && !"".equals(display.toString().trim()))
			label.setText(display.toString());
		else
			label.setText(((AbstractBean) value).getID().toString());
		}catch(Exception e){
			e.printStackTrace();
			label.setText(((AbstractBean) value).getID().toString());
			
		}
		return label;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
