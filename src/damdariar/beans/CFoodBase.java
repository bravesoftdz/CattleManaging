package damdariar.beans;

public class CFoodBase extends AbstractBean{
	
	
	private Integer CFoodBaseId;
	private String foodName;
	private String description;
	
	
	public CFoodBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CFoodBase(Integer foodBaseId, String foodName, String description) {
		super();
		CFoodBaseId = foodBaseId;
		this.foodName = foodName;
		this.description = description;
	}
	
	
	public Integer getCFoodBaseId() {
		return CFoodBaseId;
	}
	public void setCFoodBaseId(Integer foodBaseId) {
		CFoodBaseId = foodBaseId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
}
