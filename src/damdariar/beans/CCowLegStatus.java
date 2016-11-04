package damdariar.beans;

import java.util.Date;

public class CCowLegStatus extends AbstractBean{
	
	private Integer CCowLegStatusId;
	private Integer CPropertyId;
	private String legName;
	private Date dateStart;
	private Date dateEnd;
	private Integer CLegStatusId;
	private Boolean active;
	
	static{
		IDMAP.put(CCowLegStatus.class,"CPropertyId", CProperty.class);
	}
	static{
		IDMAP.put(CCowLegStatus.class,"CLegStatusId", CLegStatus.class);
	}
	
	public CCowLegStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CCowLegStatus(Integer cowLegStatusId, Integer propertyId,
			String legName, Date dateStart, Date dateEnd, Integer legStatusId,
			Boolean active) {
		super();
		CCowLegStatusId = cowLegStatusId;
		CPropertyId = propertyId;
		this.legName = legName;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		CLegStatusId = legStatusId;
		this.active = active;
	}
	public Integer getCCowLegStatusId() {
		return CCowLegStatusId;
	}
	public void setCCowLegStatusId(Integer cowLegStatusId) {
		CCowLegStatusId = cowLegStatusId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public String getLegName() {
		return legName;
	}
	public void setLegName(String legName) {
		this.legName = legName;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getCLegStatusId() {
		return CLegStatusId;
	}
	public void setCLegStatusId(Integer legStatusId) {
		CLegStatusId = legStatusId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

}
