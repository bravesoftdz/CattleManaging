package damdariar.gui.editor;

import damdariar.beans.PropertyMetaData;

public interface EditorI {
	
	public  void setEditorSequence(int sequenceNo);
	public  int  getEditorSequence();
	public  void setEditorProperty(PropertyMetaData  property);  
	public  PropertyMetaData getEditorProperty();
	public  void setValue(Object value);
	public  Object getValue();

}
