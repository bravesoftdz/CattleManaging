package damdariar.gui.editor;

import javax.swing.ComboBoxModel;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DComboBox;

public class ComboBoxEditor  extends DComboBox implements EditorI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;

	public ComboBoxEditor(ComboBoxModel model) {
		super(model);
	}
	
	public ComboBoxEditor(){
		
		super();
	}
	
	public Object getValue() {
		// TODO Auto-generated method stub
		return getSelectedItem();
	}

	public void setValue(Object value) {
		// TODO Auto-generated method stub
		if(value == null)
			setSelectedIndex(0);
		else
			setSelectedItem(value);
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
