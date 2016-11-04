package damdariar.beans;

import java.util.Date;

public class CStore extends AbstractBean{
	
	
	private Integer CStoreId;
	private Date storeDate;
	private Float foodWeight;
	private Integer CFoodBaseId;
	private String description;
	private Float price;
	private Integer CPersonalId;
	
	static{
		IDMAP.put(CStore.class,"CFoodBaseId", CFoodBase.class);
	}
	static{
		IDMAP.put(CStore.class, "CPersonalId", CPersonal.class);
	}
	
	public CStore() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CStore(Integer storeId, Date storeDate, Float foodWeight,
			Integer foodBaseId, String description, Float price,
			Integer personalId) {
		super();
		CStoreId = storeId;
		this.storeDate = storeDate;
		this.foodWeight = foodWeight;
		CFoodBaseId = foodBaseId;
		this.description = description;
		this.price = price;
		CPersonalId = personalId;
	}
	public Integer getCStoreId() {
		return CStoreId;
	}
	public void setCStoreId(Integer storeId) {
		CStoreId = storeId;
	}
	public Date getStoreDate() {
		return storeDate;
	}
	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}
	public Float getFoodWeight() {
		return foodWeight;
	}
	public void setFoodWeight(Float foodWeight) {
		this.foodWeight = foodWeight;
	}
	public Integer getCFoodBaseId() {
		return CFoodBaseId;
	}
	public void setCFoodBaseId(Integer foodBaseId) {
		CFoodBaseId = foodBaseId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	
}
