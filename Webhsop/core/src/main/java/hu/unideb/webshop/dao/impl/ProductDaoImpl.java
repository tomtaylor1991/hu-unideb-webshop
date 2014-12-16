package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.Product;

public class ProductDaoImpl implements BaseConvertDao<Product, ProductDTO>{

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
        ret.setSpecial(dto.getIsSpecial());
        ret.setSpecialPrice(dto.getSpecialPrice());
        ret.setHighlight(dto.getIsHighlight());
        ret.setShortText(dto.getShortText());
        
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
        ret.setSpecial(entity.getIsSpecial());
        ret.setSpecialPrice(entity.getSpecialPrice());
        ret.setHighlight(entity.getIsHighlight());
        ret.setShortText(entity.getShortText());
		return ret;
	}

}
