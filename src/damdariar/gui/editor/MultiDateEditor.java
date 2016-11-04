package damdariar.gui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.forms.listeners.DateDialogListener;
import damdariar.images.ImageUtil;

public class MultiDateEditor extends JComponent implements EditorI,ActionListener,DateDialogListener {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CalendarDialog   calendarDialog;
	PropertyMetaData propertyMetaData;
	private int sequenceNo;
	JTable table;
	JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
	MyTableModel model;  
	JButton add ;
	public  static String SIGNATURE = "#MULTIDATEEDITOR#";
	public MultiDateEditor(){
		model = new MyTableModel();
		table = new JTable();
		table.setModel(model);
		table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		table.setOpaque(false);
		table.setDragEnabled(false);
//		table.getTableHeader().setVisible(false)
		table.setDefaultRenderer ( Date.class , new DTableCellRenderer () );
		Icon deleteIco = ImageUtil.getImageIcon("row_delete.png");
		add    = new JButton(ImageUtil.getImageIcon("row_add.png"));
		add.setPreferredSize(new Dimension(24,24));
		add.setSize(24,24);
		JButton delete = new JButton(deleteIco);
		delete.setPreferredSize(new Dimension(24,24));
		delete.setSize(24,24);
		delete.setActionCommand("delete");
		add.setActionCommand("add");
		add.addActionListener(this);
		delete.addActionListener(this);
		toolbar.add(add);
		toolbar.add(delete);
		setLayout(new BorderLayout());
		DScrollPane pane = new DScrollPane(table);
		add(toolbar,BorderLayout.EAST);
		add(pane,BorderLayout.CENTER);
		
	}
	@Override
	public PropertyMetaData getEditorProperty() {
		return 	  propertyMetaData;
	}

	@Override
	public int getEditorSequence() {
		return sequenceNo;
	}

	@Override
	public Object getValue() {
		StringBuffer buffer = new StringBuffer();
		boolean isFirstTime = true;
		for(Date date:model.data){
			if(isFirstTime){
				buffer.append(SIGNATURE );
				isFirstTime = false;
			}
			String time = date.getTime()+"";
			buffer.append(time).append("@");
			
		}
		if(buffer.length() > 0)
			return buffer.toString();
		return null;
	}

	@Override
	public void setEditorProperty(PropertyMetaData property) {
		propertyMetaData = property;		
	}

	@Override
	public void setEditorSequence(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	@Override
	public void setValue(Object value) {
		if(value == null || ((String)value).trim().equals("")){
			model.data = new Vector<Date>();
			model.fireTableDataChanged();
		}
		else{
			String dateStream = (String)value;
			dateStream =  dateStream.substring(SIGNATURE.length());
			Vector<Date>  newModel = new Vector<Date>();
			while(dateStream.length() > 0){
				int index = dateStream.indexOf("@");
				 long milis = Long.parseLong(dateStream.substring(0,index));
				 newModel.add(new Date(milis));
				 dateStream = dateStream.substring(index+1);
			}
			
			model.data = newModel;
			model.fireTableDataChanged();
			
		}
		
	}
	
	public static Date[] getDates(String dateStream){
		if(dateStream == null || dateStream.trim().equals(""))
			return null;
		dateStream =  dateStream.substring(SIGNATURE.length());
		Vector<Date>  newModel = new Vector<Date>();
		while(dateStream.length() > 0){
			int index = dateStream.indexOf("@");
			 long milis = Long.parseLong(dateStream.substring(0,index));
			 newModel.add(new Date(milis));
			 dateStream = dateStream.substring(index+1);
		}
		Date[]  dates = new Date[newModel.size()];
		newModel.toArray(dates);
		return dates;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equalsIgnoreCase("delete")){
				int selectedRow = table.getSelectedRow();
				if(selectedRow >= 0){
					int row = table.convertRowIndexToModel(selectedRow);
					model.removeRow(row);
				}
			}
			else{
				
					  try{	
							calendarDialog = new CalendarDialog(add,null,true);
							calendarDialog.addNotifierListener(this);
							
						  }catch(Exception exp){
							  return;
						  }
						calendarDialog.setVisible(true);		
				}
				
			}
	@Override
	public void notifyOnClose() {
		model.insertRow(calendarDialog.getCalendarDate());
	}
	
	public static String getDisplay(String str){
		StringBuffer buff = new StringBuffer();
		String dateStream = str;
		dateStream =  dateStream.substring(SIGNATURE.length());
		while(dateStream.length() > 0){
			int index = dateStream.indexOf("@");
			 long milis = Long.parseLong(dateStream.substring(0,index));
			 if(buff.length()>0)
				 buff.append(",");
			 buff.append(DateTimeEditor.getDisplay(new Date(milis)));
			 dateStream = dateStream.substring(index+1);
		}
		return buff.toString();
 
	}

}



class DTableCellRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 7174826787820700051L;

	public Component getTableCellRendererComponent ( JTable table , Object value , boolean isSelected ,
			boolean hasFocus , int row , int col )
	{
		
			
		JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		if ( row % 2 == 0 && !isSelected )
		{
			l.setBackground ( new Color ( 240 , 251 , 252 ) );
		}
		else
		{
			l.setBackground ( new Color ( 194 , 234 , 251 ) );
		}

		if ( isSelected )
		{
			l.setFont ( new Font ( table.getFont ().getFontName () , Font.BOLD , table.getFont ().getSize () + 1 ) );
			l.setBackground ( new Color ( 159 , 218 , 200 ) );
		}

	
		l.setHorizontalAlignment (JLabel.CENTER );
		l.setText ( value != null ? "  " + value.toString () : "" );
		if(value instanceof Date)
			l.setText(DateTimeEditor.getDisplay((Date)value));
		l.setOpaque ( true );

		return l;
	}


}


class MyTableModel extends AbstractTableModel {
	boolean DEBUG = false;
    private String[] columnNames = {" \u0631\u0648\u0632 \u0648 \u0633\u0627\u0639\u062a \u0645\u0635\u0631\u0641 \u062f\u0627\u0631\u0648"};
    
    public void removeRow(int row){
    	data.remove(row);
    	fireTableDataChanged();
    }
    public void insertRow(Date calendarDate) {
    	setValueAt(calendarDate, getRowCount(), 0);
	}
	 Vector<Date> data = new Vector<Date>();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data.get(row);
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return Date.class;
    }

    public boolean isCellEditable(int row, int col) {
 /*       //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }*/
    	return false;
    }

    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }
    
        data.add((Date)value);/*insertElementAt((Date)value, row);
        if(row < data.size()-1)
        	data.remove(row+1);*/
        fireTableDataChanged();

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {

       
    }
}

