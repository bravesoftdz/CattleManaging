package damdariar.beans;

import java.sql.Date;

public class CDaroStor extends AbstractBean{
	
	private Integer CDaroStorId;
	private Integer CDaroId;
	private Date date;
	private Float priceUnit;
	private Integer numberUnit;
	private String producted;
	private Integer CPersonalId;
	private Boolean acrive;
	
	static{
		IDMAP.put(CDaroStor.class,"CDaroId", CDaro.class);
	}
	
	static{
		IDMAP.put(CDaroStor.class,"CPersonalId", CDaro.class);
	}
	public CDaroStor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CDaroStor(Integer daroStorId, Integer daroId, Date date,
			Float priceUnit, Integer numberUnit, String producted,
			Integer personalId, Boolean acrive) {
		super();
		CDaroStorId = daroStorId;
		CDaroId = daroId;
		this.date = date;
		this.priceUnit = priceUnit;
		this.numberUnit = numberUnit;
		this.producted = producted;
		CPersonalId = personalId;
		this.acrive = acrive;
	}
	public Integer getCDaroStorId() {
		return CDaroStorId;
	}
	public void setCDaroStorId(Integer daroStorId) {
		CDaroStorId = daroStorId;
	}
	public Integer getCDaroId() {
		return CDaroId;
	}
	public void setCDaroId(Integer daroId) {
		CDaroId = daroId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(Float priceUnit) {
		this.priceUnit = priceUnit;
	}
	public Integer getNumberUnit() {
		return numberUnit;
	}
	public void setNumberUnit(Integer numberUnit) {
		this.numberUnit = numberUnit;
	}
	public String getProducted() {
		return producted;
	}
	public void setProducted(String producted) {
		this.producted = producted;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public Boolean getAcrive() {
		return acrive;
	}
	public void setAcrive(Boolean acrive) {
		this.acrive = acrive;
	}
	 
}
