package damdariar.beans;

public class CFoodExit extends AbstractBean{
	
	
	private Integer CFoodExitId;
	private String  reason;
	private String description;
	
	
	public CFoodExit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CFoodExit(Integer foodExitId, String reason, String description) {
		super();
		CFoodExitId = foodExitId;
		this.reason = reason;
		this.description = description;
	}
	public Integer getCFoodExitId() {
		return CFoodExitId;
	}
	public void setCFoodExitId(Integer foodExitId) {
		CFoodExitId = foodExitId;
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
