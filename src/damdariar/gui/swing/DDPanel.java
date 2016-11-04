package damdariar.gui.swing;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

public class DDPanel extends GradientPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public DDPanel (){
		
		super();
		gradientStart = Color.green/*Color.GRAY*/;
		gradientEnd = Color.LIGHT_GRAY;
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}   
	
	public DDPanel (LayoutManager layout){
		
		super(layout);
		gradientStart = Color.green/*Color.GRAY*/;
		gradientEnd = Color.LIGHT_GRAY;
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
	
	public void setBackgroundImage(Image img){
		this.image = img;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		gradientStart = Color.green/*Color.GRAY*/;
		gradientEnd = Color.LIGHT_GRAY;
		super.paintComponent(g);
		if(image != null)
			g.drawImage(image, 0, 0, null);
	}


}
