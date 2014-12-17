package hu.unideb.webshop.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.ImageInfoDao;
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.entity.Category;

public class CategoryDaoImpl implements BaseConvertDao<Category, CategoryDTO> {

	@Autowired
	ImageInfoDao imageInfoDao;

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
		if (dto.getImageInfoId() != null) {
			ret.setImageInfoId(dto.getImageInfoId());
		}
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
		if (entity.getImageInfoId() != null) {
			ret.setImageInfoId(entity.getImageInfoId());
			ret.setImage(imageInfoDao.toDto(imageInfoDao.findOne(entity
					.getImageInfoId())));

		}

		ret.setPriority(entity.getPriority());

		return ret;
	}

}
