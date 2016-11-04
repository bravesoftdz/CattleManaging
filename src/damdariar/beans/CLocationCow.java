package damdariar.beans;

import java.util.Date;

public class CLocationCow extends AbstractBean{
	
	private Integer CLocationCowId;
	private Integer CPropertyId;
	private Integer CLocationBaseId;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private String description;
	private Integer CPersonalId;
	private Integer CMoveLocationReasonId;
	private Boolean active;
	
	static{
		IDMAP.put(CLocationCow.class,"CPropertyId", CProperty.class);
	}
	static{
		IDMAP.put(CLocationCow.class,"CLocationBaseId", CLocationBase.class);
	}
	static{
		IDMAP.put(CLocationCow.class,"CPersonalId", CPersonal.class);
	}
	static{
		IDMAP.put(CLocationCow.class,"CMoveLocationReasonId", CMoveLocationReason.class);
	}
	public CLocationCow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CLocationCow(Integer locationCowId, Integer propertyId,
			Integer locationBaseId, java.util.Date startDate, java.util.Date endDate,
			String description, Integer personalId,
			Integer moveLocationReasonId,Boolean  active) {
		super();
		CLocationCowId = locationCowId;
		CPropertyId = propertyId;
		CLocationBaseId = locationBaseId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		CPersonalId = personalId;
		CMoveLocationReasonId = moveLocationReasonId;
		this.active = active;
	}
	public Integer getCLocationCowId() {
		return CLocationCowId;
	}
	public void setCLocationCowId(Integer locationCowId) {
		CLocationCowId = locationCowId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public Integer getCLocationBaseId() {
		return CLocationBaseId;
	}
	public void setCLocationBaseId(Integer locationBaseId) {
		CLocationBaseId = locationBaseId;
	}
	public java.util.Date getstartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public Integer getCMoveLocationReasonId() {
		return CMoveLocationReasonId;
	}
	public void setCMoveLocationReasonId(Integer moveLocationReasonId) {
		CMoveLocationReasonId = moveLocationReasonId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
