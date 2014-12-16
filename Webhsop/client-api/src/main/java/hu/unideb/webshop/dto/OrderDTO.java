package hu.unideb.webshop.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class of OrderDTO, which extends the BaseDTO.
 * It contains the id, the name, the date, the status of the Order, 
 * the DTO of the Partner who requested the Order, and their
 * setter and getter methods.
 */
public class OrderDTO extends BaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * The id of the Order. 
	 */
	private Long id;
	
	/** 
	 * The name of the Order. 
	 */
	private String name;
	
	/** 
	 * The date of the Order. 
	 */
	private Date date;
	
	/** 
	 * The status of the Order. 
	 */
	private String status;
	
	/**
	 *  The DTO of the partner, who requested the Order.
	 */
	private PartnerDTO partnerDTO;

	/**
	 * Instantiates a new order dto.
	 */
	public OrderDTO() {

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OrderDTO other = (OrderDTO) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the partner DTO.
	 *
	 * @return the partner DTO
	 */
	public PartnerDTO getPartnerDTO() {
		return partnerDTO;
	}

	/**
	 * Sets the partner DTO.
	 *
	 * @param partnerDTO the new partner DTO
	 */
	public void setPartnerDTO(PartnerDTO partnerDTO) {
		this.partnerDTO = partnerDTO;
	}

}
