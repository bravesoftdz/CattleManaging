package damdariar.beans;

public class CSmsMail extends AbstractBean{
 
	
	private Integer CSmsMailId;
	private String CUserName;
	private String CPassword;
	private Integer CMobile;
	private String CMail;
	private Boolean SFZayeman;
	private Boolean SFKhoshk;
	private Boolean MFZayeman;
	private Boolean MFKhoshk;
	private Boolean active;
	
	
	
	public CSmsMail(Integer smsMailId, String userName, String password,
			Integer mobile, String mail, Boolean zayeman, Boolean khoshk,
			Boolean zayeman2, Boolean khoshk2, Boolean active) {
		super();
		CSmsMailId = smsMailId;
		CUserName = userName;
		CPassword = password;
		CMobile = mobile;
		CMail = mail;
		SFZayeman = zayeman;
		SFKhoshk = khoshk;
		MFZayeman = zayeman2;
		MFKhoshk = khoshk2;
		this.active = active;
	}
	
	
	public CSmsMail() {
		super();
	}
	
	
	public Integer getCSmsMailId() {
		return CSmsMailId;
	}
	public void setCSmsMailId(Integer smsMailId) {
		CSmsMailId = smsMailId;
	}
	public String getCUserName() {
		return CUserName;
	}
	public void setCUserName(String userName) {
		CUserName = userName;
	}
	public String getCPassword() {
		return CPassword;
	}
	public void setCPassword(String password) {
		CPassword = password;
	}
	public Integer getCMobile() {
		return CMobile;
	}
	public void setCMobile(Integer mobile) {
		CMobile = mobile;
	}
	public String getCMail() {
		return CMail;
	}
	public void setCMail(String mail) {
		CMail = mail;
	}
	public Boolean getSFZayeman() {
		return SFZayeman;
	}
	public void setSFZayeman(Boolean zayeman) {
		SFZayeman = zayeman;
	}
	public Boolean getSFKhoshk() {
		return SFKhoshk;
	}
	public void setSFKhoshk(Boolean khoshk) {
		SFKhoshk = khoshk;
	}
	public Boolean getMFZayeman() {
		return MFZayeman;
	}
	public void setMFZayeman(Boolean zayeman) {
		MFZayeman = zayeman;
	}
	public Boolean getMFKhoshk() {
		return MFKhoshk;
	}
	public void setMFKhoshk(Boolean khoshk) {
		MFKhoshk = khoshk;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
