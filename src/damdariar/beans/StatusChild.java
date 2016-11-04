package damdariar.beans;

public class StatusChild extends AbstractBean{
	Integer statusChildId;
	String name;
	String description;
	public StatusChild() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatusChild(Integer statusChildId, String name, String description) {
		super();
		this.statusChildId = statusChildId;
		this.name = name;
		this.description = description;
	}
	public Integer getstatusChildId() {
		return statusChildId;
	}
	public void setStatusChildId(Integer statusChildId) {
		this.statusChildId = statusChildId;
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
