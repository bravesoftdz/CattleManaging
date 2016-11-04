package damdariar.beans;

import java.util.Date;

public class CMilkTotal extends AbstractBean {
	private Integer CMilkTotalId;
	private Float milkProtean;
	private Float milkChrbi; 
	private Float milkTotal;
	private Float consumeMotafareghe;
	private Float consumeGosale;
	private String producter;
	private String description;
	private Date dateTotal;
	private Float price;
	private Float milkMikrob;
	
	
	public CMilkTotal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CMilkTotal(Integer milkTotalId, Float milkProtean, Float milkChrbi,
			Float milkTotal, Float consumeMotafareghe, Float consumeGosale,
			String producter, String description, Date date, Float price, Float milkMikrob) {
		super();
		CMilkTotalId = milkTotalId;
		this.milkProtean = milkProtean;
		this.milkChrbi = milkChrbi;
		this.milkTotal = milkTotal;
		this.consumeMotafareghe = consumeMotafareghe;
		this.consumeGosale = consumeGosale;
		this.producter = producter;
		this.description = description;
		this.dateTotal = date;
		this.price = price;
		this.milkMikrob = milkMikrob;
	}
	
	
	public Float getMilkMikrob() {
		return milkMikrob;
	}
	public void setMilkMikrob(Float milkMikrob) {
		this.milkMikrob = milkMikrob;
	}
	public Integer getCMilkTotalId() {
		return CMilkTotalId;
	}
	public void setCMilkTotalId(Integer milkTotalId) {
		CMilkTotalId = milkTotalId;
	}
	public Float getMilkProtean() {
		return milkProtean;
	}
	public void setMilkProtean(Float milkProtean) {
		this.milkProtean = milkProtean;
	}
	public Float getMilkChrbi() {
		return milkChrbi;
	}
	public void setMilkChrbi(Float milkChrbi) {
		this.milkChrbi = milkChrbi;
	}
	public Float getMilkTotal() {
		return milkTotal;
	}
	public void setMilkTotal(Float milkTotal) {
		this.milkTotal = milkTotal;
	}
	public Float getConsumeMotafareghe() {
		return consumeMotafareghe;
	}
	public void setConsumeMotafareghe(Float consumeMotafareghe) {
		this.consumeMotafareghe = consumeMotafareghe;
	}
	public Float getConsumeGosale() {
		return consumeGosale;
	}
	public void setConsumeGosale(Float consumeGosale) {
		this.consumeGosale = consumeGosale;
	}
	public String getProducter() {
		return producter;
	}
	public void setProducter(String producter) {
		this.producter = producter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateTotal() {
		return dateTotal;
	}
	public void setDateTotal(Date date) {
		this.dateTotal = date;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	 
}
