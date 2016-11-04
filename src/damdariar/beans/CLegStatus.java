package damdariar.beans;

public class CLegStatus extends AbstractBean {
	
	private Integer CLegStatusId;
	private String name;
	private String description;
	
	public CLegStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CLegStatus(Integer legStatusId, String name, String description) {
		super();
		CLegStatusId = legStatusId;
		this.name = name;
		this.description = description;
	}
	public Integer getCLegStatusId() {
		return CLegStatusId;
	}
	public void setCLegStatusId(Integer legStatusId) {
		CLegStatusId = legStatusId;
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
