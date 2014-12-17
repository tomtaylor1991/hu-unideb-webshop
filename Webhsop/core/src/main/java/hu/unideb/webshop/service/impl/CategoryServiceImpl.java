package hu.unideb.webshop.service.impl;

import java.util.ArrayList;
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
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.entity.Category;
import hu.unideb.webshop.service.CategoryService;

@Service("categoryService")
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

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
			for (Category recipe : contents) {
				ret.add(categoryDao.toDto(recipe));
			}
		}
		return ret;

	}

	@Override
	public int getRowNumber() {
		return categoryDao.countRowNum();
	}

}
