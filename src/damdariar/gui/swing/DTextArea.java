package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.beans.PropertyVetoException;

import javax.swing.JTextArea;
import javax.swing.text.Document;

public class DTextArea extends JTextArea{
	
	@Override
	public void fireVetoableChange(String propertyName, Object oldValue,
			Object newValue) throws PropertyVetoException {
		super.fireVetoableChange(propertyName, oldValue, newValue);
	}



	public DTextArea() {
		super();
		configEditor();
	}

	public DTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		configEditor();

	}

	public DTextArea(Document doc) {
		super(doc);
		configEditor();
	}

	public DTextArea(int rows, int columns) {
		super(rows, columns);
		configEditor();
	}

	public DTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		configEditor();
	}

	public DTextArea(String text) {
		super(text);
		configEditor();
	}
	public void configEditor(){
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setDocument(new TextDocument(this));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}


