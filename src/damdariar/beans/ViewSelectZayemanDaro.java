package damdariar.beans;

import java.util.Date;

public class ViewSelectZayemanDaro extends AbstractBean{
	
	private Integer CDaroTajvizId;
	private String dateConsume;
	private String cuontDaro;
	private  Integer CZayemanId;
	private Integer CDaroId;
	private Date  dateTajviz;
	
	static{
		IDMAP.put(ViewSelectZayemanDaro.class,"CDaroId", CDaro.class);
		MASTERDETAIL.put(ViewSelectZayemanDaro.class,"CZayemanId");
	}

	public ViewSelectZayemanDaro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewSelectZayemanDaro(Integer daroTajvizId, String dateConsume,
			String cuontDaro, Integer zayamanId, Integer daroId, Date dateTajviz) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.dateConsume = dateConsume;
		this.cuontDaro = cuontDaro;
		CZayemanId = zayamanId;
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

	public Integer getCZayemanId() {
		return CZayemanId;
	}

	public void setCZayemanId(Integer zayamanId) {
		CZayemanId = zayamanId;
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
