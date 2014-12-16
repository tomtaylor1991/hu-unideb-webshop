package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;

import java.util.List;

/**
 * The Interface ManageRegistryFacadeService.
 */
public interface ManageRegistryFacadeService {
	
	/**
	 * Save registrys.
	 *
	 * @param registry the registry
	 */
	void saveRegistrys(List<RegistryDTO> registry);

	/**
	 * Update registry.
	 *
	 * @param registry the registry
	 */
	void updateRegistry(RegistryDTO registry);

	/**
	 * Delete registry.
	 *
	 * @param registry the registry
	 */
	void deleteRegistry(RegistryDTO registry);

	/**
	 * Gets the registrys by material.
	 *
	 * @param warehouse the warehouse
	 * @return the registrys by material
	 */
	List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse);

	/**
	 * Gets the registrys by beer.
	 *
	 * @param warehouse the warehouse
	 * @return the registrys by beer
	 */
	List<RegistryDTO> getRegistrysByBeer(WarehouseDTO warehouse);

	/**
	 * Find by material.
	 *
	 * @param material the material
	 * @return the list
	 */
	List<RegistryDTO> findByMaterial(MaterialDTO material);

	/**
	 * Find by status.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<RegistryDTO> findByStatus(String status);

	/**
	 * Gets the registry sorted list.
	 *
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filter the filter
	 * @param filterColumnName the filter column name
	 * @return the registry sorted list
	 */
	List<RegistryDTO> getRegistrySortedList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName);

	/**
	 * Find by order.
	 *
	 * @param order the order
	 * @return the list
	 */
	List<RegistryDTO> findByOrder(OrderDTO order);

	/**
	 * Checks if is order can be serve.
	 *
	 * @param order the order
	 * @return the leader test info dto
	 */
	LeaderTestInfoDTO isOrderCanBeServe(OrderDTO order);

	/**
	 * Keep material for order.
	 *
	 * @param order the order
	 * @param material the material
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material,
			int quantity);

	/**
	 * Creates the beer need for order.
	 *
	 * @param order the order
	 * @param beer the beer
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	boolean createBeerNeedForOrder(OrderDTO order, BeerDTO beer, int quantity);

	/**
	 * Cost of material.
	 *
	 * @param registryDTO the registry dto
	 * @return the double
	 */
	double costOfMaterial(RegistryDTO registryDTO);

	/**
	 * Find by status and order.
	 *
	 * @param status the status
	 * @param order the order
	 * @return the list
	 */
	List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order);
}
