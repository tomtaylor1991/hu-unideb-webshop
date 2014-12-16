package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.WarehouseDTO;

import java.util.List;

/**
 * The Interface WarehouseService.
 */
public interface WarehouseService {
	
	/**
	 * Gets the warehouse list.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the warehouse list
	 */
	List<WarehouseDTO> getWarehouseList(int page, int size);
	
	/**
	 * Gets the warehouse list.
	 *
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filter the filter
	 * @param filterColumnName the filter column name
	 * @return the warehouse list
	 */
	List<WarehouseDTO> getWarehouseList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);
	
	/**
	 * Creates the warehouse.
	 *
	 * @param warehouse the warehouse
	 */
	void createWarehouse(WarehouseDTO warehouse);
	
	/**
	 * Removes the warehouse.
	 *
	 * @param warehouse the warehouse
	 */
	void removeWarehouse(WarehouseDTO warehouse);
	
	/**
	 * Update warehouse.
	 *
	 * @param warehouse the warehouse
	 */
	void updateWarehouse(WarehouseDTO warehouse);

	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	int getRowNumber();
}
