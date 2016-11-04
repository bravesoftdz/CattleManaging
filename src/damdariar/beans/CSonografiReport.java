package damdariar.beans;

public class CSonografiReport extends AbstractBean{
	
	private Integer CSonografiReportId;
	private Integer CSonografiId;
	private String sonogerafiReport;
	private String sonografiDarman;
	private String description;
	
	static{
		//IDMAP.put(CSonografiReport.class,"CSonografiId", CSonografi.class);
		MASTERDETAIL.put(CSonografiReport.class,"CSonografiId");
	}
	
	
	public CSonografiReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CSonografiReport(Integer sonografiReportId, Integer sonografiId,
			String sonogerafiReport, String sonografiDarman, String description) {
		super();
		CSonografiReportId = sonografiReportId;
		CSonografiId = sonografiId;
		this.sonogerafiReport = sonogerafiReport;
		this.sonografiDarman = sonografiDarman;
		this.description = description;
	}
	public Integer getCSonografiReportId() {
		return CSonografiReportId;
	}
	public void setCSonografiReportId(Integer sonografiReportId) {
		CSonografiReportId = sonografiReportId;
	}
	public Integer getCSonografiId() {
		return CSonografiId;
	}
	public void setCSonografiId(Integer sonogerafiId) {
		CSonografiId = sonogerafiId;
	}
	public String getSonogerafiReport() {
		return sonogerafiReport;
	}
	public void setSonogerafiReport(String sonogerafiReport) {
		this.sonogerafiReport = sonogerafiReport;
	}
	public String getSonografiDarman() {
		return sonografiDarman;
	}
	public void setSonografiDarman(String sonografiDarman) {
		this.sonografiDarman = sonografiDarman;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
