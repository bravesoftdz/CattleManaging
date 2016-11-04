package damdariar.gui.swing.renderers;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Blob;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import damdariar.gui.editor.DateEditor;
import damdariar.gui.editor.MultiDateEditor;


public class DTableCellRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 7174826787820700051L;

	public Component getTableCellRendererComponent ( JTable table , Object value , boolean isSelected ,
			boolean hasFocus , int row , int col )
	{
		
		/*if(((DTable)table).isIdentifier(col)){
			
			value = ((DTable)table).getDisplayProperty(row,col);
		}*/
		
		if(value instanceof Blob)
			value = " \u0639\u06a9\u0633 \u0648\u0627\u0631\u062f \u0634\u062f\u0647";
		else if(value instanceof Boolean)
			return getTableCellRendererCheckBox(table, value, isSelected, hasFocus, row, col);
		
			
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
		l.setOpaque ( true );

		return l;
	}
	
	public Component getTableCellRendererCheckBox ( JTable table , Object value , boolean isSelected ,
			boolean hasFocus , int row , int col )
	{
			JCheckBox c = new JCheckBox ();
			if ( row % 2 == 0 && !isSelected )
			{
				c.setBackground ( new Color ( 240 , 251 , 252 ) );
			}
			else
			{
				c.setBackground ( new Color ( 194 , 234 , 251 ) );
			}

			if ( isSelected )
			{
				c.setBackground ( new Color ( 159 , 218 , 200 ) );
			}

			c.setHorizontalAlignment ( JLabel.CENTER );
			c.setSelected ( (Boolean) value );
			c.setOpaque ( true );
			return c;

		
	}

}
