package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.ProductDao;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public void saveProduct(ProductDTO product) {
		productDao.save(productDao.toEntity(product, null));
	}

	@Override
	public void updateProduct(ProductDTO product) {
		productDao.save(productDao.toEntity(product, null));
	}

	@Override
	public void removeProduct(ProductDTO product) {
		productDao.delete(productDao.toEntity(product, null));
	}

	@Override
	public List<ProductDTO> getProductList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;

		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Order(dir, sortField)));
		List<ProductDTO> ret = new ArrayList<ProductDTO>(size);
		Page<Product> entities;
		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = productDao.findByNameStartsWith(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("category.name")) {
			entities = productDao.findByCategoryNameStartsWith(filter, pageRequest);
		} else {
			entities = productDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Product> contents = entities.getContent();
			for (Product p : contents) {
				ret.add(productDao.toDto(p));
			}
		}
		return ret;
	}

	@Override
	public int getRowNumber() {
		return productDao.countRowNum();
	}

}
