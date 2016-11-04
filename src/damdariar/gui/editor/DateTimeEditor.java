package damdariar.gui.editor;


import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import com.ghasemkiani.util.icu.PersianCalendar;

import damdariar.gui.swing.forms.listeners.DateDialogListener;

public class DateTimeEditor extends DateEditor implements EditorI,ActionListener,DateDialogListener {
	
	
	@Override
	public void showCalendareDialog() {
			  try{	
				calendarDialog = new CalendarDialog(calendarButton,(Date)getValue(),true);
				calendarDialog.addNotifierListener(this);
				
			  }catch(Exception exp){
				  return;
			  }
			calendarDialog.setVisible(true);	
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DateTimeEditor(){
		super();		
	}
	
	public void setValue(Object value) {
		if(value != null && value instanceof String)
			value = new Date(System.currentTimeMillis());
		editorValue = (Date)value;
		dateTextField.setText(getDisplay((Date)value));
	}

	public static String  getDisplay(Date date){
		if(date == null)
			return null;
		PersianCalendar cl =  new PersianCalendar(date);
		return	getUnicodeNumer(cl.get(Calendar.HOUR_OF_DAY)+"")+ ":" +
				getUnicodeNumer(cl.get(Calendar.MINUTE)+"")+ ":" +
				getUnicodeNumer("00")+
				 "    " +
			   getUnicodeNumer(cl.get(Calendar.YEAR)+"")+ "/" +
		       getUnicodeNumer(cl.get(Calendar.MONTH)+1+"")+ "/" +
		       getUnicodeNumer(cl.get(Calendar.DATE)+"");
		      
		
	}

	


	


}
