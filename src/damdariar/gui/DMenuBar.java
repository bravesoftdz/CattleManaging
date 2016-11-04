package damdariar.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JMenuBar;


public class DMenuBar extends JMenuBar{
	protected BufferedImage gradientImage;
	protected Color gradientStart = Color.lightGray/*GUIProperty.startGradientColor*/;
	protected Color gradientEnd = Color.white;
	

	public DMenuBar() {
		super();
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		addComponentListener(new GradientCacheManager());
	}
	

	   @Override
	    protected void paintComponent(Graphics g) {
	        createImageCache();

	        if (gradientImage != null) {
	            g.drawImage(gradientImage, 0, 0, getWidth(), getHeight(), null);
	        }
	    }

	    protected void createImageCache() {
	        int width = 2;
	        int height = getHeight();

	        if (width == 0 || height == 0) {
	            return;
	        }

	        if (gradientImage == null ||
	            width != gradientImage.getWidth() || 
	            height != gradientImage.getHeight()) {

	            gradientImage = new BufferedImage(width, height,
	                                              BufferedImage.TYPE_INT_RGB);

	            Graphics2D g2 = gradientImage.createGraphics();
	            GradientPaint painter = new GradientPaint(0, 0, gradientEnd,
	                                                      0, height / 2, gradientStart);
	            g2.setPaint(painter);

	            Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height / 2.0);
	            g2.fill(rect);

	            painter = new GradientPaint(0, height / 2, gradientStart,
	                                        0, height, gradientEnd);
	            g2.setPaint(painter);

	            rect = new Rectangle2D.Double(0, (height / 2.0) - 1.0, width, height);
	            g2.fill(rect);

	            g2.dispose();
	        }
	    }

	    private void disposeImageCache() {
	    	if (gradientImage == null)
	    		return;
	        synchronized (gradientImage) {
	            gradientImage.flush();
	            gradientImage = null;
	        }
	    }

	    private class GradientCacheManager implements ComponentListener {
	        public void componentResized(ComponentEvent e) {
	        }

	        public void componentMoved(ComponentEvent e) {
	        }

	        public void componentShown(ComponentEvent e) {
	        }

	        public void componentHidden(ComponentEvent e) {
	            disposeImageCache();
	        }
	    }	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
