package damdariar.beans;


public class CGuiProperty extends AbstractBean{
	
	public CGuiProperty(){
		super();
	}
	public CGuiProperty(Integer guiPropertyId, String fontName,
			String colorName, String className,String fontColor,String password) {
		super();
		CGuiPropertyId = guiPropertyId;
		this.fontName = fontName;
		this.colorName = colorName;
		this.className = className;
		this.fontColor = fontColor;
		this.password = password;
	}
	private Integer CGuiPropertyId;
	private String fontName;
	private String colorName;
	private String className;
	private String fontColor;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public Integer getCGuiPropertyId() {
		return CGuiPropertyId;
	}
	public void setCGuiPropertyId(Integer guiPropertyId) {
		CGuiPropertyId = guiPropertyId;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	
}
