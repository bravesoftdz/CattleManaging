package damdariar.beans;

import java.util.Date;

public class CIll extends AbstractBean{
	
	private Integer CIllId;
	private Date startDate;
	private Date endDate;
	private Integer illNameId;
	private Integer CPropertyId;
	private Integer CPersonalId;
	private Date visitDate;
	private String description;
	private Boolean active;
	
	static{
		IDMAP.put(CIll.class,"illNameId", IllName.class);
	}
	static{
		IDMAP.put(CIll.class,"CPropertyId", CProperty.class);
	}
	static{
		IDMAP.put(CIll.class,"CPersonalId", CPersonal.class);
	}
	public CIll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CIll(Integer illId, Date startDate, Date endDate, Integer illNameId,
			Integer propertyId, Integer personalId, Date visitDate,
			String description, Boolean active) {
		super();
		CIllId = illId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.illNameId = illNameId;
		CPropertyId = propertyId;
		CPersonalId = personalId;
		this.visitDate = visitDate;
		this.description = description;
		this.active = active;
	}
	public Integer getCIllId() {
		return CIllId;
	}
	public void setCIllId(Integer illId) {
		CIllId = illId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getIllNameId() {
		return illNameId;
	}
	public void setIllNameId(Integer illNameId) {
		this.illNameId = illNameId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
