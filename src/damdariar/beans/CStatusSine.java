package damdariar.beans;

public class CStatusSine extends AbstractBean {
	
	
	private Integer CStatusSineId;
	private String name;
	private String description;
	
	
	public CStatusSine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CStatusSine(Integer statusSineId, String name, String description) {
		super();
		CStatusSineId = statusSineId;
		this.name = name;
		this.description = description;
	}
	public Integer getCStatusSineId() {
		return CStatusSineId;
	}
	public void setCStatusSineId(Integer statusSineId) {
		CStatusSineId = statusSineId;
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
