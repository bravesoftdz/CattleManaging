package damdariar.beans;

public class ReasonZayemanStatus extends AbstractBean{
	
	Integer reasonZayemanStatusId;
	String name;
	String description;
	
	public ReasonZayemanStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReasonZayemanStatus(Integer reasonZayemanStatusId, String name,
			String description) {
		super();
		this.reasonZayemanStatusId = reasonZayemanStatusId;
		this.name = name;
		this.description = description;
	}
	
	public Integer getreasonZayemanStatusId() {
		return reasonZayemanStatusId;
	}
	public void setReasonZayemanStatusId(Integer reasonZayemanStatusId) {
		this.reasonZayemanStatusId = reasonZayemanStatusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
