package damdariar.beans;

import java.util.Date;

public class ViewSelectLegDaro extends AbstractBean{
	
	private Integer CDaroTajvizId;
	private String dateConsume;
	private String cuontDaro;
	private  Integer CCowLegStatusId;
	private Integer CDaroId;
	private Date  dateTajviz;
	
	static{
		IDMAP.put(ViewSelectLegDaro.class,"CDaroId", CDaro.class);
		MASTERDETAIL.put(ViewSelectLegDaro.class,"CCowLegStatusId");
	}
	
	public ViewSelectLegDaro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViewSelectLegDaro(Integer daroTajvizId, String dateConsume,
			String cuontDaro, Integer CCowLegStatusId, Integer daroId,
			Date dateTajviz) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.dateConsume = dateConsume;
		this.cuontDaro = cuontDaro;
		CCowLegStatusId = CCowLegStatusId;
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
	public Integer getCCowLegStatusId() {
		return CCowLegStatusId;
	}
	public void setCCowLegStatusId(Integer CCowLegStatusId) {
		CCowLegStatusId = CCowLegStatusId;
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
