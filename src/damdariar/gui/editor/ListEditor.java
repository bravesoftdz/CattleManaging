package damdariar.gui.editor;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DList;

public class ListEditor extends DList implements EditorI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;

	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
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
