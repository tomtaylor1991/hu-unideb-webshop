package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.entity.Category;

public class CategoryDaoImpl implements BaseConvertDao<Category, CategoryDTO> {

	@Override
	public Category toEntity(CategoryDTO dto, Category entity) {
		Category ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Category();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		if (dto.getParent() != null) {
			ret.setParent(toEntity(dto.getParent(), null));
		}
		ret.setName(dto.getName());
		ret.setImage(dto.getImage());
		ret.setPriority(dto.getPriority());

		return ret;
	}

	@Override
	public CategoryDTO toDto(Category entity) {
		CategoryDTO ret = new CategoryDTO();

		ret.setId(entity.getId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		if (entity.getParent() != null) {
			ret.setParent(toDto(entity.getParent()));
		}
		ret.setName(entity.getName());
		ret.setImage(entity.getImage());
		ret.setPriority(entity.getPriority());

		return ret;
	}

}
