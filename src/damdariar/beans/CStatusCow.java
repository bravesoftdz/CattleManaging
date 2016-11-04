package damdariar.beans;

import java.util.Date;

public class CStatusCow extends AbstractBean {
	
	private Integer CStatusCowId;
	private Integer CPropertyId;
	private Integer CStatusBaseId;
	private Date startDate;
	private Date endDate;
	private String description;
	private Boolean active;

	
	static{
		IDMAP.put(CStatusCow.class,"CPropertyId", CProperty.class);
	}
	
	static{
		IDMAP.put(CStatusCow.class,"CStatusBaseId",CStatusBase.class);
	}
   
	public CStatusCow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CStatusCow(Integer statusCowId, Integer propertyId,
			Integer statusBaseId, Date startDate, Date endDate,
			String description, Boolean active) {
		super();
		CStatusCowId = statusCowId;
		CPropertyId = propertyId;
		CStatusBaseId = statusBaseId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.active = active;
	}
	public Integer getCStatusCowId() {
		return CStatusCowId;
	}
	public void setCStatusCowId(Integer statusCowId) {
		CStatusCowId = statusCowId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public Integer getCStatusBaseId() {
		return CStatusBaseId;
	}
	public void setCStatusBaseId(Integer statusBaseId) {
		CStatusBaseId = statusBaseId;
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
