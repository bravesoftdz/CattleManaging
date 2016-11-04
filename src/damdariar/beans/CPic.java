package damdariar.beans;

import java.sql.Blob;
import java.util.Date;

/**
 * CPic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CPic extends AbstractBean implements java.io.Serializable {

	
	static{
		IDMAP.put(CPic.class,"CPersonalId", CPersonal.class);
		IDMAP.put(CPic.class,"CPropertyId", CProperty.class);
	}
	// Fields

	private Integer CPicId;
	private Integer CPropertyId;
	private Integer CPersonalId;
	private String description;
	private Blob picLeft;
	private Blob picRight;
	private Blob picHead;
	private Date date;

	// Constructors

	/** default constructor */
	public CPic() {
	}

	/** full constructor */
	public CPic(Integer CPropertyId, Integer CPersonalId, String description,
			Blob picLeft,Blob picRight, Blob picHead, Date date) {
		this.CPropertyId = CPropertyId;
		this.CPersonalId = CPersonalId;
		this.description = description;
		this.picLeft = picLeft;
		this.picRight = picRight;
		this.picHead = picHead;
		this.date = date;
	}

	// Property accessors

	public Integer getCPicId() {
		return this.CPicId;
	}

	public void setCPicId(Integer CPicId) {
		this.CPicId = CPicId;
	}

	public Integer getCPropertyId() {
		return this.CPropertyId;
	}

	public void setCPropertyId(Integer CPropertyId) {
		this.CPropertyId = CPropertyId;
	}

	public Integer getCPersonalId() {
		return this.CPersonalId;
	}

	public void setCPersonalId(Integer CPersonalId) {
		this.CPersonalId = CPersonalId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getPicLeft() {
		return this.picLeft;
	}

	public void setPicLeft(Blob picLeft) {
		this.picLeft = picLeft;
	}

	public Blob getPicRight() {
		return this.picRight;
	}

	public void setPicRight(Blob picRight) {
		this.picRight = picRight;
	}

	public Blob getPicHead() {
		return this.picHead;
	}

	public void setPicHead(Blob picHead) {
		this.picHead = picHead;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}