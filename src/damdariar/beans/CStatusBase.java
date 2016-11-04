package damdariar.beans;

public class CStatusBase extends AbstractBean{
	
	Integer CStatusBaseId;
	String statusName;
	String description;
	
	public CStatusBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CStatusBase(Integer statusBaseId, String statusName,
			String description) {
		super();
		CStatusBaseId = statusBaseId;
		this.statusName = statusName;
		this.description = description;
	}
	
	public Integer getCStatusBaseId() {
		return CStatusBaseId;
	}
	public void setCStatusBaseId(Integer statusBaseId) {
		CStatusBaseId = statusBaseId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
