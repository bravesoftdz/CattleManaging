package damdariar.util.dateutil;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class PersianDateFormat {
	
	public static String mediumFormat( JalaliCalendar jc)
	{
		return jc.get(Calendar.DATE) + " " + jc.getJalaliMonthName() + ", " + jc.get(Calendar.YEAR);
	}
	
	public static String shortFormat ( JalaliCalendar jc)
	{
		return jc.get(Calendar.YEAR) + "/" + (jc.get(Calendar.MONTH) + 1) + "/" + jc.get(Calendar.DATE);
	}
	
	public static String shortFormat ( Date date)
	{
		JalaliCalendar jc = new JalaliCalendar();
		jc.setTime(date);
		return shortFormat(jc);
	}
	
	public static String monthANDday( JalaliCalendar jc)
	{
		return (jc.get(Calendar.MONTH) + 1) + "/" + jc.get(Calendar.DATE);
	}
	
	public static String dayOfWeek( JalaliCalendar jc)
	{
		return jc.getJalaliWeekDayName();
	}
	
	public static String monthofWeek( JalaliCalendar jc)
	{
		return jc.getJalaliMonthName();
	}
	
	public static String monthANDyear( JalaliCalendar jc)
	{
		return jc.getJalaliMonthName()+ ", " + jc.get(Calendar.YEAR) ;
	}
	
	public static String month( JalaliCalendar jc)
	{
		return jc.getJalaliMonthName();
	}
	
	public static String dateANDtime(Date date )
	{
		JalaliCalendar jc = new JalaliCalendar();
		jc.setTime(date);
		String dat = shortFormat(jc);
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
		String time = df.format(date);
		return dat + " " + time;
	}
	
	public static String getCurrentDate(JalaliCalendar jc)
	{
		return jc.getJalaliWeekDayName();
	}
	
	public static String getCurrentDate()
	{
		return getCurrentDate(System.currentTimeMillis());
	}


	private static String getCurrentDate(long currentTimeMillies)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTimeMillies);
		
		JalaliCalendar jc = new JalaliCalendar(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE),
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND));
		return jc.getJalaliWeekDayName();
	}
	

}
