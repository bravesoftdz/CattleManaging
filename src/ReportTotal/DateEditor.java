package ReportTotal;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JDialog;

import com.ghasemkiani.util.icu.PersianCalendar;
import damdariar.beans.PropertyMetaData;
import damdariar.gui.editor.CalendarDialog;
import damdariar.gui.editor.EditorI;
import damdariar.gui.swing.DButton;
import damdariar.gui.swing.DTextField;
import damdariar.gui.swing.forms.listeners.DateDialogListener;
import damdariar.images.ImageUtil;
import damdariar.util.dateutil.JalaliCalendar;

public class DateEditor extends JComponent implements EditorI,ActionListener,DateDialogListener {
	
	boolean dirty = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Component parent;
	DateEditor(Component parent){
		
		this.parent = parent;
		setLayout(new BorderLayout());
		add(calendarButton,BorderLayout.LINE_START);
		add(dateTextField,BorderLayout.CENTER);
		
//		dateTextField.setEditable(false);
//		dateTextField.setDocument(new DateDocument(this));
		dateTextField.addKeyListener(new KeyAdapter(){

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE ||
				   e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					dirty = false;
					setValue(null);
				}
				else if (!Character.isDigit(e.getKeyChar()) 
					&& e.getKeyCode() != KeyEvent.VK_SLASH 
					&& e.getKeyCode() != KeyEvent.VK_SUBTRACT
					&& e.getKeyCode() != KeyEvent.VK_COLON)
				{
					showCalendareDialog();
				}
				else {
					dirty = true;
				}
				}
			
		});
		calendarButton.addActionListener(this);
		calendarButton.setFocusable(false);
		calendarButton.setIcon(ImageUtil.getImageIcon("datebuttonicon.png"));
		
	}

	 DButton  calendarButton = new DButton();
	 DTextField dateTextField = new DTextField(20);
	 PropertyMetaData propertyMetaData;
	 CalendarDialog   calendarDialog;
	 Date             editorValue;
	private int sequenceNo;
	private DateDialogListener calendarListener;
	static  char SEPERATOR = '/';

	public Object getValue() {
		if(dirty){
			try{
				char separator = '/';
				String text = dateTextField.getText();
				int index = text.indexOf(separator);
				if(index == -1 ){
					separator = ':';
					index = text.indexOf(separator);
					if(index == -1){
						separator = '-';
						index = text.indexOf(separator);
						if(index == -1){
							dirty = false;
							setValue(null);
							return null;
						}
					}
					}
				
				int year = 0 ;
				if(index == 2)
					year = Integer.parseInt("13"+text.substring(0, index));
				else 
					year = Integer.parseInt(text.substring(0, index));
				
				text = text.substring(index+1);
				index = text.indexOf(separator);
				int month=0;
				month = Integer.parseInt(text.substring(0, index));
				
				text = text.substring(index+1);
				int day=0;
				day = Integer.parseInt(text.substring(0));
				JalaliCalendar calendar = new JalaliCalendar();
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month-1);
				calendar.set(Calendar.DATE, day);
				Date date = new Date(calendar.getTimeInMillis());
				dirty = false;
				return date;
				
			}catch(Exception e){
				dirty = false;
				setValue(null);
				return null;
			}
			
		}
		return editorValue;
	}
	
	
	public void addMouseListener(MouseListener l){
		dateTextField.addMouseListener(l);
	}


	public void setValue(Object value) {
		if(value != null && value instanceof String)
			value = new Date(System.currentTimeMillis());
		editorValue = (Date)value;
		dateTextField.setText(getDisplay((Date)value));
	}


	public void setEnabled(boolean enabled){
		dateTextField.setEnabled(enabled);
		calendarButton.setEnabled(enabled);
		
		
	}

	public void actionPerformed(ActionEvent e) {
	   showCalendareDialog();
	}
	
	public PropertyMetaData getEditorProperty() {
		return this.propertyMetaData;
	}

	public void setEditorProperty(PropertyMetaData property) {
		this.propertyMetaData = property;
		
	}
	
	public static String  getDisplay(Date date){
		if(date == null)
			return null;
		PersianCalendar cl =  new PersianCalendar(date);
		return getUnicodeNumer(cl.get(Calendar.YEAR)+"")+ SEPERATOR +
		       getUnicodeNumer(cl.get(Calendar.MONTH)+1+"")+ SEPERATOR +
		       getUnicodeNumer(cl.get(Calendar.DATE)+"");
		
	}

	public static String getUnicodeNumer(String str){
		
		String result = "";
		for(int i = 0 ; i < str.length() ; i++){
			char digit = '\u06F0';
			digit += Integer.parseInt(str.charAt(i)+"");
			result = result +  digit;
			
		}
		return result;
	}
	
	
	
	

	@Override
	public void notifyOnClose() {
		dirty = false;
		setValue(calendarDialog.getCalendarDate());
	}
	
	public void showCalendareDialog(){
		  try{	
				calendarDialog = new CalendarDialog((JDialog) parent,calendarButton,(Date)getValue(),false);
				calendarDialog.addNotifierListener(this);
				
			  }catch(Exception exp){
				  return;
			  }
			calendarDialog.setVisible(true);		
	}



	
	@Override
	public int getEditorSequence() {
		return sequenceNo;
	}

	@Override
	public void setEditorSequence(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}


