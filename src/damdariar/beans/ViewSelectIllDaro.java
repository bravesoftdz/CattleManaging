package damdariar.beans;

import java.util.Date;

public class ViewSelectIllDaro extends AbstractBean{
	
	private Integer CDaroTajvizId;
	private String dateConsume;
	private String cuontDaro;
	private  Integer CIllId;
	private Integer CDaroId;
	private Date  dateTajviz;
	
	static{
		IDMAP.put(ViewSelectIllDaro.class,"CDaroId", CDaro.class);
		MASTERDETAIL.put(ViewSelectIllDaro.class,"CIllId");
	}
	
	public ViewSelectIllDaro(Integer daroTajvizId, String dateConsume,
			String cuontDaro, Integer illId, Integer daroId, Date dateTajviz) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.dateConsume = dateConsume;
		this.cuontDaro = cuontDaro;
		CIllId = illId;
		CDaroId = daroId;
		this.dateTajviz = dateTajviz;
	}
	
	
	
	public ViewSelectIllDaro() {
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
	public Integer getCIllId() {
		return CIllId;
	}
	public void setCIllId(Integer illId) {
		CIllId = illId;
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