package damdariar.gui.swing.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.beans.VetoableChangeListener;
import java.util.EventObject;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import damdariar.gui.editor.EditorI;
import damdariar.gui.swing.forms.FormUtility;
import damdariar.gui.swing.model.DEditableTableModel;
public class DTableCellEditor extends AbstractCellEditor implements TableCellEditor{
  
	private Map<String,EditorI> editorsMap;
	String[] propertyNames;
	Class<?> classType;
    private Component  editor;
	private VetoableChangeListener listener;
	public DTableCellEditor(DEditableTableModel model,VetoableChangeListener listener){
		classType = model.getClassType();
		propertyNames = model.getPropertyNames();
		this.listener = listener;
		editorsMap = FormUtility.getEditors(classType, null, listener);
	}
	@Override
	public Object getCellEditorValue() {
		if(editor != null)
			return ((EditorI)editor).getValue();
		return null;
	}

	@Override
    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            int clickCount = 2;
            return ((MouseEvent)evt).getClickCount() >= clickCount;
        }
        return true;
    }

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) {
		editor = (Component)editorsMap.get(propertyNames[arg4]);
		editor.requestFocus();
		return editor;
	}
	

}
