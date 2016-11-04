package damdariar.gui.swing;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ConfirmDialog extends JDialog{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String[] YES_NO_OPTIONS = {"\u0628\u0644\u06cc","\u062e\u06cc\u0631"};
	static String[] OK_CANCEL_OPTIONS = {"\u062a\u0627\u06cc\u06cc\u062f","\u0627\u0646\u0635\u0631\u0627\u0641"};
	
	public ConfirmDialog(){
		
		
	}

	public static int showYesNoDialog(Component component, String message,
			String deleteDialogTitle, int yesNoOption, int warningMessage,
			Object object) {
		

		return JOptionPane.showOptionDialog(component,
				message ,
				deleteDialogTitle,
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.WARNING_MESSAGE,
			    null,     //do not use a custom Icon
			    YES_NO_OPTIONS,  //the titles of buttons
			    YES_NO_OPTIONS[0]); //default button title
	}
	
	public static int showOkCancelDialog(Component component, String message,
			String deleteDialogTitle, int yesNoOption, int warningMessage,
			Object object) {
		

		return JOptionPane.showOptionDialog(component,
				message ,
				deleteDialogTitle,
			    JOptionPane.OK_CANCEL_OPTION,
			    JOptionPane.WARNING_MESSAGE,
			    null,     //do not use a custom Icon
			    OK_CANCEL_OPTIONS,  //the titles of buttons
			    OK_CANCEL_OPTIONS[0]); //default button title
	}
	
	

}
