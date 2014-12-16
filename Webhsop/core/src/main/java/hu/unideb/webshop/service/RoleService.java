package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.entity.Role;

import java.util.List;

/**
 * The Interface RoleService.
 */
public interface RoleService {
	 
    /**
     * Gets the role.
     *
     * @param id the id
     * @return the role
     */
    Role getRole(Long id);

    /**
     * Gets the role by role.
     *
     * @param role the role
     * @return the role by role
     */
    RoleDTO getRoleByRole(String role);

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	List<RoleDTO> getRoles();
}
