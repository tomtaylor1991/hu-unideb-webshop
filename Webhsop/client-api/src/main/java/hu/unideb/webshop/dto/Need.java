package hu.unideb.webshop.dto;

import java.io.Serializable;

public class Need implements Serializable, Comparable<Need> {

	private static final long serialVersionUID = 1L;

	private String name;
	private int quantity;

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

	public Need(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Need [name=" + name + ", quantity=" + quantity + "]";
	}

	@Override
	public int compareTo(Need o) {
		if (this.quantity < o.getQuantity()) {
			return -1;
		} else if (this.quantity > o.getQuantity()) {
			return 1;
		}
		return 0;
	}

}
