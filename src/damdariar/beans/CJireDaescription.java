package damdariar.beans;

public class CJireDaescription extends AbstractBean{
	
	private Integer CJireDescriptionId;
	private Integer CJireId;
	private Integer CFoodBaseId;
	private Float foodWeight;
	private String description;
	
	static{
		IDMAP.put(CJireDaescription.class,"CJireId", CJire.class);
	}
	static{
		IDMAP.put(CJireDaescription.class,"CFoodBaseId", CFoodBase.class);
	}
	
	public CJireDaescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CJireDaescription(Integer jireDescriptionId, Integer jireId,
			Integer foodBaseId, Float foodWeight, String description) {
		super();
		CJireDescriptionId = jireDescriptionId;
		CJireId = jireId;
		CFoodBaseId = foodBaseId;
		this.foodWeight = foodWeight;
		this.description = description;
	}
	
	public Integer getCJireDescriptionId() {
		return CJireDescriptionId;
	}
	public void setCJireDescriptionId(Integer jireDescriptionId) {
		CJireDescriptionId = jireDescriptionId;
	}
	public Integer getCJireId() {
		return CJireId;
	}
	public void setCJireId(Integer jireId) {
		CJireId = jireId;
	}
	public Integer getCFoodBaseId() {
		return CFoodBaseId;
	}
	public void setCFoodBaseId(Integer foodBaseId) {
		CFoodBaseId = foodBaseId;
	}
	public Float getFoodWeight() {
		return foodWeight;
	}
	public void setFoodWeight(Float foodWeight) {
		this.foodWeight = foodWeight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
}
