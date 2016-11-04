package damdariar.gui.editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.DTextField;
import damdariar.util.AdvanceKeyListener;

public class NumberEditor extends DTextField implements EditorI{


	
	private PropertyMetaData propertyMetaData;
	private int sequenceNo;


	public NumberEditor() {
		new AdvanceKeyListener(this);
		addKeyListener(new KeyAdapter() {
			  public void keyTyped(KeyEvent e) {
			    char c = e.getKeyChar();
			    if (!((Character.isDigit(c) ||
			      (c == KeyEvent.VK_BACK_SPACE) ||
			      (c == KeyEvent.VK_DELETE)))) {
			        getToolkit().beep();
			        e.consume();
			    }
			  }
			});
		
		setInputVerifier(new InputVerifier() {
   			 public boolean verify(JComponent comp) {
    				  boolean returnValue = true;
    				  DTextField textField = (DTextField)comp;
    				  String content = textField.getText();
    				  if (content.length() != 0) {
   				     try {
   					       Integer.parseInt(textField.getText());
   				     } catch (NumberFormatException e) {
   			       returnValue = false;
    			    }
   		   }
  		    return returnValue;
   		 }
    		public boolean shouldYieldFocus(JComponent input) {
   			   boolean valid = super.shouldYieldFocus(input);
    			  if (!valid) {
    			    getToolkit().beep();
    			  }
  		    return valid;
    }
 		 });
	}
	public Integer getValue() {
		// TODO Auto-generated method stub
		String text = getText();
		if(text != null && !"".equalsIgnoreCase(text.trim()))
			return Integer.parseInt(text);
		return 0;
	}


	public void setValue(Object value) {
		// TODO Auto-generated method stub
		if(value != null)
			setText(value.toString());
		else
			setText(null);
	
	}
	
	

	private static final long serialVersionUID = 1L;


	public PropertyMetaData getEditorProperty() {
		// TODO Auto-generated method stub
		return this.propertyMetaData;
	}

	public void setEditorProperty(PropertyMetaData property) {
		// TODO Auto-generated method stub
		this.propertyMetaData = property;
		
	}
	
	@Override
	public int getEditorSequence() {
		// TODO Auto-generated method stub
		return sequenceNo;
	}

	@Override
	public void setEditorSequence(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}
