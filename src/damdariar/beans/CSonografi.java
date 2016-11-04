package damdariar.beans;

import java.util.Date;

public class CSonografi extends AbstractBean {
	
	private Integer CSonografiId;
	private Integer CPropertyId;
	private Date date;
	private Integer CPersonalId; 
	private String description;
	
	static{
		IDMAP.put(CSonografi.class,"CPropertyId", CProperty.class);
	}
	
	static{
		IDMAP.put(CSonografi.class,"CPersonalId",CPersonal.class);
	}
	
	public CSonografi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CSonografi(Integer sonografiId, Integer propertyId, Date date,
			Integer personalId, String description) {
		super();
		CSonografiId = sonografiId;
		CPropertyId = propertyId;
		this.date = date;
		CPersonalId = personalId;
		this.description = description;
	}
	public Integer getCSonografiId() {
		return CSonografiId;
	}
	public void setCSonografiId(Integer sonografiId) {
		CSonografiId = sonografiId;
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
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
}
