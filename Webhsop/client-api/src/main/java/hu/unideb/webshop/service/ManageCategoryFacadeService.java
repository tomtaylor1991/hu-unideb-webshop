package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.CategoryDTO;

import java.util.List;


public interface ManageCategoryFacadeService {
	public void createCategory(CategoryDTO category);
	public void updateCategory(CategoryDTO category);
	public void removeCategory(CategoryDTO category);
	List<CategoryDTO> getCategoryList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);
	
	public List<CategoryDTO> getAllCategory();
	
	public int getRowNumber();
	
	List<CategoryDTO> searchCategoryByName(String name);
	List<CategoryDTO> searchCategoryByParent(CategoryDTO parent);
	
	CategoryDTO getCategory(Long id);
}
