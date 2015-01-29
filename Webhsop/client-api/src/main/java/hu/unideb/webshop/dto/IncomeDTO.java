package hu.unideb.webshop.dto;

import java.io.Serializable;

public class IncomeDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private int price;
	private String comment;
	private Long orderId;
	private String type;
	private String name;
	private int quantity;


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "IncomeDTO [id=" + id + ", price=" + price + ", comment="
				+ comment + ", orderId=" + orderId + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
