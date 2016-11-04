package damdariar.beans;

import java.util.Date;

public class CCowLeg extends AbstractBean{
	
	
	private Integer CCowLegId;
	private Integer CPropertyId;
	private Date date;
	private String description;
	private Integer CPersonalId;
	
	static{
		IDMAP.put(CCowLeg.class,"CPropertyId", CProperty.class);
	}
	static{
		IDMAP.put(CCowLeg.class,"CPersonalId", CPersonal.class);
	}
	public CCowLeg(Integer cowLegId, Integer propertyId, Date date,
			String description, Integer personalId) {
		super();
		CCowLegId = cowLegId;
		CPropertyId = propertyId;
		this.date = date;
		this.description = description;
		CPersonalId = personalId;
	}
	public CCowLeg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCCowLegId() {
		return CCowLegId;
	}
	public void setCCowLegId(Integer cowLegId) {
		CCowLegId = cowLegId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	
	
}
