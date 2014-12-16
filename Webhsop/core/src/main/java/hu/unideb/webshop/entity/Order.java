package hu.unideb.webshop.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {


	private static final long serialVersionUID = 1L;

	private String name;

	private Date date;

	private String status;

	private Long partnerId;
	
	/**
	 * Gets the partner id.
	 *
	 * @return the partner id
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * Sets the partner id.
	 *
	 * @param partnerId the new partner id
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
