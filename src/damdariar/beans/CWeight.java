package damdariar.beans;

import java.util.Date;

public class CWeight extends AbstractBean {
	
	private Integer CWeightId;
	private Float cowWieght;
	private Float cowLengh;
	private Date date;
	private Integer CPropertyId;
	private String description;
	
	
	static{
		IDMAP.put(CWeight.class,"CPropertyId", CProperty.class);
	}
	
	public CWeight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CWeight(Integer weightId, Float cowWieght, Float cowLengh,
			Date date, String description, Integer propertyId) {
		super();
		CWeightId = weightId;
		this.cowWieght = cowWieght;
		this.cowLengh = cowLengh;
		this.date = date;
		this.description = description;
		CPropertyId = propertyId;
	}
	public Integer getCWeightId() {
		return CWeightId;
	}
	public void setCWeightId(Integer weightId) {
		CWeightId = weightId;
	}
	public Float getCowWieght() {
		return cowWieght;
	}
	public void setCowWieght(Float cowWieght) {
		this.cowWieght = cowWieght;
	}
	public Float getCowLengh() {
		return cowLengh;
	}
	public void setCowLengh(Float cowLengh) {
		this.cowLengh = cowLengh;
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
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	

}
