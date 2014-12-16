package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.RoleDTO;

/**
 * The Interface ManageRoleFacadeService.
 */
public interface ManageRoleFacadeService {

	/**
	 * Gets the role.
	 *
	 * @param id the id
	 * @return the role
	 */
	RoleDTO getRole(Long id);
	
}
