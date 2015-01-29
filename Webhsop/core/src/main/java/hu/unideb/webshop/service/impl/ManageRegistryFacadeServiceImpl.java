package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ComponentService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.RecipeService;
import hu.unideb.webshop.service.RegistryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageRegistryFacadeService")
public class ManageRegistryFacadeServiceImpl implements
        ManageRegistryFacadeService {

    @Autowired
    RegistryService registryService;

    @Autowired
    ComponentService componentService;

    @Autowired
    RecipeService recipeService;


    @Autowired
    OrderDao orderDao;

    @Autowired
    RegistryDao registryDao;

    @Override
    public double costOfMaterial(RegistryDTO registryDTO) {
        return 0.0;
    }

    @Override
    public void saveRegistrys(List<RegistryDTO> registry) {
        registryService.saveRegistrys(registry);
    }

    @Override
    public List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse) {
        return registryService.getRegistrysByMaterial(warehouse);
    }

    @Override
    public List<RegistryDTO> findByMaterial(MaterialDTO material) {
        return registryService.findByMaterial(material);
    }

    @Override
    public List<RegistryDTO> findByOrder(OrderDTO order) {
        return registryService.findByOrder(order);
    }

    @Override
    public List<RegistryDTO> findByStatus(String status) {
        return registryService.findByStatus(status);
    }

    @Override
    public void updateRegistry(RegistryDTO registry) {
        registryService.updateRegistry(registry);
    }

    @Override
    public List<RegistryDTO> getRegistrySortedList(int page, int size,
            String sortField, int sortOrder, String filter,
            String filterColumnName) {
        return registryService.getRegistrySortedList(page, size, sortField,
                sortOrder, filter, filterColumnName);
    }

    @Override
    public LeaderTestInfoDTO isOrderCanBeServe(OrderDTO order) {
		return registryService.getOrderData(order);
    }

    @Override
    public boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material,
            int quantity) {

        return registryService.keepMaterialForOrder(order, material, quantity);
    }


    @Override
    public void deleteRegistry(RegistryDTO registry) {
        registryService.deleteRegistry(registry);
    }

    @Override
    public List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order) {

        return registryService.findByStatusAndOrder(status, order);
    }

	@Override
	public void createProductNeedForOrder(ProductDTO product, OrderDTO order,
			int quantity) {
		registryService.createProductNeedForOrder(product, order, quantity);
	}

	@Override
	public int keepProductForOrder(ProductDTO product, OrderDTO order,
			int quantity) {
		return registryService.keepProductForOrder(product, order, quantity);
	}

	@Override
	public List<RegistryDTO> getRegistrysByWarehouse(WarehouseDTO warehouse) {

		return registryService.getRegistrysByWarehouse(warehouse);
	}

}
