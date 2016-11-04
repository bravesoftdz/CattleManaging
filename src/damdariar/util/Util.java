package damdariar.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;

public class Util {
	
	public static String getUnicodeNumer(String mixstr){
		

		if(mixstr == null || "".equals(mixstr))
			return mixstr; 
		char[] ca = mixstr.toCharArray();
		int n = 0;
		char ch = 0x06f0 ;
		String result = "";
		for(int k = 0; k<ca.length; k++){
			if(Character.isDigit(ca[k])){
			n = Integer.parseInt(""+ca[k]);
			ch+=n;
			}
			else{
				ch=ca[k];
			}
			result+=ch;
			ch = 0x06f0;
		}
	
		return result;
	
	
	}
	
	public static String getEnglishNumer(String mixstr){
		

		if(mixstr == null || "".equals(mixstr))
			return mixstr; 
		char[] ca = mixstr.toCharArray();
		int n = 0;
		String result = "";
		for(int k = 0; k<ca.length; k++){
			if(Character.isDigit(ca[k])){
				n = Integer.parseInt(""+ca[k]);
				result = result + n;
			}
			else{
				result = result + ca[k];
			}
		}
	
		return result;
	
	
	}
	
	
	public  static byte[] toByteArray(Blob fromBlob) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
		return toByteArrayImpl(fromBlob, baos);
		} catch (SQLException e) {
		throw new RuntimeException(e);
		} catch (IOException e) {
		throw new RuntimeException(e);
		} finally {
		if (baos != null) {
		try {
		baos.close();
		} catch (IOException ex) {
		}
		}
		}
		}
	
	private static byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)
	throws SQLException, IOException {
	byte[] buf = new byte[1000000];
	InputStream is = fromBlob.getBinaryStream();
	try {
	for (;;) {
	int dataSize = is.read(buf);

	if (dataSize == -1)
	break;
	baos.write(buf, 0, dataSize);
	}
	} finally {
	if (is != null) {
	try {
	is.close();
	} catch (IOException ex) {
	}
	}
	}
	return baos.toByteArray();
	}
	


	




	
	

}
