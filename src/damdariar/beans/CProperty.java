package damdariar.beans;

import java.util.Date;

import damdariar.gui.swing.forms.CPropertyConstants;


public class CProperty extends AbstractBean implements java.io.Serializable {

	// Fields

	private Integer CPropertyId;
	private String color;
	private Boolean active;
	private Integer fatherId;
	private String numberPelastic;
	private String numberBody;
	private String description;
	private Float CHf;
	private Boolean flagShakhsozi;
	private Boolean flagMagnet;
	private Date dateInput;
	private Date dateBirthday;
	private Date dateShakhsozi;
	private Date dateMagnet;
	private Float price;
	private Integer dore;
	private String cowSex;
	private Integer rgt;
	private String numberRegister;
	private String fatherName;
	private Integer spermId;
	private Integer spermNumber;
	private String numberFelezi;
	
	public Integer getRgt() {
		return rgt;
	}

	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}

	public Integer getLft() {
		return lft;
	}

	public void setLft(Integer lft) {
		this.lft = lft;
	}

	private Integer lft;
	
	static{
		IDMAP.put(CProperty.class,"dore",CStatusBase.class);
	    IDMAP.put(CProperty.class,"fatherId",CProperty.class);
	    IDMAP.put(CProperty.class,"spermId", CBaseSperm.class);
	    TREEMAP.put(CProperty.class, new TreeConfig("fatherId",false));
	    
		
	}
	

	// Constructors

	/** default constructor */
	public CProperty() {
	}

	/** full constructor */
	public CProperty(Integer propertyId, String color, Boolean active,
			Integer fatherId, String numberPelastic, String numberBody,
			String description, Float hf, Boolean flagShakhsozi,
			Boolean flagMagnet, Date dateInput, Date dateBirthday,
			Date dateShakhsozi, Date dateMagnet, Float price, Integer dore,String cowSex,Integer rgt,Integer lft,String numberRegister,String fatherName,Integer spermId,Integer spermNumber,String numberFelzi) {
		super();
		this.numberFelezi = numberFelezi;
		CPropertyId = propertyId;
		this.color = color;
		this.active = active;
		this.fatherId = fatherId;
		this.numberPelastic = numberPelastic;
		this.numberBody = numberBody;
		this.description = description;
		CHf = hf;
		this.flagShakhsozi = flagShakhsozi;
		this.flagMagnet = flagMagnet;
		this.dateInput = dateInput;
		this.dateBirthday = dateBirthday;
		this.dateShakhsozi = dateShakhsozi;
		this.dateMagnet = dateMagnet;
		this.price = price;
		this.dore = dore;
		this.cowSex = cowSex;
		this.rgt = rgt;
		this.lft =lft;
		this.numberRegister = numberRegister;
		this.fatherName = fatherName;
		this.spermNumber = spermNumber;
		this.spermId = spermId;
	}


	public String getNumberFelezi() {
		return numberFelezi;
	}

	public void setNumberFelezi(String numberFelezi) {
		this.numberFelezi = numberFelezi;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Integer getSpermId() {
		return spermId;
	}

	public void setSpermId(Integer spermId) {
		this.spermId = spermId;
	}

	public Integer getSpermNumber() {
		return spermNumber;
	}

	public void setSpermNumber(Integer spermNumber) {
		this.spermNumber = spermNumber;
	}

	public String getNumberRegister() {
		return numberRegister;
	}

	public void setNumberRegister(String numberRegister) {
		this.numberRegister = numberRegister;
	}

	public String getCowSex() {
		return cowSex;
	}

	public void setCowSex(String cowSex) {
		this.cowSex = cowSex;
	}

	static{
		 IDMAP.put(CProperty.class,"fatherId",CProperty.class);
	}
	public Integer getCPropertyId() {
		return this.CPropertyId;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDore() {
		return dore;
	}

	public void setDore(Integer dore) {
		this.dore = dore;
	}

	public void setCPropertyId(Integer CPropertyId) {
		this.CPropertyId = CPropertyId;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public String getNumberPelastic() {
		return this.numberPelastic;
	}

	public void setNumberPelastic(String numberPelastic) {
		this.numberPelastic = numberPelastic;
	}

	public String getNumberBody() {
		return this.numberBody;
	}

	public void setNumberBody(String numberBody) {
		this.numberBody = numberBody;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCHf() {
		return this.CHf;
	}

	public void setCHf(Float CHf) {
		this.CHf = CHf;
	}

	public Boolean getFlagShakhsozi() {
		return this.flagShakhsozi;
	}

	public void setFlagShakhsozi(Boolean flagShakhsozi) {
		this.flagShakhsozi = flagShakhsozi;
	}

	public Boolean getFlagMagnet() {
		return this.flagMagnet;
	}

	public void setFlagMagnet(Boolean flagMagnet) {
		this.flagMagnet = flagMagnet;
	}

	public Date getDateInput() {
		return this.dateInput;
	}

	public void setDateInput(Date dateInput) {
		this.dateInput = dateInput;
	}

	public Date getDateBirthday() {
		return this.dateBirthday;
	}

	public void setDateBirthday(Date dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	public Date getDateShakhsozi() {
		return this.dateShakhsozi;
	}

	public void setDateShakhsozi(Date dateShakhsozi) {
		this.dateShakhsozi = dateShakhsozi;
	}

	public Date getDateMagnet() {
		return this.dateMagnet;
	}

	public void setDateMagnet(Date dateMagnet) {
		this.dateMagnet = dateMagnet;
	}

	
	public String[]  getColorModel(){
		
		return CPropertyConstants.getColor();
	}
	
	public String[]  getActiveModel(){
		
		return CPropertyConstants.getActive();
	}

	


}