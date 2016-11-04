package damdariar.gui.swing;

/* 
  * Created on Apr 4, 2005 
  */ 
 
 import java.awt.Color; 
 import java.awt.GradientPaint; 
 import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.LayoutManager;
  
import javax.swing.JPanel; 

 
 /** 
  * @author Eitan Suez 
  */ 
 public class GradientPanel2 extends JPanel 
 { 
    private Color _color; 
    private Color _otherColor; 
    
    protected static Color gradientStart = Color.GRAY;
    protected static Color gradientEnd = Color.LIGHT_GRAY;
     
   private static Color TRANSPARENT =  Color.LIGHT_GRAY;//new Color(0, 0, 0, 0);
   
   public GradientPanel2() {
	   this(gradientStart,true);
 }

 public GradientPanel2(LayoutManager layout) {
	   this(gradientStart,true);
	   setLayout(layout);
	 
 }

 
    public GradientPanel2(Color color, boolean transparent) 
   { 
       setOpaque(false); 
       _color = color; 
       _otherColor = (transparent) ? TRANSPARENT : Color.white; 
    } 
    
   protected void paintComponent(Graphics g) 
    { 
      Graphics2D g2 = (Graphics2D) g; 
      GradientPaint paint = new GradientPaint(0, 0, _color, getWidth(), 0, _otherColor); 
       g2.setPaint(paint); 
       g2.fill(getBounds()); 
       super.paintComponent(g); 
  } 
     
 } 
