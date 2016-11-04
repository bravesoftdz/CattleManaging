package damdariar.gui.swing;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
public class TestRotatedButton {
   
   void makeUI() {
      JFrame frame = new JFrame("Test Rotated Button");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);
      frame.setLayout(new FlowLayout());
      frame.add(new DRotatedButton("Test Clockwise", true));
      frame.add(new DRotatedButton("Test Anticlockwise", false));
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new TestRotatedButton().makeUI();
         }
      });
   }
}
 
