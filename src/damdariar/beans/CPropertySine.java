package damdariar.beans;

import java.util.Date;

public class CPropertySine extends AbstractBean{
	 
	private Integer CPropertySineId;
	private Integer CStatusSineId;
	private Integer bodyScore;
	private Date date;
	private Integer CPropertyId;
	private Integer scoreMove;
	private String description;
	private Integer CPersonalId;
	private Boolean active;
	
	static{
		IDMAP.put(CPropertySine.class,"CStatusSineId", CStatusSine.class);
	}
	static{
		IDMAP.put(CPropertySine.class,"CPropertyId", CProperty.class); 
	}
	static{
		IDMAP.put(CPropertySine.class,"CPersonalId", CPersonal.class);
	}
	
   
	public CPropertySine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CPropertySine(Integer propertySineId, Integer statusSineId,
			Integer bodyScore, Date date, Integer propertyId,
			Integer scoreMove, String description, Integer personalId,
			Boolean active) {
		super();
		CPropertySineId = propertySineId;
		CStatusSineId = statusSineId;
		this.bodyScore = bodyScore;
		this.date = date;
		CPropertyId = propertyId;
		this.scoreMove = scoreMove;
		this.description = description;
		CPersonalId = personalId;
		this.active = active;
	}
	public Integer getCPropertySineId() {
		return CPropertySineId;
	}
	public void setCPropertySineId(Integer propertySineId) {
		CPropertySineId = propertySineId;
	}
	public Integer getCStatusSineId() {
		return CStatusSineId;
	}
	public void setCStatusSineId(Integer statusSineId) {
		CStatusSineId = statusSineId;
	}
	public Integer getBodyScore() {
		return bodyScore;
	}
	public void setBodyScore(Integer bodyScore) {
		this.bodyScore = bodyScore;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public Integer getScoreMove() {
		return scoreMove;
	}
	public void setScoreMove(Integer scoreMove) {
		this.scoreMove = scoreMove;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	 
}
