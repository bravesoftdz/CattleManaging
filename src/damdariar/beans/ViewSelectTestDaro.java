package damdariar.beans;

import java.util.Date;


public class ViewSelectTestDaro extends AbstractBean {
	
	private Integer CDaroTajvizId;
	private String dateConsume;
	private String cuontDaro;
	private  Integer CTestId;
	private Integer CDaroId;
	private Date  dateTajviz;
	
	
	static{
		IDMAP.put(ViewSelectTestDaro.class,"CDaroId", CDaro.class);
		MASTERDETAIL.put(ViewSelectTestDaro.class,"CTestId");
	}
	
	
	public ViewSelectTestDaro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ViewSelectTestDaro(Integer daroTajvizId, String dateConsume,
			String cuontDaro, Integer testId, Integer daroId, Date dateTajviz) {
		super();
		CDaroTajvizId = daroTajvizId;
		this.dateConsume = dateConsume;
		this.cuontDaro = cuontDaro;
		CTestId = testId;
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
	public Integer getCTestId() {
		return CTestId;
	}
	public void setCTestId(Integer testId) {
		CTestId = testId;
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
