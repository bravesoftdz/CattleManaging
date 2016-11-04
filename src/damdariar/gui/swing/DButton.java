package damdariar.gui.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class DButton extends JButton{

	Icon defautlIcon;
	Icon mouseOverIcon;
	public DButton() {
		super();
		addMouseListener(new DMouseAdapter(this));
		// TODO Auto-generated constructor stub
	}

	public DButton(Action a) {
		super(a);
		addMouseListener(new DMouseAdapter(this));
	}

	public DButton(Icon icon) {
		super(icon);
		addMouseListener(new DMouseAdapter(this));
		this.defautlIcon = icon;
	}

	public DButton(String text, Icon icon) {
		super(text, icon);
		addMouseListener(new DMouseAdapter(this));
		this.defautlIcon = icon;
	}

	public DButton(String text) {
		super(text);
		addMouseListener(new DMouseAdapter(this));
	}
	
	public void setIcon(Icon defaultIcon){
		if(this.defautlIcon == null)
			this.defautlIcon = defaultIcon;
		super.setIcon(defaultIcon);
	}
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Icon getDefautlIcon() {
		return defautlIcon;
	}

	public void setDefautlIcon(Icon defautlIcon) {
		this.defautlIcon = defautlIcon;
	}

	public Icon getMouseOverIcon() {
		return mouseOverIcon;
	}

	public void setMouseOverIcon(Icon mouseOverIcon) {
		this.mouseOverIcon = mouseOverIcon;
	}
	
	 @Override
	    protected void paintBorder(Graphics g) {
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(getBackground());
	        //g2.setStroke(new BasicStroke(1.0f));
//	        g2.draw(shape);
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	        super.paintBorder(g2);
	    }

}

class DMouseAdapter extends MouseAdapter{
	private DButton button;

	DMouseAdapter(DButton button){
		this.button = button;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(button.getMouseOverIcon() != null)
			button.setIcon(button.getMouseOverIcon());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(button.getMouseOverIcon() != null)
			button.setIcon(button.getDefautlIcon());
	}
	
	
	
}
