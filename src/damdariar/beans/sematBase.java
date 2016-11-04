package damdariar.beans;


public class sematBase extends AbstractBean {
	
 private Integer sematBaseId;
 private String name;
 private String description;
 
 
public sematBase(Integer sematBaseId, String name, String descriptoin) {
	
	this.sematBaseId = sematBaseId;
	this.name = name;
	descriptoin = descriptoin;
  }

public sematBase() {
	
	// TODO Auto-generated constructor stub
}

public Integer getsematBaseId() {
	return this.sematBaseId;
}
public void setsematBaseId(Integer sematBaseId) {
	this.sematBaseId = sematBaseId;
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
	description = description;
}
}