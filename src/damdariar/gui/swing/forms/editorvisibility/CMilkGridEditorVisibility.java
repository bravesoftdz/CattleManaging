package damdariar.gui.swing.forms.editorvisibility;

public class CMilkGridEditorVisibility extends GridEditorVisibility{

	 public boolean isVisible(String propertyName){
		  
		 if(propertyName.equalsIgnoreCase("active"))
			 return false;
		 return super.isVisible(propertyName);
		 
		  
	  }	
}
