package hu.unideb.webshop.dto;

import java.io.Serializable;

public class IncomeDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private int price;
	private String comment;
	private Long orderId;

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

	@Override
	public String toString() {
		return "IncomeDTO [id=" + id + ", price=" + price + ", comment="
				+ comment + ", orderId=" + orderId + "]";
	}

}
