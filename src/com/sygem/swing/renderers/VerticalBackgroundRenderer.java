package com.sygem.swing.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class VerticalBackgroundRenderer
  implements BackgroundRenderer
{
  private Color color1;
  private Color color2;

  public VerticalBackgroundRenderer(Color paramColor1, Color paramColor2)
  {
    this.color1 = paramColor1;
    this.color2 = paramColor2;
  }

  public void renderBackground(Component paramComponent, Graphics2D paramGraphics2D)
  {
    int i = paramComponent.getWidth() / 2;
    GradientPaint localGradientPaint = new GradientPaint(i, 0F, this.color1, i, paramComponent.getHeight(), this.color2);
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