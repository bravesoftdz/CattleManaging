package damdariar.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.util.StringHelper;

public class DNamingStrategy extends DefaultNamingStrategy {
	 
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> standardProperties; 
	public static DNamingStrategy INSTANCE;
	
	static {
		if(INSTANCE == null)
			INSTANCE = new DNamingStrategy();
	}
	
	public DNamingStrategy() {
		super();
		initializeStandardProperties();		
	}
		
	private void initializeStandardProperties() {
		standardProperties = new HashMap<String, String>();
		standardProperties.put("status", "stus_cd");
	}
 
	/**
	 * org.company.domain.SaleTimestamp is converted like this:
	 * SaleTimeStamp (unqualify)
	 * sale_timestamp (camel case to underscores)
	 * sl_tmstmp (remove vowels)
	 * t_sl_tmstmp (add T prefix)
	 * @param className
	 * @return
	 */
	public String classToTableName(String className) {
		return tableName(corporatify(StringHelper.unqualify(className)));
	}
	
	/**
	 * This method does nothing additional to an explicitly provided class name.
	 * Typically, {@link #propertyToColumnName(String)} is invoked (whenever the 
	 * column value is not explicitly defined).
	 * @param columnName
	 * @return
	 */
	public String columnName(String columnName) {
		return columnName;
	}
	
	/**
	 * Converts a property like this:
	 * org.company.domain.Sale.saleRegion is converted like this:
	 * saleRegion (unqualify)
	 * sale_region (camel case to underscores)
	 * sl_rgn (remove vowels)
	 * 
	 * Note that some standard properties are mapped automatically to their custom 
	 * keys.
	 * @param propertyName
	 * @return
	 */
	public String propertyToColumnName(String propertyName) {
		return (standardProperties.containsKey(propertyName)) ? 
				standardProperties.get(propertyName) : 
				columnName(corporatify(StringHelper.unqualify(propertyName)));
	}
	
	/**
	 * Explicitly entered table names are prefixed with the corporate standard 
	 * "t_". Note that this is only invoked directly if the table name is entered 
	 * in the mapping file for hibernate (in most cases {@link #classToTableName(String)} 
	 * will be used).
	 * @param tableName
	 * @return
	 */
	public String tableName(String tableName) {
		return /*"t_" +*/ tableName;
	}
	
	/**
	 * This method pulls out any of the corporate standard no-nos.
	 * 
	 * This was adapted from org.hibernate.cfg.ImprovedNamingStrategy.
	 * @param name
	 * @return
	 */
	private String corporatify(String name) {
		StringBuffer buf = new StringBuffer( name.replace('.', '_') );
		for (int i=1; i<buf.length()-1; i++) {
	/*		if(isVowel(buf.charAt(i))) {
				buf.deleteCharAt(i--);
			}
			else*/ if (
				'_'!=buf.charAt(i-1) &&
				Character.isUpperCase( buf.charAt(i) ) &&
				!Character.isUpperCase( buf.charAt(i+1) )
			) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase();
	}
	
	private boolean isVowel(char aChar) {
		boolean result = false;
		switch(aChar) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				result = true;
				break;
		}
		return result;
	}
}