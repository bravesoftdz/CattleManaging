package damdariar.gui.swing;

import java.awt.ComponentOrientation;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

public class DCheckBox extends JCheckBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DCheckBox() {
		super();
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(Action a) {
		super(a);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(Icon icon, boolean selected) {
		super(icon, selected);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(Icon icon) {
		super(icon);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(String text, boolean selected) {
		super(text, selected);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(String text, Icon icon, boolean selected) {
		super(text, icon, selected);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(String text, Icon icon) {
		super(text, icon);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

	public DCheckBox(String text) {
		super(text);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// TODO Auto-generated constructor stub
	}

}
