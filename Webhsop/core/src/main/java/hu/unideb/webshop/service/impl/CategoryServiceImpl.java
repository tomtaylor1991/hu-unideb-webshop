package hu.unideb.webshop.service.impl;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.webshop.dao.CategoryDao;
import hu.unideb.webshop.dao.ProductDao;
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.entity.Category;
import hu.unideb.webshop.service.CategoryService;

@Service("categoryService")
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	ProductDao productDao;

	@Override
	public void saveCategory(CategoryDTO category) {
		categoryDao.save(categoryDao.toEntity(category, null));
	}

	@Override
	public void updateCategory(CategoryDTO category) {
		categoryDao.save(categoryDao.toEntity(category, null));
	}

	@Override
	public void removeCategory(CategoryDTO category) {
		categoryDao.delete(categoryDao.toEntity(category, null));

	}

	@Override
	public List<CategoryDTO> getCategoryList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;

		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Order(dir, sortField)));
		List<CategoryDTO> ret = new ArrayList<CategoryDTO>(size);
		Page<Category> entities;
		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = categoryDao.findByNameStartsWith(filter, pageRequest);
		} else if (filter.length() != 0
				&& filterColumnName.equals("parent.name")) {
			entities = categoryDao.findByParentNameStartsWith(filter,
					pageRequest);
		} else {
			entities = categoryDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Category> contents = entities.getContent();
			for (Category c : contents) {
				ret.add(categoryDao.toDto(c));
			}
		}
		return ret;

	}

	@Override
	public int getRowNumber() {
		return categoryDao.countRowNum();
	}

	@Override
	public List<CategoryDTO> searchCategoryByName(String name) {
		List<CategoryDTO> ret = new LinkedList<CategoryDTO>();
		List<Category> entities = categoryDao
				.findByNameStartsWithAndParentIsNull(name);
		if (entities != null && entities.size() > 0) {
			for (Category c : entities) {
				ret.add(categoryDao.toDto(c));
			}
		}
		return ret;
	}

	@Override
	public CategoryDTO getCategory(Long id) {
		Category entity = categoryDao.findById(id);
		System.out.println(entity);
		return categoryDao.toDto(entity);
	}

	@Override
	public List<CategoryDTO> searchAllCategoryByName(String name) {
		List<CategoryDTO> ret = new LinkedList<CategoryDTO>();
		List<Category> entities = categoryDao.findByNameStartsWith(name);
		if (entities != null && entities.size() > 0) {
			for (Category c : entities) {
				ret.add(categoryDao.toDto(c));
			}
		}
		return ret;
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<CategoryDTO> ret = new LinkedList<CategoryDTO>();
		Iterable<Category> entities = categoryDao.findAll();
		for (Category c : entities) {
			ret.add(categoryDao.toDto(c));
		}
		return ret;
	}

	@Override
	public List<CategoryDTO> searchCategoryByParent(CategoryDTO parent) {
		List<CategoryDTO> ret = new LinkedList<CategoryDTO>();
		Iterable<Category> entities;
		if (parent == null) {
			entities = categoryDao.findByParent(null);
		} else {
			entities = categoryDao.findByParent(categoryDao
					.toEntity(parent, null));
		}
		for (Category c : entities) {
			CategoryDTO category = categoryDao.toDto(c);
			Integer childNumber = categoryDao.countChildNumber(category.getId());
			category.setChildNumber(childNumber != null ? childNumber : 0);
			Integer productNumber = productDao
					.countCategoryProductNumber(category.getId());
			category.setProductNumber(productNumber != null ? productNumber : 0);
			ret.add(category);
		}
		return ret;
	}
}
