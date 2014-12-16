package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.WarehouseDao;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Warehouse;
import hu.unideb.webshop.service.UserService;
import hu.unideb.webshop.service.WarehouseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	WarehouseDao warehouseDao;

	@Autowired
	UserService userService;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<WarehouseDTO> getWarehouseList(int page, int size) {

		List<WarehouseDTO> ret = new ArrayList<WarehouseDTO>(size);
		Page<Warehouse> entities = warehouseDao.findAll(new PageRequest(page,
				size));

		if (entities != null && entities.getSize() > 0) {
			List<Warehouse> content = entities.getContent();

			for (Warehouse warehouse : content){
				ret.add(warehouseDao.toDto(warehouse));
			}
		}

		return ret;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void createWarehouse(WarehouseDTO warehouse) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			warehouse.setRecUserId(userService.getUser(
					userDetails.getUsername()).getId()
					+ "");
			warehouse.setRecDate(new Date());
			warehouse.setModUserId(userService.getUser(
					userDetails.getUsername()).getId()
					+ "");
			warehouse.setModDate(new Date());
		}
        Warehouse warehouseentity = warehouseDao.toEntity(warehouse, null);
		warehouseentity = warehouseDao.save(warehouseentity);
		warehouse.setId(warehouseDao.toDto(warehouseentity).getId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeWarehouse(WarehouseDTO warehouse) {
		warehouseDao.delete(warehouseDao.toEntity(warehouse, null));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateWarehouse(WarehouseDTO warehouse) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			warehouse.setModUserId(userService.getUser(
					userDetails.getUsername()).getId()
					+ "");
			warehouse.setModDate(new Date());
		}
		warehouseDao.save(warehouseDao.toEntity(warehouse, null));
	}

	@Override
	public List<WarehouseDTO> getWarehouseList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Order(dir, sortField)));
		List<WarehouseDTO> ret = new ArrayList<WarehouseDTO>(size);
		Page<Warehouse> entities;
		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = warehouseDao.findByNameStartsWith(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("address")) {
			entities = warehouseDao
					.findByAddressContaining(filter, pageRequest);
		} else {
			entities = warehouseDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Warehouse> contents = entities.getContent();
			for (Warehouse w : contents) {
				ret.add(warehouseDao.toDto(w));
			}
		}		
		return ret;
	}

	@Override
	public int getRowNumber() {
		
		return warehouseDao.countById();
	}

}
