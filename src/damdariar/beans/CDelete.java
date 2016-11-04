package damdariar.beans;

import java.util.Date;

public class CDelete extends AbstractBean{
	
	Integer CDeleteId;
	Integer CPropertyId;
	Integer CPersonalId;
	Date date;
	String description;
	Integer CExitReasonId;
	
	static{
		IDMAP.put(CDelete.class, "CPropertyId", CProperty.class);
	}
	
	static{
		IDMAP.put(CDelete.class,"CPersonalId", CPersonal.class);
	}
	
	static{
		IDMAP.put(CDelete.class,"CExitReasonId", CExitReason.class);
	}
	public CDelete() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CDelete(Integer deleteId, Integer propertyId, Integer personalId,
			Date date, String description, Integer exitReasonId) {
		super();
		CDeleteId = deleteId;
		CPropertyId = propertyId;
		CPersonalId = personalId;
		this.date = date;
		this.description = description;
		CExitReasonId = exitReasonId;
	}
	public Integer getCDeleteId() {
		return CDeleteId;
	}
	public void setCDeleteId(Integer deleteId) {
		CDeleteId = deleteId;
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
	public Integer getCExitReasonId() {
		return CExitReasonId;
	}
	public void setCExitReasonId(Integer exitReasonId) {
		CExitReasonId = exitReasonId;
	}
	
}
