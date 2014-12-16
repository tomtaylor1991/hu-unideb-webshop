package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.entity.Material;

public class MaterialDaoImpl implements BaseConvertDao<Material, MaterialDTO>{

	@Override
	public Material toEntity(MaterialDTO dto, Material entity) {
		Material ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Material();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setMaterialName(dto.getMaterialName());
		ret.setCost(dto.getCost());
		return ret;
	}

	@Override
	public MaterialDTO toDto(Material entity) {
		MaterialDTO ret = new MaterialDTO();
		
		ret.setId(entity.getId());
		ret.setMaterialName(entity.getMaterialName());
		ret.setCost(entity.getCost());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());

		return ret;
	}

}
