package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyOrderModel extends LazyDataModel<OrderDTO> {

	private static final long serialVersionUID = 1L;

	private ManageOrderFacadeService manageOrderFacadeService = null;
	private ManageRegistryFacadeService manageRegistryFacadeService = null;
	private List<OrderDTO> visibleOrderList;
	private boolean isNeedRegistryInfo = false;

	public LazyOrderModel() {

	}

	public LazyOrderModel(ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public LazyOrderModel(ManageOrderFacadeService manageOrderFacadeService,
			ManageRegistryFacadeService manageRegistryFacadeService,
			boolean info) {
		this.manageOrderFacadeService = manageOrderFacadeService;
		this.manageRegistryFacadeService = manageRegistryFacadeService;
		isNeedRegistryInfo = info;
	}

	@Override
	public OrderDTO getRowData(String rowKey) {
		if (visibleOrderList != null || rowKey != null) {
			for (OrderDTO order : visibleOrderList) {
				if (order.getId().equals(rowKey)) {
					return order;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(OrderDTO order) {
		if (order == null) {
			return null;
		}
		return order.getId();
	}

	@Override
	public List<OrderDTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		/*
		 * System.out.println("Recipe lazy" + first + " " + pageSize + " " +
		 * sortField + " " + sortOrder + " " + filters);
		 */
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "status";
		}
		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleOrderList = manageOrderFacadeService.getOrderList(first
				/ pageSize, pageSize, sortField, dir, filter, filterColumnName);
		int dataSize = manageOrderFacadeService.getRowNumber();
		this.setRowCount(dataSize);

		if (isNeedRegistryInfo) {
			for (OrderDTO order : visibleOrderList) {
				order.setInfo(manageRegistryFacadeService
						.isOrderCanBeServe(order));
			}
		}
		return visibleOrderList;
	}

	public ManageOrderFacadeService getmanageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setmanagevFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public List<OrderDTO> getVisibleOrderList() {
		return visibleOrderList;
	}

	public void setVisibleOrderList(List<OrderDTO> visibleOrderList) {
		this.visibleOrderList = visibleOrderList;
	}

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	public boolean isNeedRegistryInfo() {
		return isNeedRegistryInfo;
	}

	public void setNeedRegistryInfo(boolean isNeedRegistryInfo) {
		this.isNeedRegistryInfo = isNeedRegistryInfo;
	}

}
