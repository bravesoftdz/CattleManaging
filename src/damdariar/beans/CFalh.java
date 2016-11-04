package damdariar.beans;

import java.sql.Date;

public class CFalh extends AbstractBean{
	
	private Integer CFalhId;
	private java.util.Date falhDate;
	private String falhType;
	private Integer CPropertyId;
	private String description;
	private Integer raftarFahliId;
	private Integer CPersonalId;
	
	static{
		IDMAP.put(CFalh.class,"CPropertyId", CProperty.class);
	}
	static{
		IDMAP.put(CFalh.class,"raftarFahliId", RaftarFahli.class);
	}
	static{
		IDMAP.put(CFalh.class,"CPersonalId", CPersonal.class);
	}
	public CFalh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CFalh(Integer falhId, java.util.Date falhDate, String falhType,
			Integer propertyId, String description, Integer raftarFahliId,
			Integer personalId) {
		super();
		CFalhId = falhId;
		this.falhDate = falhDate;
		this.falhType = falhType;
		CPropertyId = propertyId;
		this.description = description;
		this.raftarFahliId = raftarFahliId;
		CPersonalId = personalId;
	}

	public Integer getCFalhId() {
		return CFalhId;
	}

	public void setCFalhId(Integer falhId) {
		CFalhId = falhId;
	}

	public java.util.Date getFalhDate() {
		return falhDate;
	}

	public void setFalhDate(java.util.Date falhDate) {
		this.falhDate = falhDate;
	}

	public String getFalhType() {
		return falhType;
	}

	public void setFalhType(String falhType) {
		this.falhType = falhType;
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

	public Integer getRaftarFahliId() {
		return raftarFahliId;
	}

	public void setRaftarFahliId(Integer raftarFahliId) {
		this.raftarFahliId = raftarFahliId;
	}

	public Integer getCPersonalId() {
		return CPersonalId;
	}

	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	
}
