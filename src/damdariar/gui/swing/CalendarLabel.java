package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CalendarLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	
	
	public CalendarLabel(){
		setOrientation();
		
	}
	
	public CalendarLabel(String text){
		
		super(text);
		setOrientation();
	}
	
	
	public CalendarLabel(Icon ic){
		
		super(ic);
		setOrientation();
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
		g.drawString(getText(),  3 , 12);
		
	}
	
	public void setOrientation(){
		
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setHorizontalTextPosition(SwingConstants.RIGHT);
	}





}
