package com.sygem.swing.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class GradientBackgroundRenderer
  implements BackgroundRenderer
{
  private Color color1;
  private Color color2;

  public GradientBackgroundRenderer(Color paramColor1, Color paramColor2)
  {
    this.color1 = paramColor1;
    this.color2 = paramColor2;
  }

  public void renderBackground(Component paramComponent, Graphics2D paramGraphics2D)
  {
    int i = paramComponent.getHeight() / 2;
    GradientPaint localGradientPaint = new GradientPaint(0F, i, this.color1, paramComponent.getWidth(), i, this.color2);
    paramGraphics2D.setPaint(localGradientPaint);
    paramGraphics2D.fillRect(0, 0, paramComponent.getWidth(), paramComponent.getHeight());
  }

  public void setColor1(Color paramColor)
  {
    this.color1 = paramColor;
  }

  public void setColor2(Color paramColor)
  {
    this.color2 = paramColor;
  }
}