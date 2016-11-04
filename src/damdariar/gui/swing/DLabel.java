package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import damdariar.gui.property.GUIProperty;

public class DLabel extends JLabel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	
	
	public DLabel(){
		config();
		
	}
	
	public DLabel(String text){
		
		super(text);
		config();
	}
	
	
	public DLabel(Icon ic){
		
		super(ic);
		config();
	}
	public void setBackgroundImage(Image img){
		this.image = img;
	}

	
	

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(image != null)
			g.drawImage(image, 0, 0, null);
		
	}
	
	public void config(){
		
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setHorizontalTextPosition(SwingConstants.RIGHT);
		setFont(GUIProperty.font);
		setForeground(GUIProperty.fontColor);
	}





}
