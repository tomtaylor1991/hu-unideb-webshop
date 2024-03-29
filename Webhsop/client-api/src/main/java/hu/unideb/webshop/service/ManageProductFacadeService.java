package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ProductDTO;

import java.util.List;

public interface ManageProductFacadeService {
	
	public ProductDTO getProduct(Long id);
	public void saveProduct(ProductDTO product);

	public void updateProduct(ProductDTO product);

	public void removeProduct(ProductDTO product);

	List<ProductDTO> getProductList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);

	public int getRowNumber();

	List<CategoryDTO> searchCategoryByName(String name);

	public List<ProductDTO> searchProductByName(String name);

	public List<ProductDTO> searchProductByCategory(CategoryDTO category,
			int page, int size, String sortField, int sortOrder, String filter,
			String filterColumnName);

	int countFreeProductNumber(ProductDTO product);
	public int countCategoryProductNumber(CategoryDTO category);

	public List<ProductDTO> getHighlightedProducts();
	
}
