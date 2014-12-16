package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.PartnerDTO;

import java.util.List;

/**
 * The Interface PartnerService.
 */
public interface PartnerService {
	
	/**
	 * Gets the partner list.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the partner list
	 */
	List<PartnerDTO> getPartnerList(int page, int size);
	
	/**
	 * Creates the partner.
	 *
	 * @param partner the partner
	 */
	void createPartner(PartnerDTO partner);
	
	/**
	 * Removes the partner.
	 *
	 * @param partner the partner
	 */
	void removePartner(PartnerDTO partner);
	
	/**
	 * Update partner.
	 *
	 * @param partner the partner
	 */
	void updatePartner(PartnerDTO partner);
	
	/**
	 * Gets the partner by name.
	 *
	 * @param name the name
	 * @return the partner by name
	 */
	PartnerDTO getPartnerByName(String name);

}
