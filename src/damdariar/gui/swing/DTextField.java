package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.beans.PropertyVetoException;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import damdariar.util.FarsiTextAssistant;
import damdariar.util.Util;

public class DTextField extends JTextField{

	@Override
	public void fireVetoableChange(String propertyName, Object oldValue,
			Object newValue) throws PropertyVetoException {
		super.fireVetoableChange(propertyName, oldValue, newValue);
	}


	public DTextField() {
		super();
		config();
	}

	public DTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		config();
		// TODO Auto-generated constructor stub
	}

	public DTextField(int columns) {
		super(columns);
		config();
		// TODO Auto-generated constructor stub
	}

	public DTextField(String text, int columns) {
		super(text, columns);
		config();
		// TODO Auto-generated constructor stub
	}

	public void config(){
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setDocument(new TextDocument(this));
		
	}
	public DTextField(String text) {
		super(text);
		config();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

class TextDocument extends PlainDocument{

	JComponent comp;
	private static final long serialVersionUID = 1L;
	TextDocument(JComponent comp){
		this.comp = comp;
	}
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if(comp.getInputContext() != null &&  comp.getInputContext().getLocale().getCountry().equalsIgnoreCase("IR"))
			str = Util.getUnicodeNumer(str);
		super.insertString(offs, FarsiTextAssistant.validate(str), a);
	}
	
	
}
