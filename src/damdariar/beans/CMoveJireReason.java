package damdariar.beans;

public class CMoveJireReason extends AbstractBean{
	
	
	private Integer CMoveJireReasonId;
	private String reason;
	private String decription;
	
	
	public CMoveJireReason() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CMoveJireReason(Integer moveJireReasonId, String reason,
			String decription) {
		super();
		CMoveJireReasonId = moveJireReasonId;
		this.reason = reason;
		this.decription = decription;
	}
	public Integer getCMoveJireReasonId() {
		return CMoveJireReasonId;
	}
	public void setCMoveJireReasonId(Integer moveJireReasonId) {
		CMoveJireReasonId = moveJireReasonId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	
}
