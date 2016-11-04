package damdariar.beans;

public class StatusJoft extends AbstractBean{
	
	Integer statusJoftId;
	String name;
	String description;
	
	public StatusJoft() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StatusJoft(Integer statusJoftId, String name, String description) {
		super();
		this.statusJoftId = statusJoftId;
		this.name = name;
		this.description = description;
	}
	
	public Integer getstatusJoftId() {
		return statusJoftId;
	}
	public void setStatusJoftId(Integer statusJoftId) {
		this.statusJoftId = statusJoftId;
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
