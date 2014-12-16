package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Warehouse;

public class WarehouseDaoImpl implements BaseConvertDao<Warehouse, WarehouseDTO> {

	@Override
	public WarehouseDTO toDto(Warehouse entity) {
		WarehouseDTO ret = new WarehouseDTO();
		
		ret.setId(entity.getId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setName(entity.getName());
		ret.setAddress(entity.getAddress());
		
		return ret;
	}
	
	@Override
	public Warehouse toEntity(WarehouseDTO dto, Warehouse entity) {
		Warehouse ret = entity;
		
		if (dto.getId() == null || entity == null) {
			ret = new Warehouse();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setName(dto.getName());
		ret.setAddress(dto.getAddress());
		
		return ret;
	}
	
}
