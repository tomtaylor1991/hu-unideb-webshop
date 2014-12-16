package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.BeerDao;
import hu.unideb.webshop.dao.MaterialDao;
import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.WarehouseDao;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.entity.Registry;

import org.springframework.beans.factory.annotation.Autowired;

public class RegistryDaoImpl implements BaseConvertDao<Registry, RegistryDTO> {

	@Autowired
	BeerDao beerDao;

	@Autowired
	MaterialDao materialDao;

	@Autowired
	WarehouseDao warehouseDao;

	@Autowired
	OrderDao orderDao;

	@Override
	public Registry toEntity(RegistryDTO dto, Registry entity) {
		Registry ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Registry();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		if (dto.getBeer() != null) {
			ret.setBeer(beerDao.toEntity(dto.getBeer(), null));
		}
		if (dto.getMaterial() != null) {
			ret.setMaterial(materialDao.toEntity(dto.getMaterial(), null));
		}
		if (dto.getWarehouse() != null) {
			ret.setWarehouse(warehouseDao.toEntity(dto.getWarehouse(), null));
		}
		if (dto.getOrder() != null) {
			ret.setOrder(orderDao.save(orderDao.toEntity(dto.getOrder(), null)));
		}
		ret.setQuantity(dto.getQuantity());
		ret.setStatus(dto.getStatus());
		ret.setOriginalQuantity(dto.getOriginalQuantity());
		return ret;
	}

	@Override
	public RegistryDTO toDto(Registry entity) {
		RegistryDTO ret = new RegistryDTO();
		if (entity.getBeer() != null) {
			ret.setBeer(beerDao.toDto(entity.getBeer()));
		}
		if (entity.getMaterial() != null) {
			ret.setMaterial(materialDao.toDto(entity.getMaterial()));
		}
		if (entity.getWarehouse() != null) {
			ret.setWarehouse(warehouseDao.toDto(entity.getWarehouse()));
		}
		if (entity.getOrder() != null) {
			ret.setOrder(orderDao.toDto(entity.getOrder()));
		}
		ret.setModDate(entity.getModDate());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setModUserId(entity.getModUserId());
		ret.setQuantity(entity.getQuantity());
		ret.setId(entity.getId());
		ret.setStatus(entity.getStatus());
		ret.setOriginalQuantity(entity.getOriginalQuantity());
		return ret;
	}

}
