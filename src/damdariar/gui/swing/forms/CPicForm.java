package damdariar.gui.swing.forms;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JComponent;

import damdariar.beans.CPic;
import damdariar.gui.editor.EditorI;
import damdariar.gui.swing.DLabel;

public class CPicForm extends AbstractForm {

	CPicForm() {
		super(CPic.class);
		
		
	}
	
		
	
public void layOutComponents(){
		
		EditorI editor = editorsMap.get("CPropertyId");; 
		FormUtility.initializeLocale((JComponent)editor);
	 	FormUtility.setPreferredSize(editor,FormUtility.FORM_MIN_FIELD_TYPE);
		
		
	   
	
		        layoutManager.getConstriant().fill = GridBagConstraints.BOTH;		
				layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
			 	layoutManager.add(new DLabel(editor.getEditorProperty().getPropertyTranslation()));
				layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
			 	layoutManager.add((JComponent) editor);
			 
			
	   EditorI editor2 = editorsMap.get("CPersonalId");
  	   layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
	 	layoutManager.add(new DLabel(editor2.getEditorProperty().getPropertyTranslation()));
		layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
	 	layoutManager.add((JComponent) editor2);
	 	
	 	
	 	EditorI editor3 = editorsMap.get("date");
	 	((JComponent) editor3).setPreferredSize(((JComponent) editor2).getPreferredSize());
	 	layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
	 	layoutManager.add(new DLabel(editor3.getEditorProperty().getPropertyTranslation()));
		layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
	 	layoutManager.add((JComponent) editor3);
	 	
	 	layoutManager.gotoNewLine();
	     layoutManager.getConstriant().fill = GridBagConstraints.BOTH;
	 	 EditorI editor4 = editorsMap.get("picLeft");
	 	layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
	 	layoutManager.add(new DLabel(editor4.getEditorProperty().getPropertyTranslation()));
		layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
	 	layoutManager.add((JComponent) editor4);
	 	
	 	
	 	EditorI editor5 = editorsMap.get("picHead");
	 	layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
	 	layoutManager.add(new DLabel(editor5.getEditorProperty().getPropertyTranslation()));
		layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
	 	layoutManager.add((JComponent) editor5);

	 	
	 	EditorI editor6 = editorsMap.get("picRight");
	 	layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
	 	layoutManager.add(new DLabel(editor6.getEditorProperty().getPropertyTranslation()));
		layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
	 	layoutManager.add((JComponent) editor6);
	 	
	 	layoutManager.gotoNewLine();
	 	EditorI editor7 = editorsMap.get("description");
	 	layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
	 	layoutManager.add(new DLabel(editor7.getEditorProperty().getPropertyTranslation()));
	 	layoutManager.getConstriant().gridwidth = 3;
		layoutManager.getConstriant().gridheight = 1;
		layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
	 	layoutManager.add((JComponent) editor7);
	 	((JComponent) editor7).setPreferredSize(new Dimension(200,50));
	 	

						
	}



	

}
