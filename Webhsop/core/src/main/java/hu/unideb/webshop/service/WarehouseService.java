package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.WarehouseDTO;

import java.util.List;

/**
 * The Interface WarehouseService.
 */
public interface WarehouseService {

	List<WarehouseDTO> getWarehouseList(int page, int size);

	List<WarehouseDTO> getWarehouseList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);

	void createWarehouse(WarehouseDTO warehouse);

	void removeWarehouse(WarehouseDTO warehouse);

	void updateWarehouse(WarehouseDTO warehouse);

	int getRowNumber();
	
	List<Need> getWhQuantityStatics(WarehouseDTO warehosue);
}
