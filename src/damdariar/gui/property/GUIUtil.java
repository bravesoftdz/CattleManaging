package damdariar.gui.property;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import damdariar.images.ImageUtil;

public class GUIUtil {

	
	public static JButton getOKButton(){
		
		JButton okButton = new JButton( "\u062a\u0627\u06cc\u06cc\u062f", new ImageIcon(ImageUtil.class.getResource("ok16.gif")));
		okButton.setActionCommand("OK");
		return okButton;
		

	}
	
	
	public static  JButton getCancelButton(){
		
		JButton cancelButton = new JButton( " \u0627\u0646\u0635\u0631\u0627\u0641", new ImageIcon(ImageUtil.class.getResource("cancel16.gif")));
		cancelButton.setActionCommand("CANCEL");
		return cancelButton;
	}
	
	public static void showCenterScreen (Window window)
	{
		window.pack();
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension wSize = window.getSize();
		window.setLocation(((sSize.width-wSize.width)/2), ((sSize.height-wSize.height)/2));
		window.toFront();
		window.setVisible(true);
	}	//	showCenterScreen
	
	public static void showCenterScreenUnpacked (Window window)
	{
//		window.pack();
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension wSize = window.getSize();
		window.setLocation(((sSize.width-wSize.width)/2), ((sSize.height-wSize.height)/2));
		window.toFront();
		window.setVisible(true);
	}	//	showCenterScreen

}
