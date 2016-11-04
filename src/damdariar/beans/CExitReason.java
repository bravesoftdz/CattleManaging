package damdariar.beans;

public class CExitReason extends AbstractBean{
	
	
	Integer CExitReasonId;
	String reasonName;
	String description;
	
	
	public CExitReason() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CExitReason(Integer exitReasonId, String reasonName,
			String description) {
		super();
		CExitReasonId = exitReasonId;
		this.reasonName = reasonName;
		this.description = description;
	}
	
	
	public Integer getCExitReasonId() {
		return CExitReasonId;
	}
	public void setCExitReasonId(Integer exitReasonId) {
		CExitReasonId = exitReasonId;
	}
	public String getReasonName() {
		return reasonName;
	}
	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
