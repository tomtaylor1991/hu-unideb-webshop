package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.dto.UserDTO;

import java.util.List;

/**
 * The Interface ManageUserFacadeService.
 */
public interface ManageUserFacadeService {

	/**
	 * Gets the user.
	 *
	 * @param login the login
	 * @return the user
	 */
	UserDTO getUser(String login);
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 */
	void saveUser(UserDTO user);
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 */
	void updateUser(UserDTO user);
	
	/**
	 * Gets the role.
	 *
	 * @param role the name of the role
	 * @return the role
	 */
	RoleDTO getRole(String role);

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	List<RoleDTO> getRoles();

	/**
	 * Gets the user list.
	 *
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortUser the sort user
	 * @param filter the filter
	 * @param filterColumnName the filter column name
	 * @return the user list
	 */
	List<UserDTO> getUserList(int page, int size, String sortField,
			int sortUser, String filter, String filterColumnName);

	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	int getRowNumber();
}
