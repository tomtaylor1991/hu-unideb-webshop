package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.ProductDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dao.WarehouseDao;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO.Need;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.entity.Registry;
import hu.unideb.webshop.service.RegistryService;
import hu.unideb.webshop.service.UserService;

import java.util.LinkedList;
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
	ProductDao productDao;

	@Autowired
	UserService userService;

	@Override
	public void saveRegistrys(List<RegistryDTO> registry) {
		for (RegistryDTO r : registry) {
			registryDao.save(registryDao.toEntity(r, null));
		}
	}

	@Override
	public List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRegistry(RegistryDTO registry) {
		registryDao.save(registryDao.toEntity(registry, null));
	}

	@Override
	public void deleteRegistry(RegistryDTO registry) {
		registryDao.delete(registryDao.toEntity(registry, null));
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
		List<Registry> entities = registryDao.findByOrderAndStatus(
				orderDao.toEntity(order, null), "ORDERDATA");
		List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
		for (Registry r : entities) {
			ret.add(registryDao.toDto(r));
		}
		return ret;
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
		List<Registry> entities = registryDao.findByOrderAndStatus(
				orderDao.toEntity(order, null), status);
		List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
		for (Registry r : entities) {
			ret.add(registryDao.toDto(r));
		}
		return ret;
	}

	@Override
	public LeaderTestInfoDTO getOrderData(OrderDTO order) {
		LeaderTestInfoDTO info = new LeaderTestInfoDTO();
		info.setOrder(order);
		List<Registry> orderData = registryDao.findByOrderAndStatus(
				orderDao.toEntity(order, null), "ORDERDATA");
		List<Need> needs = new LinkedList<LeaderTestInfoDTO.Need>();
		for (Registry r : orderData) {
			Need need = new Need();
			need.setProduct(productDao.toDto(r.getProduct()));
			need.setOriginalQuantity(r.getOriginalQuantity());
			need.setNeed(r.getQuantity() >= 0 ? r.getQuantity() : 0);
			need.setReadyQuantity(r.getOriginalQuantity() - r.getQuantity());
			need.setRegistry(registryDao.toDto(r));
			Integer inWhValue = registryDao.countByProductIdAndStatus(need
					.getProduct().getId(), "FREE");
			need.setInWHQuantity(inWhValue == null ? 0 : inWhValue);

			needs.add(need);
		}
		info.setNeed(needs);
		info.calculate();
		return info;
	}

	@Override
	public void createProductNeedForOrder(ProductDTO product, OrderDTO order,
			int quantity) {
		Registry registry = new Registry();
		registry.setProduct(productDao.toEntity(product, null));
		registry.setQuantity(quantity);
		registry.setStatus("NEED");
		registry.setOrder(orderDao.toEntity(order, null));
		registryDao.save(registry);
	}

	@Override
	public int keepProductForOrder(ProductDTO product, OrderDTO order,
			int quantity) {
		List<Registry> freeProducts = registryDao.findByOrderAndStatus(null,
				"FREE");
		int counter = 0;
		for (Registry currentRegistry : freeProducts) {
			quantity -= currentRegistry.getQuantity();
			if (quantity >= 0) {
				currentRegistry.setOrder(orderDao.toEntity(order, null));
				currentRegistry.setStatus("READY");
				registryDao.save(currentRegistry);
				counter+=currentRegistry.getQuantity();
			} else {
				Registry newRegistry = new Registry();
				newRegistry.setProduct(currentRegistry.getProduct());
				newRegistry.setQuantity(0 - quantity);
				newRegistry.setStatus("FREE");
				registryDao.save(newRegistry);
				// /
				currentRegistry.setOrder(orderDao.toEntity(order, null));
				currentRegistry.setStatus("READY");
				currentRegistry.setQuantity(currentRegistry.getQuantity()
						+ quantity);
				registryDao.save(currentRegistry);
				counter+=currentRegistry.getQuantity();
				break;
			}
		}
		return counter;
	}

	@Override
	public int countFreeProductNumber(ProductDTO product) {
		Integer ret = registryDao.countByProductIdAndStatus(product.getId(),
				"FREE");
		return ret != null ? ret : 0;
	}
}
