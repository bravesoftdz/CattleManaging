package damdariar.beans;

import java.util.Date;

public class ViewSelectSonografiDaro extends AbstractBean{
  
	
	
	private Integer CDaroTajvizId;
	private String dateConsume;
	private String cuontDaro;
	private  Integer CSonografiId;
	private Integer CDaroId;
	private Date  dateTajviz;
	
	
	static{
		IDMAP.put(ViewSelectSonografiDaro.class,"CDaroId", CDaro.class);
		MASTERDETAIL.put(ViewSelectSonografiDaro.class, "CSonografiId");
	}
	
	public ViewSelectSonografiDaro(Integer daroTajvizId, String dateConsume,
			String cuontDaro, Integer sonografiId, Integer daroId,
			Date dateTajviz) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.dateConsume = dateConsume;
		this.cuontDaro = cuontDaro;
		CSonografiId = sonografiId;
		CDaroId = daroId;
		this.dateTajviz = dateTajviz;
	}
	
	
	public ViewSelectSonografiDaro() {
		super();
		// TODO Auto-generated constructor stub
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
	public Integer getCSonografiId() {
		return CSonografiId;
	}
	public void setCSonografiId(Integer sonografiId) {
		CSonografiId = sonografiId;
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
