package damdariar.gui.swing.forms;

import damdariar.beans.CTest;

public class CTestForm extends AbstractForm {

	 public CTestForm(){
		 super(CTest.class);
	 }

	@Override
	public void befroeLayoutComponents() {
		editorsMap.remove("CTalghihId");
		editorsMap.remove("CPropertyId");
	}

	@Override
	protected void afterSave() {
			}
	
	
	
	 
}
