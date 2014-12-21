package hu.unideb.webshop.dto;

import java.io.Serializable;

public class ProductDTO extends BaseDTO implements Serializable{

	private Long id;
	private static final long serialVersionUID = 1L;
	private String name;
	private double rate;
	private String text;
	private double price;
	private boolean isSpecial;
	private double specialPrice;
	private boolean isHighlight;
	private String shortText;
	private CategoryDTO category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	public double getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}
	public boolean getIsHighlight() {
		return isHighlight;
	}
	public void setIsHighlight(boolean isHighlight) {
		this.isHighlight = isHighlight;
	}
	public String getShortText() {
		return shortText;
	}
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	
	
}
