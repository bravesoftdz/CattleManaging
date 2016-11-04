package damdariar.gui.swing;



/*
 * Copyright (c) 2007, Romain Guy
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of the TimingFramework project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import damdariar.gui.property.GUIProperty;
import damdariar.gui.swing.forms.listeners.GuiPropertyChangeListener;

public class GradientPanel extends JPanel implements GuiPropertyChangeListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public   static  BufferedImage gradientImage;
    protected   Color gradientStart = GUIProperty.startGradientColor;
    protected   Color gradientEnd = GUIProperty.endGradientColor;

    public GradientPanel() {
    	 super();
//    	 addComponentListener(new GradientCacheManager());
    	 GUIProperty.INSTANCE.addFormColorChangeListener(this);
    }

    public GradientPanel(LayoutManager layout) {
        super(layout);
//        addComponentListener(new GradientCacheManager());
        GUIProperty.INSTANCE.addFormColorChangeListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
    	if (gradientImage == null)    	
    		 createImageCache();
        g.drawImage(gradientImage, 0, 0, getWidth(), getHeight(), null);
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
    	try{
	    	if (gradientImage != null){
		         gradientImage.flush();
		         gradientImage = null;
	        }
    	}catch(Exception e){
    		
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

	@Override
	public void fireGuiPropertyChanged() {
		disposeImageCache();
	}
}
