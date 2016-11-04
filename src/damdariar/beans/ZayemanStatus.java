package damdariar.beans;

public class ZayemanStatus extends AbstractBean{
	
	Integer zayemanStatusId;
	String name;
	String description;
	
	public ZayemanStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ZayemanStatus(Integer zayemanStatusId, String name,
			String description) {
		
		super();
		this.zayemanStatusId = zayemanStatusId;
		this.name = name;
		this.description = description;
	}
	
	public Integer getzayemanStatusId() {
		return zayemanStatusId;
	}
	public void setZayemanStatusId(Integer zayemanStatusId) {
		this.zayemanStatusId = zayemanStatusId;
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
