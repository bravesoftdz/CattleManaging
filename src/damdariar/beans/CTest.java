package damdariar.beans;

import java.sql.Date;

public class CTest extends AbstractBean {
	
	private Integer CTestId;
	private Integer CTalghihId;
	private Integer CPersonalId;
	private Integer CPropertyId;
	private java.util.Date testDate;
	private Integer testResultId;
	private String  CTestCount;
	private String description;
	
	static{
		IDMAP.put(CTest.class,"CPersonalId", CPersonal.class);
	}
	static{
		IDMAP.put(CTest.class,"CPropertyId", CProperty.class);
	}
    static{
	    IDMAP.put(CTest.class,"testResultId", TestResult.class);
	    MASTERDETAIL.put(CTest.class,"CTalghihId");
    }
	
	public CTest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CTest(Integer testId,Integer CTalghihId, Integer personalId, Integer propertyId,
			java.util.Date testDate, Integer testResultId, String testCount,
			String description) {
		super();
		CTalghihId = CTalghihId;
		CTestId = testId;
		CPersonalId = personalId;
		CPropertyId = propertyId;
		this.testDate = testDate;
		this.testResultId = testResultId;
		CTestCount = testCount;
		this.description = description;
	}


	public Integer getCTalghihId() {
		return CTalghihId;
	}


	public void setCTalghihId(Integer talghihId) {
		CTalghihId = talghihId;
	}


	public Integer getCTestId() {
		return CTestId;
	}


	public void setCTestId(Integer testId) {
		CTestId = testId;
	}


	public Integer getCPersonalId() {
		return CPersonalId;
	}


	public void setCPersonalId(Integer personalId) {
		CPersonalId = personalId;
	}


	public Integer getCPropertyId() {
		return CPropertyId;
	}


	public void setCPropertyId(Integer propertyId) {
		CPropertyId = propertyId;
	}


	public java.util.Date getTestDate() {
		return testDate;
	}


	public void setTestDate(java.util.Date testDate) {
		this.testDate = testDate;
	}


	public Integer getTestResultId() {
		return testResultId;
	}


	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}


	public String getCTestCount() {
		return CTestCount;
	}


	public void setCTestCount(String testCount) {
		CTestCount = testCount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
