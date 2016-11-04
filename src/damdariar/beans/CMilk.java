package damdariar.beans;

import java.util.Date;

public class CMilk extends AbstractBean{
	
	
	private Integer CMilkId;
	private Date milkDate;
	private Integer CPropertyId;
	private Float milkLiterS;
	private Float milkLiterZ;
	private Float milkLiterSh;
	private Float milkTotal;
	private String description;
	private Integer CPersonalId;
	private Boolean active;
	
	
	
	
	static{
		IDMAP.put(CMilk.class,"CPropertyId", CProperty.class);
	}
	
	static{
		IDMAP.put(CMilk.class,"CPersonalId", CPersonal.class);
	}
	
	
	public CMilk() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CMilk(Integer milkId, Date milkDate, Integer propertyId,
			Float milkLiterS,Float milkLiterZ,Float milkLiterSh,Float milkTotal, String description, Integer personalId,
			Boolean active) {
		super();
		CMilkId = milkId;
		this.milkDate = milkDate;
		CPropertyId = propertyId;
		this.milkLiterS = milkLiterS;
		this.milkLiterZ = milkLiterZ;
		this.milkLiterSh = milkLiterSh;
		this.milkTotal = milkTotal;
		this.description = description;
		CPersonalId = personalId;
		this.active = active;
	}
	
	
	public Float getMilkLiterS() {
		return milkLiterS;
	}
	public void setMilkLiterS(Float milkLiterS) {
		this.milkLiterS = milkLiterS;
	}
	public Float getMilkLiterZ() {
		return milkLiterZ;
	}
	public void setMilkLiterZ(Float milkLiterZ) {
		this.milkLiterZ = milkLiterZ;
	}
	public Float getMilkLiterSh() {
		return milkLiterSh;
	}
	public void setMilkLiterSh(Float milkLiterSh) {
		this.milkLiterSh = milkLiterSh;
	}
	public Float getMilkTotal() {
		return milkTotal;
	}
	public void setMilkTotal(Float milkTotal) {
		this.milkTotal = milkTotal;
	}
	public Integer getCMilkId() {
		return CMilkId;
	}
	public void setCMilkId(Integer milkId) {
		CMilkId = milkId;
	}
	public Date getMilkDate() {
		return milkDate;
	}
	public void setMilkDate(Date milkDate) {
		this.milkDate = milkDate;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
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
