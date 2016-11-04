package damdariar.gui.swing.forms.gridform;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DStatusPanel extends JPanel{
	JLabel label = new JLabel();
	
	DStatusPanel(){
		label.setOpaque(false);
		setOpaque(false);
		setLayout(new BorderLayout());
		add(label);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
	
	public void setStatusMessage(String text){
		label.setText(text);
	}
	
	public void setStatusMessage(String text,Icon icon){
		label.setText(text);
		label.setIcon(icon);
	}
	
	public void clear(){
		
		label.setText("");
		label.setIcon(null);
	}

}
