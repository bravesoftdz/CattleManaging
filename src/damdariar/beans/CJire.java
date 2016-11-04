package damdariar.beans;

import java.sql.Date;

public class CJire extends AbstractBean{
	
	
	private Integer CJireId;
	private String jireName;
	private Integer CPersonalId;
	private java.util.Date jireDate;
	private String description;
	
	static{
		IDMAP.put(CJire.class,"CPersonalId", CPersonal.class);
	}
	
	public CJire() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CJire(Integer jireId, String jireName, Integer personalId,
			java.util.Date jireDate, String description) {
		super();
		CJireId = jireId;
		this.jireName = jireName;
		CPersonalId = personalId;
		this.jireDate = jireDate;
		this.description = description;
	}
	public Integer getCJireId() {
		return CJireId;
	}
	public void setCJireId(Integer jireId) {
		CJireId = jireId;
	}
	public String getJireName() {
		return jireName;
	}
	public void setJireName(String jireName) {
		this.jireName = jireName;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public java.util.Date getJireDate() {
		return jireDate;
	}
	public void setJireDate(java.util.Date jireDate) {
		this.jireDate = jireDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 

}
