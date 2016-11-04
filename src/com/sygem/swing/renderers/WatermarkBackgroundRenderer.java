package com.sygem.swing.renderers;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class WatermarkBackgroundRenderer
  implements BackgroundRenderer
{
  private Image img;
  private int alphaComps = 100;

  public WatermarkBackgroundRenderer(Image paramImage, int paramInt)
  {
    this.img = null;
    this.alphaComps = paramInt;
    this.img = paramImage;
  }

  public void renderBackground(Component paramComponent, Graphics2D paramGraphics2D)
  {
    Composite localComposite = paramGraphics2D.getComposite();
    paramGraphics2D.setComposite(AlphaComposite.getInstance(3, this.alphaComps / 100.0F));
    int i = paramComponent.getWidth() - this.img.getWidth(null);
    int j = paramComponent.getHeight() - this.img.getHeight(null);
    paramGraphics2D.drawImage(this.img, AffineTransform.getTranslateInstance(i, j), null);
    paramGraphics2D.setComposite(localComposite);
  }
}