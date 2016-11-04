package damdariar.beans;

public class IllName extends AbstractBean{
	
	private Integer illNameId;
	private String name;
	private String description;
	
	public IllName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IllName(Integer illNameId, String name, String description) {
		super();
		this.illNameId = illNameId;
		this.name = name;
		this.description = description;
	}
	public Integer getillNameId() {
		return illNameId;
	}
	public void setIllNameId(Integer illNameId) {
		this.illNameId = illNameId;
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
