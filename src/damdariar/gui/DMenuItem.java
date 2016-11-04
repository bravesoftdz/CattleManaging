package damdariar.gui;

import java.awt.ComponentOrientation;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class DMenuItem extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<?>[] classes;

	public DMenuItem() {
		super();
		config();
	}

	public DMenuItem(Action a) {
		super(a);
		config();
	}

	public DMenuItem(Icon icon) {
		super(icon);
		config();
	}

	public DMenuItem(String text, Icon icon) {
		super(text, icon);
		config();
	}

	public DMenuItem(String text, int mnemonic) {
		super(text, mnemonic);
		config();
	}

	public DMenuItem(String text) {
		super(text);
		config();
	}
	
	public void config(){
		
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
	
	public  void addFormClasses(Class<?>[] clSs){
		
		this.classes = clSs;
	}

	public Class<?>[] getClasses() {
		return classes;
	}

	public void setClasses(Class<?>[] classes) {
		this.classes = classes;
	}
	

}
