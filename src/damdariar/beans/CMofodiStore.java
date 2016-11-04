package damdariar.beans;

import java.util.Date;

public class CMofodiStore extends AbstractBean{
	
	private Integer CMofodiStoreId;
	private Date date;
	private Float weight;
	private Integer CFoodBaseId;
	private String description;
	private Integer CFoodExitId;
	
	
	static{
		IDMAP.put(CMofodiStore.class,"CFoodBaseId", CFoodBase.class);
	}
	static{
		IDMAP.put(CMofodiStore.class,"CFoodExitId", CFoodExit.class);
	}
	
	public CMofodiStore() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CMofodiStore(Integer mofodiStoreId, Date date, Float weight,
			Integer foodBaseId, String description, Integer foodExitId) {
		super();
		CMofodiStoreId = mofodiStoreId;
		this.date = date;
		this.weight = weight;
		CFoodBaseId = foodBaseId;
		this.description = description;
		CFoodExitId = foodExitId;
	}

	public Integer getCFoodExitId() {
		return CFoodExitId;
	}

	public void setCFoodExitId(Integer foodExitId) {
		CFoodExitId = foodExitId;
	}

	public Integer getCMofodiStoreId() {
		return CMofodiStoreId;
	}
	public void setCMofodiStoreId(Integer mofodiStoreId) {
		CMofodiStoreId = mofodiStoreId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
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
	

}
