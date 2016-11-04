package damdariar.util;

import java.util.ArrayList;

public class FarsiTextAssistant {
	
	private FarsiTextAssistant()
	{
	}
	
	//in all char arrays below, put the best in the first element of array
	//to replace all with that;
	
	//all display symbols for Farsi character YA.
	private static final String[] Ya  = { 
					"\u064a" ,  //language: FA, Key: shift + 'X' (Arabic Letter Yeh)
					"\u06cc"    //language: FA, Key: 'D' (Arabic Letter Farsi Yeh)
				};
	//all display symbols for Farsi character Kaf.
	private static final String[] Kaf = { 
					"\u06a9" , //language: FA, Key: ';' (Arabic Letter Keheh)
					"\u0643"   //(Arabic Letter Kaf)
				};
	
	public static boolean equals(String str1, String str2)
	{
		str1 = validate(str1);
		str2 = validate(str2);
		return str1.equals(str2);
	}
	public static boolean containsStr2inStr1(String str1, String str2)
	{
		str1 = validate(str1);
		str2 = validate(str2);
		return str1.contains(str2);
	}
	
	public static boolean equalsIgnoreCase(String str1, String str2)
	{
		str1 = validate(str1);
		str2 = validate(str2);
		return str1.equalsIgnoreCase(str2);
	}
	
	public static int indexOf(String str1, String str2)
	{
		str1 = validate(str1);
		str2 = validate(str2);
		return str1.indexOf(str2);
	}
	
	public static Object validate(Object obj)
	{
		if(obj==null)
			return null;
		
		if(obj instanceof String)
			return validate((String)obj);
		
		if(obj instanceof StringBuffer)
			return new StringBuffer(validate(obj.toString()));
		
		return obj;
	}

	public static String validate(String str)
	{
		String result = str;
		result = validate_Ya(result);
		result = validate_Kaf(result);
		return result;
	}

	private static String validate(String str,String[] sameChars) {
		// TODO Auto-generated method stub
		if (str==null)
			return str;
		
		if(sameChars==null || sameChars.length<2)
			return str;
		
		String result = str;
		for(int i=1; i<sameChars.length ; ++i)
			result = result.replaceAll(sameChars[i],sameChars[0]);
		
		return result;
	}

	private static String validate_Ya(String str) {
		// TODO Auto-generated method stub
		return validate(str,Ya);
	}

	private static String validate_Kaf(String str) {
		// TODO Auto-generated method stub
		return validate(str,Kaf);
	}

	private static ArrayList<String> getValues(ArrayList<String> values,String[] sameChars) {
		if(values == null)
			return null;
		
		if(sameChars == null || sameChars.length == 0)
			return values;
		
		for (int i=0 ; i<values.size() ; ++i){
			for (int j=1 ; j<sameChars.length ; ++j){
				values.set(i, values.get(i).replaceAll(sameChars[j], sameChars[0]));
			}
		}
		
		ArrayList<String> result = new ArrayList<String>();
		for (int i=0 ; i<values.size() ; ++i){
			
			result.add(values.get(i));
			
			if (values.get(i).indexOf(sameChars[0])<0)
				continue;
			
			for (int j=1 ; j<sameChars.length ; ++j){
				result.add(values.get(i).replaceAll(sameChars[0], sameChars[j]));
			}
		}
		
		return result;
	}
	
	private static ArrayList<String> getValues_Ya(ArrayList<String> values) {
		return getValues(values, Ya);
	}
	
	private static ArrayList<String> getValues_Kaf(ArrayList<String> values) {
		return getValues(values, Kaf);
	}
	
	private static ArrayList<String> getValues(ArrayList<String> values) {
		values = getValues_Kaf(values);
		values = getValues_Ya(values);
		return values;
	}
	
	public static String getSQLString(String columnName, String operator, String columnValue){
		ArrayList<String> values = new ArrayList<String>();
		values.add(columnValue.replaceAll("'", ""));
		
		values = getValues(values);
		
		String result = "";
		for (String value : values){
			if (result.length() > 0)
				result += " OR ";
			result += columnName + " " + operator + " '" + value + "'";
		}
		
		return " ( " + result + " ) ";
	}
}
