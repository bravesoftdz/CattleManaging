package damdariar.gui.editor;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DTextField;
import damdariar.util.AdvanceKeyListener;

public class SimpleTextEditor extends DTextField implements EditorI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;
	
	public SimpleTextEditor(){
		new AdvanceKeyListener(this);
	}
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
