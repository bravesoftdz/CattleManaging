package damdariar.gui.editor;

import java.awt.Dimension;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DCheckBox;

public class CheckBoxEditor extends DCheckBox implements EditorI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;

	public CheckBoxEditor(){
		
	   super();
	   setOpaque(false);
	}
	
	public Boolean getValue() {
		// TODO Auto-generated method stub
		return isSelected();
	}

	public void setValue(Object value) {
		if(value == null)
			setSelected(false);
		else
			setSelected((Boolean)value);
	}

	public PropertyMetaData getEditorProperty() {
		// TODO Auto-generated method stub
		return this.propertyMetaData;
	}

	public void setEditorProperty(PropertyMetaData property) {
		// TODO Auto-generated method stub
		this.propertyMetaData = property;
		
	}

	@Override
	public int getEditorSequence() {
		// TODO Auto-generated method stub
		return sequenceNo;
	}

	@Override
	public void setEditorSequence(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}
