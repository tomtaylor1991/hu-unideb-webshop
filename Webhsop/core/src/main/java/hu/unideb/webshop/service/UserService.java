package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.UserDTO;

import java.util.List;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Gets the user.
	 *
	 * @param login the login
	 * @return the user
	 */
	UserDTO getUser(String login);
	
	/**
	 * Saves the user.
	 *
	 * @param user the user
	 */
	void save(UserDTO user);
	
	/**
	 * Update the user.
	 *
	 * @param user the user
	 */
	void update(UserDTO user);

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
