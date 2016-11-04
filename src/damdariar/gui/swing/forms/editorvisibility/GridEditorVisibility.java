package damdariar.gui.swing.forms.editorvisibility;

public class GridEditorVisibility {
	
	 public boolean isVisible(String propertyName){
		  
		  if(propertyName.equalsIgnoreCase("rgt") || propertyName.equalsIgnoreCase("lft"))
				return false;
		  return true;
		  
	  }	

}
