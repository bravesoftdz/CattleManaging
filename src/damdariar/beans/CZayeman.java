package damdariar.beans;

import java.sql.Date;

public class CZayeman extends AbstractBean{
	
	private Integer CZayemanId;
	private Integer CPropertyId;
	private String typeChild;
	private Integer statusChildId;
	private Float weightChild;
	private Integer zayemanStatusId;
	private java.util.Date zayamanDate;
	private Integer statusJoftId;
	private java.util.Date joftDate;
	private String description;
	private Integer reasonZayemanStatusId;
	private Integer CPersonalId;
	private java.util.Date dateKhoshk;
	private Integer dore;
	
	static{
		IDMAP.put(CZayeman.class,"CPropertyId",CProperty.class);
	}
	static{
		IDMAP.put(CZayeman.class,"statusChildId" , StatusChild.class);
	}
	static{
		IDMAP.put(CZayeman.class,"zayemanStatusId", ZayemanStatus.class);
	}
	static{
		IDMAP.put(CZayeman.class,"statusJoftId",StatusJoft.class);
	}
	static{
		IDMAP.put(CZayeman.class,"reasonZayemanStatusId",ReasonZayemanStatus.class);
	}
	static{
		IDMAP.put(CZayeman.class,"CPersonalId", CPersonal.class);
	}
	static{
		IDMAP.put(CZayeman.class,"dore",CStatusBase.class);
	}
	public CZayeman() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CZayeman(Integer zayemanId, Integer propertyId, String typeChild,
			Integer statusChildId, Float weightChild, Integer zayemanStatusId,
			java.util.Date zayamanDate, Integer statusJoftId, java.util.Date joftDate,
			String description, Integer reasonZayemanStatusId,
			Integer personalId, java.util.Date dateKhoshk, Integer dore) {
		super();
		CZayemanId = zayemanId;
		CPropertyId = propertyId;
		this.typeChild = typeChild;
		this.statusChildId = statusChildId;
		this.weightChild = weightChild;
		this.zayemanStatusId = zayemanStatusId;
		this.zayamanDate = zayamanDate;
		this.statusJoftId = statusJoftId;
		this.joftDate = joftDate;
		this.description = description;
		this.reasonZayemanStatusId = reasonZayemanStatusId;
		CPersonalId = personalId;
		this.dateKhoshk = dateKhoshk;
		this.dore = dore;
	}
	
	public Integer getCZayemanId() {
		return CZayemanId;
	}
	public void setCZayemanId(Integer zayemanId) {
		CZayemanId = zayemanId;
	}
	public Integer getCPropertyId() {
		return CPropertyId;
	}
	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}
	public String getTypeChild() {
		return typeChild;
	}
	public void setTypeChild(String typeChild) {
		this.typeChild = typeChild;
	}
	public Integer getStatusChildId() {
		return statusChildId;
	}
	public void setStatusChildId(Integer statusChildId) {
		this.statusChildId = statusChildId;
	}
	public Float getWeightChild() {
		return weightChild;
	}
	public void setWeightChild(Float weightChild) {
		this.weightChild = weightChild;
	}
	public Integer getZayemanStatusId() {
		return zayemanStatusId;
	}
	public void setZayemanStatusId(Integer zayemanStatusId) {
		this.zayemanStatusId = zayemanStatusId;
	}
	public java.util.Date getZayamanDate() {
		return zayamanDate;
	}
	public void setZayamanDate(java.util.Date zayamanDate) {
		this.zayamanDate = zayamanDate;
	}
	public Integer getStatusJoftId() {
		return statusJoftId;
	}
	public void setStatusJoftId(Integer statusJoftId) {
		this.statusJoftId = statusJoftId;
	}
	public java.util.Date getJoftDate() {
		return joftDate;
	}
	public void setJoftDate(java.util.Date joftDate) {
		this.joftDate = joftDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getReasonZayemanStatusId() {
		return reasonZayemanStatusId;
	}
	public void setReasonZayemanStatusId(Integer reasonZayemanStatusId) {
		this.reasonZayemanStatusId = reasonZayemanStatusId;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	public java.util.Date getDateKhoshk() {
		return dateKhoshk;
	}
	public void setDateKhoshk(java.util.Date dateKhoshk) {
		this.dateKhoshk = dateKhoshk;
	}
	public Integer getDore() {
		return dore;
	}
	public void setDore(Integer dore) {
		this.dore = dore;
	}
		
}
