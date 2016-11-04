package damdariar.beans;

public class CBaseSperm extends AbstractBean{
	
	
	Integer CBaseSpermId;
	String name;
	String description;
	String number;
	String producted;
	Float percent;	
	
	public CBaseSperm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CBaseSperm(Integer baseSpermId, String name, String description,
			String number, String producted, Float percent, Integer dore) {
		super();
		CBaseSpermId = baseSpermId;
		this.name = name;
		this.description = description;
		this.number = number;
		this.producted = producted;
		this.percent = percent;
	
	}
    public Integer getCBaseSpermId() {
		return CBaseSpermId;
	}
	public void setCBaseSpermId(Integer baseSpermId) {
		CBaseSpermId = baseSpermId;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getProducted() {
		return producted;
	}
	public void setProducted(String producted) {
		this.producted = producted;
	}
	public Float getPercent() {
		return percent;
	}
	public void setPercent(Float percent) {
		this.percent = percent;
	}
	 
}
