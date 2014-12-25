package hu.unideb.webshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.CategoryService;
import hu.unideb.webshop.service.ManageProductFacadeService;
import hu.unideb.webshop.service.ProductService;

@Service("manageProductFacadeService")
public class ManageProductFacadeServiceImpl implements
		ManageProductFacadeService {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@Override
	public void saveProduct(ProductDTO product) {
		productService.saveProduct(product);
	}

	@Override
	public void updateProduct(ProductDTO product) {
		productService.updateProduct(product);
	}

	@Override
	public void removeProduct(ProductDTO product) {
		productService.removeProduct(product);

	}

	@Override
	public List<ProductDTO> getProductList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		return productService.getProductList(page, size, sortField, sortOrder,
				filter, filterColumnName);
	}

	@Override
	public int getRowNumber() {
		return productService.getRowNumber();
	}

	@Override
	public List<CategoryDTO> searchCategoryByName(String name) {
		return categoryService.searchAllCategoryByName(name);
	}

	@Override
	public List<ProductDTO> searchProductByName(String name) {

		return productService.searchProductByName(name);
	}

}
