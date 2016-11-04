package damdariar.gui.editor;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DTextArea;

public class TextBoxEditor extends DTextArea implements EditorI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;

	public String getValue() {
		// TODO Auto-generated method stub
		return getText();
	}

	public void setValue(Object value) {
		// TODO Auto-generated method stub
		setText((String)value);
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
