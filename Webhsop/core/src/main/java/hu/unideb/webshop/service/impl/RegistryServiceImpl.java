package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dao.WarehouseDao;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.entity.Registry;
import hu.unideb.webshop.service.RegistryService;
import hu.unideb.webshop.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("registryService")
@Transactional(propagation = Propagation.REQUIRED)
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    RegistryDao registryDao;

    @Autowired
    WarehouseDao warehouseDao;


    @Autowired
    OrderDao orderDao;

    @Autowired
    UserService userService;

	@Override
	public void saveRegistrys(List<RegistryDTO> registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateRegistry(RegistryDTO registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRegistry(RegistryDTO registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RegistryDTO> findByMaterial(MaterialDTO material) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistryDTO> findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistryDTO> getRegistrySortedList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistryDTO> findByOrder(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int sumQuantityByMaterial(MaterialDTO material) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Registry> findByStatusAndBeerAndOrder(String status,
			Product product, hu.unideb.webshop.entity.Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material,
			int quantity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

  

}
