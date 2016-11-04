package damdariar.gui.editor;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class DamEditorTableCellEditor  extends AbstractCellEditor implements TableCellEditor {
		
		MultiDateEditor multiDateEditor;
		DamEditorTableCellEditor(MultiDateEditor multiDateEditor){
			this.multiDateEditor = multiDateEditor;
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		DateEditor editor = null;
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int rowIndex, int vColIndex) {
	        return (editor = new DateEditor());
	    }
	    

		@Override
		public boolean stopCellEditing() {
	/*		int row = multiDateEditor.table.convertRowIndexToModel(multiDateEditor.table.getSelectedRow());
			Date value = (Date) editor.getValue();
			multiDateEditor.data.insertElementAt(value, row);*/
			return super.stopCellEditing();
		}


		@Override
		public Object getCellEditorValue() {
			return editor.getValue();
		}
	}
	
