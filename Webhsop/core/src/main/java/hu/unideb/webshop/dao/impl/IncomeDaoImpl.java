package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.entity.Income;

public class IncomeDaoImpl implements BaseConvertDao<Income, IncomeDTO>{

	@Override
	public Income toEntity(IncomeDTO dto, Income entity) {
		Income ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Income();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setComment(dto.getComment());
		ret.setPrice(dto.getPrice());
		ret.setOrderId(dto.getOrderId());
		ret.setType(dto.getType());
		ret.setName(dto.getName());
		ret.setQuantity(dto.getQuantity());
		return ret;
	}

	@Override
	public IncomeDTO toDto(Income entity) {
		IncomeDTO ret = new IncomeDTO();
		ret.setId(entity.getId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setComment(entity.getComment());
		ret.setPrice(entity.getPrice());
		ret.setOrderId(entity.getOrderId());
		ret.setType(entity.getType());
		ret.setName(entity.getName());
		ret.setQuantity(entity.getQuantity());
		return ret;
	}



}
