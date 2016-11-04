package damdariar.beans;

public class RaftarFahli extends AbstractBean{
	
	private Integer raftarFahliId;
	private String name;
	private String description;

	public RaftarFahli() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RaftarFahli(Integer raftarFahliId, String name, String description) {
		super();
		this.raftarFahliId = raftarFahliId;
		this.name = name;
		this.description = description;
	}
	public Integer getraftarFahliId() {
		return raftarFahliId;
	}
	public void setRaftarFahliId(Integer raftarFahliId) {
		this.raftarFahliId = raftarFahliId;
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
