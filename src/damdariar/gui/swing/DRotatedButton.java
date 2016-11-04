package damdariar.gui.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class DRotatedButton extends JButton {
	   


       private static final long serialVersionUID = 1L;
       
	   XButton template;
	   boolean clockwise;
	   
	  public  DRotatedButton(String text, boolean clockwise) {
	      template = new XButton(text);
	      this.clockwise = clockwise;
	      
	      Dimension d = template.getPreferredSize();
	      setPreferredSize(new Dimension(d.height, d.width));
	   }
	   
	   @Override
	   protected void paintComponent(Graphics g) {
	      Graphics2D g2 = (Graphics2D) g.create();
	      
	      Dimension d = getSize();
	      template.setSize(d.height, d.width);
	      
	      if (clockwise) {
	         g2.rotate(Math.PI / 2.0);
	         g2.translate(0, -getSize().width);
	      } else {
	         g2.translate(0, getSize().height);
	         g2.rotate(- Math.PI / 2.0);
	      }
	      template.setSelected(this.getModel().isPressed());
	      template.paintComponent(g2);
	      g2.dispose();
	   }
	   
	   @Override
	   public String getText() {
		   // TODO Auto-generated method stub
		   if(template != null)
			   return template.getText();
		   else
			   return "";
	   }
	   
	   private class XButton extends JToggleButton {
	      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		XButton(String text) {
	         super(text);
	      }
	      
	      @Override
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);
	      }
	   }

	/* (non-Javadoc)
	 * @see javax.swing.AbstractButton#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		template.setText(text);
	}
	}