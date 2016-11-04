package damdariar.beans;

import java.util.Date;

public class CMilkCunsumer extends AbstractBean{
	
	private Integer CMilkCunsumerId;
	private Integer CPersonalId;
	private Float consumeWeight;
	private Date consumeDate;
	private String description;
	
	static{
		IDMAP.put(CMilkCunsumer.class,"CPersonalId", CPersonal.class);
	}
	public CMilkCunsumer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CMilkCunsumer(Integer milkCunsumerId, Integer personalId,
			Float consumeWeight, Date consumeDate, String description) {
		super();
		CMilkCunsumerId = milkCunsumerId;
		CPersonalId = personalId;
		this.consumeWeight = consumeWeight;
		this.consumeDate = consumeDate;
		this.description = description;
	}
	public Integer getCMilkCunsumerId() {
		return CMilkCunsumerId;
	}
	public void setCMilkCunsumerId(Integer milkCunsumerId) {
		CMilkCunsumerId = milkCunsumerId;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public Float getConsumeWeight() {
		return consumeWeight;
	}
	public void setConsumeWeight(Float consumeWeight) {
		this.consumeWeight = consumeWeight;
	}
	public Date getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
