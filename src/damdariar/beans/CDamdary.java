package damdariar.beans;

public class CDamdary extends AbstractBean{
	
	private Integer CDamdaryId;
	private String nameMalek;
	private String address;
	private String nameDamdary;
	
	public CDamdary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CDamdary(Integer damdaryId, String nameMalek, String adress,
			String nameDamdary) {
		super();
		CDamdaryId = damdaryId;
		this.nameMalek = nameMalek;
		this.address = adress;
		this.nameDamdary = nameDamdary;
	}
	public Integer getCDamdaryId() {
		return CDamdaryId;
	}
	public void setCDamdaryId(Integer damdaryId) {
		CDamdaryId = damdaryId;
	}
	public String getNameMalek() {
		return nameMalek;
	}
	public void setNameMalek(String nameMalek) {
		this.nameMalek = nameMalek;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public String getNameDamdary() {
		return nameDamdary;
	}
	public void setNameDamdary(String nameDamdary) {
		this.nameDamdary = nameDamdary;
	} 
	
}
