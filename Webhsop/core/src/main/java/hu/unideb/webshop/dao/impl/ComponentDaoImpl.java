package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.MaterialDao;
import hu.unideb.webshop.dao.RecipeDao;
import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.entity.Component;

import org.springframework.beans.factory.annotation.Autowired;

public class ComponentDaoImpl implements BaseConvertDao<Component, ComponentDTO>{
	
	@Autowired
	RecipeDao recipeDao;
	
	@Autowired
	MaterialDao materialDao;
	
	@Override
	public Component toEntity(ComponentDTO dto, Component entity) {
		Component ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Component();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setRecipeId(dto.getRecipeDTO().getId());
		ret.setMaterialId(dto.getMaterialDTO().getId());
		ret.setQuantity(dto.getQuantity());
		ret.setComment(dto.getComment());
		return ret;
	}

	@Override
	public ComponentDTO toDto(Component entity) {
		ComponentDTO ret = new ComponentDTO();
		ret.setId(entity.getId());
		ret.setQuantity(entity.getQuantity());
		ret.setComment(entity.getComment());
		ret.setRecipeDTO(recipeDao.toDto(recipeDao.findOne(entity.getRecipeId())));
		ret.setMaterialDTO(materialDao.toDto(materialDao.findOne(entity.getMaterialId())));
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		return ret;
	}

	
}
