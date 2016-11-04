package damdariar.beans;

import java.sql.Date;

public class CLocationJire extends AbstractBean {
	
	
	private Integer CLocationJireId;
	private Integer CLocationBaseId;
	private Integer CJireId;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private String description;
	private Boolean active;
	private Integer CPersonalId;
	private Integer CMoveJireReasonId;
	
	static{
		IDMAP.put(CLocationJire.class,"CLocationBaseId", CLocationBase.class);
	}
	static{
		IDMAP.put(CLocationJire.class,"CPersonalId", CPersonal.class);
	}
	static{
		IDMAP.put(CLocationJire.class,"CMoveJireReasonId", CMoveJireReason.class);
	}
	static{
		IDMAP.put(CLocationJire.class,"CJireId", CJire.class);
	}
	public CLocationJire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CLocationJire(Integer locationJireId, Integer locationBaseId,
			Integer jireId, java.util.Date startDate, java.util.Date endDate, String description,
			Boolean active, Integer personalId, Integer moveJireReasonId) {
		super();
		CLocationJireId = locationJireId;
		CLocationBaseId = locationBaseId;
		CJireId = jireId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.active = active;
		CPersonalId = personalId;
		CMoveJireReasonId = moveJireReasonId;
	}
	public Integer getCLocationJireId() {
		return CLocationJireId;
	}
	public void setCLocationJireId(Integer locationJireId) {
		CLocationJireId = locationJireId;
	}
	public Integer getCLocationBaseId() {
		return CLocationBaseId;
	}
	public void setCLocationBaseId(Integer locationBaseId) {
		CLocationBaseId = locationBaseId;
	}
	public Integer getCJireId() {
		return CJireId;
	}
	public void setCJireId(Integer jireId) {
		CJireId = jireId;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public Integer getCMoveJireReasonId() {
		return CMoveJireReasonId;
	}
	public void setCMoveJireReasonId(Integer moveJireReasonId) {
		CMoveJireReasonId = moveJireReasonId;
	}
	 
}
