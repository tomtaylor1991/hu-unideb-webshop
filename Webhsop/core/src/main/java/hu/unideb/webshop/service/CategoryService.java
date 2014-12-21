package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
	public void saveCategory(CategoryDTO category);
	public void updateCategory(CategoryDTO category);
	public void removeCategory(CategoryDTO category);
	List<CategoryDTO> getCategoryList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);
	public int getRowNumber();
	
	List<CategoryDTO> searchCategoryByName(String name);
	List<CategoryDTO> searchAllCategoryByName(String name);

	CategoryDTO getCategory(Long id);
}
