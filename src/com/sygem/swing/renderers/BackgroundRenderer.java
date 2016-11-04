package com.sygem.swing.renderers;

import java.awt.Component;
import java.awt.Graphics2D;

public abstract interface BackgroundRenderer
{
  public abstract void renderBackground(Component paramComponent, Graphics2D paramGraphics2D);
}