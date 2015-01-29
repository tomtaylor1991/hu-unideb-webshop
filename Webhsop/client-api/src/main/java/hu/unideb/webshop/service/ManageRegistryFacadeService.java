package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;

import java.util.List;

public interface ManageRegistryFacadeService {

	void saveRegistrys(List<RegistryDTO> registry);

	void updateRegistry(RegistryDTO registry);

	void deleteRegistry(RegistryDTO registry);

	List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse);
	List<RegistryDTO> getRegistrysByWarehouse(WarehouseDTO warehouse);
	
	List<RegistryDTO> findByMaterial(MaterialDTO material);

	List<RegistryDTO> findByStatus(String status);

	List<RegistryDTO> getRegistrySortedList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName);

	List<RegistryDTO> findByOrder(OrderDTO order);

	LeaderTestInfoDTO isOrderCanBeServe(OrderDTO order);

	boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material,
			int quantity);

	double costOfMaterial(RegistryDTO registryDTO);

	List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order);
	
	void createProductNeedForOrder(ProductDTO product, OrderDTO order, int quantity);
	int keepProductForOrder(ProductDTO product, OrderDTO order, int quantity);
}
