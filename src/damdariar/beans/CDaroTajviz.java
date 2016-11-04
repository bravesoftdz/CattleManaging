package damdariar.beans;

import java.sql.Date;

public class CDaroTajviz extends AbstractBean{
	
	
	private Integer CDaroTajvizId;
	private Date date;
	private String cuont;
	private Integer CReasonId;
	private Integer CLegStatusId;
	private Integer CIllId;
	private Integer CPropertySineId;
	private Integer CSonografiId;
	private Integer CZayemanId;
	private String dateConsume;
	
	/*static{
		IDMAP.put("", key, value);
	}*/
	public CDaroTajviz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CDaroTajviz(Integer daroTajvizId, Date date, String cuont,
			Integer reasonId, Integer legStatusId, Integer illId,
			Integer propertySineId, Integer sonografiId, Integer zayemanId,
			String dateConsume) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.date = date;
		this.cuont = cuont;
		CReasonId = reasonId;
		CLegStatusId = legStatusId;
		CIllId = illId;
		CPropertySineId = propertySineId;
		CSonografiId = sonografiId;
		CZayemanId = zayemanId;
		this.dateConsume = dateConsume;
	}
	public Integer getCDaroTajvizId() {
		return CDaroTajvizId;
	}
	public void setCDaroTajvizId(Integer daroTajvizId) {
		CDaroTajvizId = daroTajvizId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCuont() {
		return cuont;
	}
	public void setCuont(String cuont) {
		this.cuont = cuont;
	}
	public Integer getCReasonId() {
		return CReasonId;
	}
	public void setCReasonId(Integer reasonId) {
		CReasonId = reasonId;
	}
	public Integer getCLegStatusId() {
		return CLegStatusId;
	}
	public void setCLegStatusId(Integer legStatusId) {
		CLegStatusId = legStatusId;
	}
	public Integer getCIllId() {
		return CIllId;
	}
	public void setCIllId(Integer illId) {
		CIllId = illId;
	}
	public Integer getCPropertySineId() {
		return CPropertySineId;
	}
	public void setCPropertySineId(Integer propertySineId) {
		CPropertySineId = propertySineId;
	}
	public Integer getCSonografiId() {
		return CSonografiId;
	}
	public void setCSonografiId(Integer sonografiId) {
		CSonografiId = sonografiId;
	}
	public Integer getCZayemanId() {
		return CZayemanId;
	}
	public void setCZayemanId(Integer zayemanId) {
		CZayemanId = zayemanId;
	}
	public String getDateConsume() {
		return dateConsume;
	}
	public void setDateConsume(String dateConsume) {
		this.dateConsume = dateConsume;
	}
	 

}
