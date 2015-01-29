package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.CategoryDao;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductDaoImpl implements BaseConvertDao<Product, ProductDTO> {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public Product toEntity(ProductDTO dto, Product entity) {
		Product ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Product();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setName(dto.getName());
		ret.setRate(dto.getRate());
		ret.setText(dto.getText());
		ret.setPrice(dto.getPrice());
		ret.setIsSpecial(dto.getIsSpecial());
		ret.setSpecialPrice(dto.getSpecialPrice());
		ret.setIsHighlight(dto.getIsHighlight());
		ret.setShortText(dto.getShortText());
		ret.setPurchasePrice(dto.getPurchasePrice());
		if (dto.getCategory() != null) {
			ret.setCategory(categoryDao.toEntity(dto.getCategory(), null));
		}
		return ret;
	}

	@Override
	public ProductDTO toDto(Product entity) {
		ProductDTO ret = new ProductDTO();
		ret.setId(entity.getId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		ret.setName(entity.getName());
		ret.setRate(entity.getRate());
		ret.setText(entity.getText());
		ret.setPrice(entity.getPrice());
		ret.setIsSpecial(entity.getIsSpecial());
		ret.setSpecialPrice(entity.getSpecialPrice());
		ret.setIsHighlight(entity.getIsHighlight());
		ret.setShortText(entity.getShortText());
		ret.setPurchasePrice(entity.getPurchasePrice());
		if (entity.getCategory() != null) {
			ret.setCategory(categoryDao.toDto(entity.getCategory()));
		}
		return ret;
	}

}
