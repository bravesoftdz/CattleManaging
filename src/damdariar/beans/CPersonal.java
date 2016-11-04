package damdariar.beans;

import java.util.Date;
import damdariar.gui.swing.forms.CPropertyConstants;

public class CPersonal extends AbstractBean{
	private Integer CPersonalId;
	private String firstName;
	private String lastName;
	private Integer codeMilli;
	private Integer sematBaseId;
	private Date startDate;
	private Date endDate;
	private String description;
    
    
    static{
		 IDMAP.put(CPersonal.class,"sematBaseId",sematBase.class);
	}

	public CPersonal(Integer personalId, String firstName, String lastName,
			Integer codeMilli, Integer sematBaseId,Date startDate,
			Date endDate, String description) {
		super();
		CPersonalId = personalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.codeMilli = codeMilli;
		this.sematBaseId = sematBaseId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}
	public CPersonal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getCodeMilli() {
		return codeMilli;
	}
	public void setCodeMilli(Integer codeMilli) {
		this.codeMilli = codeMilli;
	}
	public Integer getSematBaseId() {
		return sematBaseId;
	}
	public void setSematBaseId(Integer sematBaseId) {
		this.sematBaseId = sematBaseId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    public String[]  getColorModel(){
		
		return CPropertyConstants.getColor();
	}
	
	public String[]  getActiveModel(){
		
		return CPropertyConstants.getActive();
	}

    
}
