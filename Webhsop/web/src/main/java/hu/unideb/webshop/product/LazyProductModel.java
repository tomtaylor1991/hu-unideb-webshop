package hu.unideb.webshop.product;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyProductModel extends LazyDataModel<ProductDTO> {

	private static final long serialVersionUID = 1L;

	private ManageProductFacadeService manageProductFacadeService = null;
	private List<ProductDTO> visibleProductList;

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
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		System.out.println(sortField + " " + filter + " " + filterColumnName);
		// set default sort field
		if (sortField == null) {
			sortField = "name";
		}
		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleProductList = manageProductFacadeService.getProductList(first
				/ pageSize, pageSize, sortField, dir, filter, filterColumnName);

		int dataSize = manageProductFacadeService.getRowNumber();
		this.setRowCount(dataSize);

		return visibleProductList;
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}

	public List<ProductDTO> getVisibleProductList() {
		return visibleProductList;
	}

	public void setVisibleProductList(List<ProductDTO> visibleProductList) {
		this.visibleProductList = visibleProductList;
	}

	public LazyProductModel(
			ManageProductFacadeService manageProductFacadeService) {
		super();
		this.manageProductFacadeService = manageProductFacadeService;
	}

}
