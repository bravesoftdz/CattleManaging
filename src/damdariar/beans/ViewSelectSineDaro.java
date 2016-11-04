package damdariar.beans;

import java.util.Date;

public class ViewSelectSineDaro extends AbstractBean{
	
	private Integer CDaroTajvizId;
	private String dateConsume;
	private String cuontDaro;
	private  Integer CPropertySineId;
	private Integer CDaroId;
	private Date  dateTajviz;
	
	static{
		IDMAP.put(ViewSelectSineDaro.class,"CDaroId", CDaro.class);
	}
	static{
		MASTERDETAIL.put(ViewSelectSineDaro.class, "CPropertySineId");
	}
	public ViewSelectSineDaro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ViewSelectSineDaro(Integer daroTajvizId, String dateConsume,
			String cuontDaro, Integer propertySineId, Integer daroId,
			Date dateTajviz) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.dateConsume = dateConsume;
		this.cuontDaro = cuontDaro;
		CPropertySineId = propertySineId;
		CDaroId = daroId;
		this.dateTajviz = dateTajviz;
	}
	
	public Integer getCDaroTajvizId() {
		return CDaroTajvizId;
	}
	public void setCDaroTajvizId(Integer daroTajvizId) {
		CDaroTajvizId = daroTajvizId;
	}
	public String getDateConsume() {
		return dateConsume;
	}
	public void setDateConsume(String dateConsume) {
		this.dateConsume = dateConsume;
	}
	public String getCuontDaro() {
		return cuontDaro;
	}
	public void setCuontDaro(String cuontDaro) {
		this.cuontDaro = cuontDaro;
	}
	public Integer getCPropertySineId() {
		return CPropertySineId;
	}
	public void setCPropertySineId(Integer propertySineId) {
		CPropertySineId = propertySineId;
	}
	public Integer getCDaroId() {
		return CDaroId;
	}
	public void setCDaroId(Integer daroId) {
		CDaroId = daroId;
	}
	public Date getDateTajviz() {
		return dateTajviz;
	}
	public void setDateTajviz(Date dateTajviz) {
		this.dateTajviz = dateTajviz;
	}
	
	
	
}
