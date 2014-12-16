package hu.unideb.webshop.dto;

import java.util.Date;

/**
 * Base class of DTO-s. 
 * It contains recDate, modDate, recUserId, modUserId fields,
 * and their getter, and setter methods.
 */
public class BaseDTO {

	/**
	 * The date of the creation of the DTO.
	 */
	private Date recDate;
	/**
	 * The modifying date of the DTO.
	 */
	private Date modDate;
	/**
	 * The id of the user who created the DTO.
	 */
	private String recUserId;
	/**
	 * The id of the user who modified the DTO.
	 */
	private String modUserId;

	/**
	 * Returns the date of the creation of the DTO.
	 * 
	 * @return the date of the creation of the DTO
	 */
	public Date getRecDate() {
		return recDate;
	}

	/**
	 * Sets the date of the creation of the DTO.
	 * 
	 * @param recDate
	 *            the date of the creation of the DTO.
	 */
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	/**
	 * Returns the modifying date of the DTO.
	 * 
	 * @return the modifying date of the DTO
	 */
	public Date getModDate() {
		return modDate;
	}

	/**
	 * Sets the modifying date of the DTO.
	 * 
	 * @param modDate
	 *            the modifying date of the DTO
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	/**
	 * Returns the id of the user who created the DTO.
	 * 
	 * @return the id of the user who created the DTO
	 */
	public String getRecUserId() {
		return recUserId;
	}

	/**
	 * Sets the id of the user who created the DTO.
	 * 
	 * @param recUserId
	 *            the id of the user who created the DTO
	 */
	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}

	/**
	 * Returns the id of the user who modified the DTO.
	 * 
	 * @return the id of the user who modified the DTO
	 */
	public String getModUserId() {
		return modUserId;
	}

	/**
	 * Sets the id of the user who modified the DTO.
	 * 
	 * @param modUserId
	 *            the id of the user who modified the DTO
	 */
	public void setModUserId(String modUserId) {
		this.modUserId = modUserId;
	}
}