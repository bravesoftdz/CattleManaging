package damdariar.beans;

public class CDaro extends AbstractBean{
	
	private Integer CDaroId;
	private String name;
	private String description;
	
	public CDaro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CDaro(Integer daroId, String name, String description) {
		super();
		CDaroId = daroId;
		this.name = name;
		this.description = description;
	}
	public Integer getCDaroId() {
		return CDaroId;
	}
	public void setCDaroId(Integer daroId) {
		CDaroId = daroId;
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
