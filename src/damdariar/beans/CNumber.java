package damdariar.beans;

import java.util.Date;

public class CNumber extends AbstractBean{
	
	private Integer CNumberId;
	private Integer CPropertyId;
	private Date startDate;
	private Date endDate;
	private String number;
	private String description;
	private Integer CPersonalId;
	private Boolean active;
	
	static{
		IDMAP.put(CNumber.class,"CPropertyId", CProperty.class);
	}
	
	static{
		IDMAP.put(CNumber.class,"CPersonalId", CPersonal.class);
	}
	public CNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CNumber(Integer numberId, Integer propertyId, Date startDate,
			Date endDate, String number, String description,
			Integer personalId, Boolean active) {
		super();
		CNumberId = numberId;
		CPropertyId = propertyId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.number = number;
		this.description = description;
		CPersonalId = personalId;
		this.active = active;
	}
	public Integer getCNumberId() {
		return CNumberId;
	}
	public void setCNumberId(Integer numberId) {
		CNumberId = numberId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

}
