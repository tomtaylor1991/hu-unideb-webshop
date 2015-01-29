package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.entity.Registry;

import java.util.List;

public interface RegistryService {
	
	void saveRegistrys(List<RegistryDTO> registry);

	List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse);

	void updateRegistry(RegistryDTO registry);

    void deleteRegistry(RegistryDTO registry);
    
    public int countFreeProductNumber(ProductDTO product);

	List<RegistryDTO> findByMaterial(MaterialDTO material);
	List<RegistryDTO> getRegistrysByWarehouse(WarehouseDTO warehouse);

	List<RegistryDTO> findByStatus(String status);

	List<RegistryDTO> getRegistrySortedList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);

	List<RegistryDTO> findByOrder(OrderDTO order);

	int sumQuantityByMaterial(MaterialDTO material);

	List<Registry> findByStatusAndBeerAndOrder(String status, Product product,
			Order order);

	boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material, int quantity);

	List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order);

	LeaderTestInfoDTO getOrderData(OrderDTO order);
	
	void createProductNeedForOrder(ProductDTO product, OrderDTO order, int quantity);
	int keepProductForOrder(ProductDTO product, OrderDTO order, int quantity);
}
