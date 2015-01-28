package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ManageWarehouseFacadeService;
import hu.unideb.webshop.service.WarehouseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageWarehouseFacadeService")
public class ManageWarehouseFacadeServiceImpl implements
		ManageWarehouseFacadeService {

	@Autowired
	WarehouseService warehouseService;

	@Override
	public List<WarehouseDTO> getWarehouseList(int page, int size) {
		return warehouseService.getWarehouseList(page, size);
	}

	@Override
	public void createWarehouse(WarehouseDTO warehouse) {
		warehouseService.createWarehouse(warehouse);
	}

	@Override
	public void removeWarehouse(WarehouseDTO warehouse) {
		warehouseService.removeWarehouse(warehouse);
	}

	@Override
	public void updateWarehouse(WarehouseDTO warehouse) {
		warehouseService.updateWarehouse(warehouse);
	}

	@Override
	public List<WarehouseDTO> getWarehouseList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {		
		return warehouseService.getWarehouseList(page, size, sortField, sortOrder, filter, filterColumnName);
	}

	@Override
	public int getRowNumber() {
		
		return warehouseService.getRowNumber();
	}

	@Override
	public List<Need> getWhQuantityStatics(WarehouseDTO warehosue) {
		
		return warehouseService.getWhQuantityStatics(warehosue);
	}
}
