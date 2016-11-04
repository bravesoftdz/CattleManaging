package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import damdariar.gui.swing.model.DTableModel;

public class DTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	    public DTable(int numRows, int numColumns) {
			super(numRows, numColumns);
			init();
		
	    }

	public DTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		init();
	}

	public DTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		init();
	}

	public DTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
		init();
	}

		public DTable() {
	        super();
	        init();
	    }

	    public DTable(TableModel dm) {
	        super(dm);
	        init();
	    }

	    public DTable(TableModel dm, TableColumnModel cm) {
	        super(dm, cm);
	        init();
	    }
	    
	    public void init(){
	    	setComponentOrientation ( ComponentOrientation.RIGHT_TO_LEFT );
	    }

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public boolean isIdentifier(int col) {
			int modelCol = convertColumnIndexToModel(col);
			return  ((DTableModel)getModel()).isIdentifier(modelCol);
		}

		public Object getDisplayProperty(int row, int col) {
			int modelRow = convertRowIndexToModel(row);
			int modelCol = convertColumnIndexToModel(col);
			return ((DTableModel)getModel()).getDisplayProperty(modelRow, modelCol);
		}


}
