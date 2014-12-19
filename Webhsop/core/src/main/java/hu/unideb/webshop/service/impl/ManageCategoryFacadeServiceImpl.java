package hu.unideb.webshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.service.CategoryService;
import hu.unideb.webshop.service.ManageCategoryFacadeService;

@Service("manageCategoryFacadeService")
public class ManageCategoryFacadeServiceImpl implements
		ManageCategoryFacadeService {

	@Autowired
	CategoryService categoryService;
	
	@Override
	public void createCategory(CategoryDTO category) {
		categoryService.saveCategory(category);

	}

	@Override
	public void updateCategory(CategoryDTO category) {
		categoryService.updateCategory(category);

	}

	@Override
	public void removeCategory(CategoryDTO category) {
		categoryService.removeCategory(category);

	}

	@Override
	public List<CategoryDTO> getCategoryList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		
		return categoryService.getCategoryList(page, size, sortField, sortOrder, filter, filterColumnName);
	}

	@Override
	public int getRowNumber() {
		return categoryService.getRowNumber();
	}

	@Override
	public List<CategoryDTO> searchCategoryByName(String name) {
		return categoryService.searchCategoryByName(name);
	}

	@Override
	public CategoryDTO getCategory(Long id) {
		System.out.println("--- " + id);
		return categoryService.getCategory(id);
	}

}
