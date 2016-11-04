package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

public class DPanel extends GradientPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public DPanel (){
		
		super();
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}   
	
	public DPanel (LayoutManager layout){
		
		super(layout);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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


}
