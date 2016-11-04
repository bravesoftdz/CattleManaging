package damdariar.beans;

import java.util.Date;

public class CSperm extends AbstractBean{
	
	private Integer CSpermId;
	private Float spermPrice;
	private Integer spermNumber;
	private String description;
	private Integer CBaseSpermId;
	private Integer CPersonalId;
	private Date date;
	
	static{
	   IDMAP.put(CSperm.class, "CPersonalId", CPersonal.class);	
	}
	static{
		IDMAP.put(CSperm.class, "CBaseSpermId", CBaseSperm.class);
	}
	
	public CSperm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CSperm(Integer spermId, Float spermPrice, Integer spermNumber,
			String description, Integer baseSpermId, Integer personalId, Date date) {
		super();
		CSpermId = spermId;
		this.spermPrice = spermPrice;
		this.spermNumber = spermNumber;
		this.description = description;
		CBaseSpermId = baseSpermId;
		CPersonalId = personalId;
		this.date = date ;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCSpermId() {
		return CSpermId;
	}
	public void setCSpermId(Integer spermId) {
		CSpermId = spermId;
	}
	public Float getSpermPrice() {
		return spermPrice;
	}
	public void setSpermPrice(Float spermPrice) {
		this.spermPrice = spermPrice;
	}
	public Integer getSpermNumber() {
		return spermNumber;
	}
	public void setSpermNumber(Integer spermNumber) {
		this.spermNumber = spermNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCBaseSpermId() {
		return CBaseSpermId;
	}
	public void setCBaseSpermId(Integer baseSpermId) {
		CBaseSpermId = baseSpermId;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	 
}
