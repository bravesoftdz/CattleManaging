package com.sygem.swing.renderers;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.TexturePaint;

public class TextureBackgoundRenderer
  implements BackgroundRenderer
{
  private TexturePaint texturePaint = null;

  public TextureBackgoundRenderer(TexturePaint paramTexturePaint)
  {
    this.texturePaint = paramTexturePaint;
  }

  public void renderBackground(Component paramComponent, Graphics2D paramGraphics2D)
  {
    paramGraphics2D.setPaint(this.texturePaint);
    paramGraphics2D.fillRect(0, 0, paramComponent.getWidth(), paramComponent.getHeight());
  }
}