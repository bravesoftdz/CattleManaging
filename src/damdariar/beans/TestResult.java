package damdariar.beans;

public class TestResult extends AbstractBean {
	
	private Integer testResultId;
	private String name;
	private String description;
	
	public TestResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestResult(Integer testResultId, String name, String description) {
		super();
		this.testResultId = testResultId;
		this.name = name;
		this.description = description;
	}
	public Integer gettestResultId() {
		return testResultId;
	}
	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
