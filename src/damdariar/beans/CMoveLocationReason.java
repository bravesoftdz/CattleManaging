package damdariar.beans;

public class CMoveLocationReason extends AbstractBean{
	
	
	private Integer CMoveLocationReasonId;
	private String reason;
	private String description;
	
	
	public CMoveLocationReason() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CMoveLocationReason(Integer moveLocationReasonId, String reason,
			String description) {
		super();
		CMoveLocationReasonId = moveLocationReasonId;
		this.reason = reason;
		this.description = description;
	}
	
	public Integer getCMoveLocationReasonId() {
		return CMoveLocationReasonId;
	}
	public void setCMoveLocationReasonId(Integer moveLocationReasonId) {
		CMoveLocationReasonId = moveLocationReasonId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	}
