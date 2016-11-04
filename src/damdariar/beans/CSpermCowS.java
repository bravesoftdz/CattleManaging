package damdariar.beans;

import java.util.Date;

public class CSpermCowS extends AbstractBean{
	
	private Integer CSpermCowSId;
	private Integer CBaseSpermId;
	private Date date;
	private String description;
	private Integer CPersonsalId;
	private Integer CPropertyId;
	
	static{
		IDMAP.put(CSpermCowS.class,"CBaseSpermId", CBaseSperm.class);
	}
	static{
		IDMAP.put(CSpermCowS.class,"CPersonsalId", CPersonal.class);
	}
	static{
		IDMAP.put(CSpermCowS.class,"CPropertyId", CProperty.class);
	}
	
	
	public CSpermCowS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CSpermCowS(Integer spermCowSId, Integer baseSpermId, Date date,
			String description, Integer personsalId, Integer propertyId) {
		super();
		CSpermCowSId = spermCowSId;
		CBaseSpermId = baseSpermId;
		this.date = date;
		this.description = description;
		CPersonsalId = personsalId;
		CPropertyId = propertyId;
	}
	
	
	public Integer getCSpermCowSId() {
		return CSpermCowSId;
	}
	public void setCSpermCowSId(Integer spermCowSId) {
		CSpermCowSId = spermCowSId;
	}
	public Integer getCBaseSpermId() {
		return CBaseSpermId;
	}
	public void setCBaseSpermId(Integer baseSpermId) {
		CBaseSpermId = baseSpermId;
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
	public Integer getCPersonsalId() {
		return CPersonsalId;
	}
	public void setCPersonsalId(Integer personsalId) {
		CPersonsalId = personsalId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	
}
