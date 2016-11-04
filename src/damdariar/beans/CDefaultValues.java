package damdariar.beans;

import java.sql.Blob;

/**
 * CPic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CDefaultValues extends AbstractBean implements java.io.Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CDefaultValues(){
		super();
	}
	public CDefaultValues(String className, String editorName, Blob defaultValue) {
		super();
		this.className = className;
		this.editorName = editorName;
		this.defaultValue = defaultValue;
	}
	public CDefaultValues(Integer defaultValuesId, String className,
			String editorName, Blob defaultValue) {
		super();
		CDefaultValuesId = defaultValuesId;
		this.className = className;
		this.editorName = editorName;
		this.defaultValue = defaultValue;
	}
	private Integer CDefaultValuesId;
	private String  className;
	private String  editorName;
	private Blob    defaultValue;
	public Integer getCDefaultValuesId() {
		return CDefaultValuesId;
	}
	public void setCDefaultValuesId(Integer defaultValuesId) {
		CDefaultValuesId = defaultValuesId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getEditorName() {
		return editorName;
	}
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	public Blob getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Blob defaultValue) {
		this.defaultValue = defaultValue;
	}
	


}