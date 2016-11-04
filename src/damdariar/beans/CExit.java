package damdariar.beans;

import java.util.Date;

public class CExit extends AbstractBean{
	
	private Integer CExitId;
	private Integer CPropertyId;
	private Integer CExitReasonId;
	private Date date;
	private String description;
	private Float price;
	private Integer CPersonalId;
	private Integer CPersonalIdKh;
	
	static {
		IDMAP.put(CExit.class,"CPropertyId",CProperty.class);
	}
	
	
	static{
		IDMAP.put(CExit.class,"CPersonalId",CPersonal.class);
		IDMAP.put(CExit.class,"CPersonalIdKh",CPersonal.class);
	}
	
	
	static{
		IDMAP.put(CExit.class,"CExitReasonId", CExitReason.class);
	}
	
	
	public CExit(Integer exitId, Integer propertyId, Integer exitReasonId,
			Date date, String description, Float price, Integer personalId,Integer CPersonalIdKh) {
		super();
		CExitId = exitId;
		CPropertyId = propertyId;
		CExitReasonId = exitReasonId;
		this.date = date;
		this.description = description;
		this.price = price;
		this.CPersonalIdKh = CPersonalIdKh;
		CPersonalId = personalId;
	}
	public CExit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getCPersonalIdKh() {
		return CPersonalIdKh;
	}
	public void setCPersonalIdKh(Integer personalIdKh) {
		CPersonalIdKh = personalIdKh;
	}
	public Integer getCExitId() {
		return CExitId;
	}
	public void setCExitId(Integer exitId) {
		CExitId = exitId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public Integer getCExitReasonId() {
		return CExitReasonId;
	}
	public void setCExitReasonId(Integer exitReasonId) {
		CExitReasonId = exitReasonId;
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}


}
