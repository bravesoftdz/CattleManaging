package damdariar.beans;

import java.util.Date;

public class CTalghih extends AbstractBean{
	
	private Integer CTalghihId;
	private Integer CPropertyId;
	private java.util.Date date;
	private Integer CPersoanlId;
	private String talghihType;
	private Integer CPropertyIdF;
	private Integer CBaseSpermId;
	private Integer dore;
	
	static{
		IDMAP.put(CTalghih.class,"CPropertyId", CProperty.class);
	}
	static{
		IDMAP.put(CTalghih.class,"CPersoanlId", CPersonal.class);
	}
	static{
		IDMAP.put(CTalghih.class,"CPropertyIdF", CProperty.class);
	}
	static{
		IDMAP.put(CTalghih.class,"CBaseSpermId", CBaseSperm.class);
	}
	static{
		IDMAP.put(CTalghih.class,"dore",CStatusBase.class);
	}
	public CTalghih() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getDore() {
		return dore;
	}


	public void setDore(Integer dore) {
		this.dore = dore;
	}


	public CTalghih(Integer talghihId, Integer propertyId, java.util.Date date,
			Integer persoanlId, String talghihType, Integer propertyIdF,
			Integer baseSpermId, Integer dore) {
		super();
		CTalghihId = talghihId;
		CPropertyId = propertyId;
		this.date = date;
		CPersoanlId = persoanlId;
		this.talghihType = talghihType;
		CPropertyIdF = propertyIdF;
		CBaseSpermId = baseSpermId;
		this.dore = dore;
	}


	public Integer getCTalghihId() {
		return CTalghihId;
	}
	public void setCTalghihId(Integer talghihId) {
		CTalghihId = talghihId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public Integer getCPersoanlId() {
		return CPersoanlId;
	}
	public void setCPersoanlId(Integer persoanlId) {
		CPersoanlId = persoanlId;
	}
	public String getTalghihType() {
		return talghihType;
	}
	public void setTalghihType(String talghihType) {
		this.talghihType = talghihType;
	}
	public Integer getCPropertyIdF() {
		return CPropertyIdF;
	}
	public void setCPropertyIdF(Integer propertyIdF) {
		CPropertyIdF = propertyIdF;
	}
	public Integer getCBaseSpermId() {
		return CBaseSpermId;
	}
	public void setCBaseSpermId(Integer baseSpermId) {
		CBaseSpermId = baseSpermId;
	}
	

}
