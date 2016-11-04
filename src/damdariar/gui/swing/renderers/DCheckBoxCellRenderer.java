package damdariar.gui.swing.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;



public class DCheckBoxCellRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 7174826787820700051L;

	public Component getTableCellRendererComponent ( JTable table , Object value , boolean isSelected ,
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