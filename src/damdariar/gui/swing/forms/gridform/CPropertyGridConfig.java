package damdariar.gui.swing.forms.gridform;

public class CPropertyGridConfig extends GridConfig{

	int getColumnWidth(String property) {
		if(property.equalsIgnoreCase("color"))
			return  70;
		if(property.equalsIgnoreCase("active"))
			return 70;
		if(property.equalsIgnoreCase("fatherId"))
			return 100;
		if(property.equalsIgnoreCase("numberPelastic"))
			return 70;
		if(property.equalsIgnoreCase("numberBody"))
			return 70;
		if(property.equalsIgnoreCase("description"))
			return 70;
		if(property.equalsIgnoreCase("CHf"))
			return 70;
		if(property.equalsIgnoreCase("flagShakhsozi"))
			return 70;
		if(property.equalsIgnoreCase("flagMagnet"))
			return 70;
		if(property.equalsIgnoreCase("dateInput"))
			return 70;
		if(property.equalsIgnoreCase("dateBirthday"))
			return 70;
		if(property.equalsIgnoreCase("dateShakhsozi"))
			return 70;
		if(property.equalsIgnoreCase("dateMagnet"))
			return 70;
		if(property.equalsIgnoreCase("price"))
			return 70;
		if(property.equalsIgnoreCase("dore"))
			return 70;
		if(property.equalsIgnoreCase("price"))
			return 70;
		if(property.equalsIgnoreCase("cowSex"))
			return 70;
		else
			return super.getDefaultColumnWidth();
		
		
	}
	
	

}
