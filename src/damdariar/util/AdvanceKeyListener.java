package damdariar.util;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextField;

import damdariar.gui.swing.DComboBox;
import damdariar.gui.swing.DTextField;


public class AdvanceKeyListener  implements KeyListener {

	
	StringBuffer buff = new StringBuffer();
	Map<KeyChecker,Boolean>  isAliveChecker = new HashMap<KeyChecker, Boolean>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComponent field  ;
	FireKeyEvent keyEventFirer ;
	StringBuffer charBuff = new StringBuffer("");
	
	public AdvanceKeyListener(JComponent field)
	{
		this.field = field;
		this.field.addKeyListener(this);
		keyEventFirer 	= new FireKeyEvent(isAliveChecker,field,charBuff);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	boolean  isFirerStarted = false;
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.isControlDown() || e.isAltDown())
			return;
		charBuff.append(e.getKeyChar());
		KeyChecker ch = new KeyChecker(isAliveChecker);
		isAliveChecker.put(ch,true);
		ch.start();
		if(!keyEventFirer.isAlive()){ 
			if(	isFirerStarted){
				keyEventFirer = new FireKeyEvent(isAliveChecker,field,charBuff);
				isFirerStarted = false;
			}
			keyEventFirer.start();
		}
		isFirerStarted = true;
	
	

		
	}
	

}

class  FireKeyEvent extends Thread{
	Map<KeyChecker,Boolean>  isAliveChecker;
	JComponent field;
	StringBuffer charBuff;
	FireKeyEvent(Map<KeyChecker,Boolean>  isAliveChecker,JComponent field,StringBuffer charBuff){
		this.isAliveChecker = isAliveChecker;
		this.field = field;
		this.charBuff = charBuff;
	}
	
	public void run(){
		while(isAliveChecker.containsValue(Boolean.TRUE));
		try {
			String value = null;
			if(field instanceof DTextField){
				value = ((JTextField)field).getText();
				((DTextField)field).fireVetoableChange("", null,value);
			}
			if(field instanceof DComboBox){
				value = charBuff.toString();
				charBuff.replace(0, charBuff.length(), "");
				((DComboBox)field).fireVetoableChange("", null,value.trim().length() > 0 ? value : null);
			}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}

class  KeyChecker extends Thread{
	
	
	Map<KeyChecker,Boolean>  isAliveChecker;
	KeyChecker(Map<KeyChecker,Boolean>  isAliveChecker){
		this.isAliveChecker = isAliveChecker;
	}
	
	public void run(){
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isAliveChecker.put(this,false);
	}
	
}

