package damdariar.beans;

import java.util.Date;

public class CDaroConsume extends AbstractBean{
	
	private Integer CDaroConsumeId;
	private Integer CDaroId;
	private Date date;
	private Integer cuontDaro;
	private String description;
	private Integer CPersonalId;
	private Integer CDaroTajvizId;
	
	static{
		IDMAP.put(CDaroConsume.class,"CDaroId", CDaro.class);
	}
	static{
		IDMAP.put(CDaroConsume.class,"CPersonalId", CPersonal.class);
	}
	static{
		IDMAP.put(CDaroConsume.class,"CDaroTajvizId", CDaroTajviz.class);
	}
	public CDaroConsume() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CDaroConsume(Integer daroConsumeId, Integer daroId, Date date,
			Integer cuontDaro, String description, Integer personalId,
			Integer daroTajvizId) {
		super();
		CDaroConsumeId = daroConsumeId;
		CDaroId = daroId;
		this.date = date;
		this.cuontDaro = cuontDaro;
		this.description = description;
		CPersonalId = personalId;
		CDaroTajvizId = daroTajvizId;
	}

	public Integer getCDaroTajvizId() {
		return CDaroTajvizId;
	}

	public void setCDaroTajvizId(Integer daroTajvizId) {
		CDaroTajvizId = daroTajvizId;
	}

	public Integer getCDaroConsumeId() {
		return CDaroConsumeId;
	}
	public void setCDaroConsumeId(Integer daroConsumeId) {
		CDaroConsumeId = daroConsumeId;
	}
	public Integer getCDaroId() {
		return CDaroId;
	}
	public void setCDaroId(Integer daroId) {
		CDaroId = daroId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCuontDaro() {
		return cuontDaro;
	}
	public void setCuontDaro(Integer cuontDaro) {
		this.cuontDaro = cuontDaro;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCPersonalId() {
		return CPersonalId;
	}
	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}
	

}
