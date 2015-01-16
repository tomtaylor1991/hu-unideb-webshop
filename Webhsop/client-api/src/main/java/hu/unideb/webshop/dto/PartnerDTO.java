package hu.unideb.webshop.dto;

import java.io.Serializable;

public class PartnerDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String address;

	private String type;

	private Long userId;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PartnerDTO [id=" + id + ", name=" + name + ", address="
				+ address + ", type=" + type + ", user=" + userId + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
