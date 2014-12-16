package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.MaterialDTO;

import java.util.List;

/**
 * The Interface MaterialService.
 */
public interface MaterialService {

	/**
	 * Gets the material list.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the material list
	 */
	List<MaterialDTO> getMaterialList(int page, int size);

	/**
	 * Gets the material list.
	 *
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filter the filter
	 * @param filterColumnName the filter column name
	 * @return the material list
	 */
	List<MaterialDTO> getMaterialList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);
	
	/**
	 * Creates the material.
	 *
	 * @param material the material
	 */
	void createMaterial(MaterialDTO material);
	
	/**
	 * Update material.
	 *
	 * @param material the material
	 */
	void updateMaterial(MaterialDTO material);
	
	/**
	 * Removes the material.
	 *
	 * @param material the material
	 */
	void removeMaterial(MaterialDTO material);
	
	/**
	 * Gets the material.
	 *
	 * @param materialId the material id
	 * @return the material
	 */
	MaterialDTO getMaterial(Long materialId);
	
	/**
	 * Gets the all material list.
	 *
	 * @return the all material list
	 */
	List<MaterialDTO> getAllMaterialList();

	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	int getRowNumber();
}
