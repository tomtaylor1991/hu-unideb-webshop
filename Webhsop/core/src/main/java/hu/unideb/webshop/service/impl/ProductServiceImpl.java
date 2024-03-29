package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.CategoryDao;
import hu.unideb.webshop.dao.ProductDao;
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.Category;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.service.ProductService;

import java.util.ArrayList;
import java.util.LinkedList;
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
	@Autowired
	CategoryDao categoryDao;

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
		} else if (filter.length() != 0
				&& filterColumnName.equals("category.name")) {
			entities = productDao.findByCategoryNameStartsWith(filter,
					pageRequest);
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

	@Override
	public List<ProductDTO> searchProductByName(String name) {
		List<ProductDTO> ret = new LinkedList<ProductDTO>();
		List<Product> entities = productDao.findByNameStartsWith(name);
		if (entities != null && entities.size() > 0) {
			for (Product p : entities) {
				ret.add(productDao.toDto(p));
			}
		}
		return ret;
	}

	@Override
	public List<ProductDTO> searchProductByCategory(CategoryDTO category,
			int page, int size, String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;

		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Order(dir, sortField)));
		List<ProductDTO> ret = new ArrayList<ProductDTO>(size);
		Page<Product> entities;
		Category categoryEntity = category != null ? categoryDao.toEntity(
				category, null) : null;
		entities = productDao.findByCategory(categoryEntity, pageRequest);
		if (entities != null && entities.getSize() > 0) {
			List<Product> contents = entities.getContent();
			for (Product p : contents) {
				ret.add(productDao.toDto(p));
			}
		}
		return ret;
	}

	@Override
	public ProductDTO getProduct(Long id) {
		return productDao.toDto(productDao.findOne(id));
	}

	@Override
	public List<ProductDTO> getHighlightedProducts() {
		List<ProductDTO> ret = new LinkedList<ProductDTO>();
		List<Product> entities = productDao.findByIsHighlightTrue();
		if (entities != null && entities.size() > 0) {
			for (Product p : entities) {
				ret.add(productDao.toDto(p));
			}
		}
		return ret;
	}
}
