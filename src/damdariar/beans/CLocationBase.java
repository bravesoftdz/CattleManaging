package damdariar.beans;

public class CLocationBase extends AbstractBean{
	
	private Integer CLocationBaseId;
	private String locationName;
	private String locationNumber;
	private String description;
	private Float lengh;
	private Float wide;
	private Float SWide;
	private Float SLengh;
	
	
	public CLocationBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CLocationBase(Integer locationBaseId, String locationName,
			String locationNumber, String description, Float lengh, Float wide,
			Float wide2, Float lengh2) {
		super();
		CLocationBaseId = locationBaseId;
		this.locationName = locationName;
		this.locationNumber = locationNumber;
		this.description = description;
		this.lengh = lengh;
		this.wide = wide;
		SWide = wide2;
		SLengh = lengh2;
	}
	public Integer getCLocationBaseId() {
		return CLocationBaseId;
	}
	public void setCLocationBaseId(Integer locationBaseId) {
		CLocationBaseId = locationBaseId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationNumber() {
		return locationNumber;
	}
	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getLengh() {
		return lengh;
	}
	public void setLengh(Float lengh) {
		this.lengh = lengh;
	}
	public Float getWide() {
		return wide;
	}
	public void setWide(Float wide) {
		this.wide = wide;
	}
	public Float getSWide() {
		return SWide;
	}
	public void setSWide(Float wide) {
		SWide = wide;
	}
	public Float getSLengh() {
		return SLengh;
	}
	public void setSLengh(Float lengh) {
		SLengh = lengh;
	}
	
}
