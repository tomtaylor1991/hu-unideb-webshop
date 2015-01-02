package hu.unideb.webshop.categoryProduct;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageImageFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyCategoryProductModel extends LazyDataModel<ProductDTO> {

	private static final long serialVersionUID = 1L;

	private ManageProductFacadeService manageProductFacadeService = null;

	private ManageImageFacadeService manageImageFacadeService = null;

	private List<ProductDTO> visibleProductList;

	private CategoryDTO currentCategory = null;

	private int chunkSize = 5;
	private int pageNumber = 0;
	private int rowNumber = 0;

	@Override
	public ProductDTO getRowData(String rowKey) {
		// System.out.println("rowKey: "+rowKey);
		if (visibleProductList != null || rowKey != null) {
			for (ProductDTO c : visibleProductList) {
				if (c.getId().equals(rowKey)) {
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(ProductDTO c) {
		// System.out.println("rowKey2: "+c);
		if (c == null) {
			return null;
		}
		return c.getId();
	}

	@Override
	public List<ProductDTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		String filter = "";
		String filterColumnName = "";
		if (filters != null && filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}

		// set default sort field
		if (sortField == null) {
			sortField = "name";
		}
		int dir = SortOrder.ASCENDING.equals(sortOrder) ? 1 : 2;

		List<ProductDTO> newVisibleProductList = manageProductFacadeService
				.searchProductByCategory(currentCategory, first, pageSize,
						sortField, dir, filter, filterColumnName);

		System.out.println(first + " " + pageSize);
		System.out.println("List : : " + newVisibleProductList + " size: "
				+ newVisibleProductList.size());

		// Set images to the products
		for (ProductDTO product : newVisibleProductList) {
			try {
				product.setImages(manageImageFacadeService
						.getProductImages(product));
			} catch (NullPointerException e) {
				product.setImages(new LinkedList<ImageInfoDTO>());
			}
			this.visibleProductList.add(product);
		}
		// /
		return visibleProductList;
	}

	public void loadMore() {
		List<ProductDTO> list = load(pageNumber, chunkSize, null, null, null);
		this.pageNumber++;
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}

	public LazyCategoryProductModel(
			ManageProductFacadeService manageProductFacadeService,
			ManageImageFacadeService manageImageFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
		this.manageImageFacadeService = manageImageFacadeService;
		initProductList();
	}

	public void initProductList() {
		this.visibleProductList = new LinkedList<ProductDTO>();
		this.pageNumber = 0;
		rowNumber = manageProductFacadeService
				.countCategoryProductNumber(currentCategory);
		this.setRowCount(rowNumber);
		loadMore();
	}

	public void initProductList(CategoryDTO category) {
		setCurrentCategory(category);
		initProductList();
	}

	public List<ProductDTO> getVisibleProductList() {
		return visibleProductList;
	}

	public void setVisibleProductList(List<ProductDTO> visibleProductList) {
		this.visibleProductList = visibleProductList;
	}

	public CategoryDTO getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(CategoryDTO currentCategory) {
		this.currentCategory = currentCategory;
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

}
