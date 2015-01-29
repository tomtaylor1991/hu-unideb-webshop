package hu.unideb.webshop.exporter;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.order.LazyOrderModel;
import hu.unideb.webshop.service.ManageIncomeFacadeService;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "exporterController")
public class ExporterController implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyOrderModel orderModel;
	private OrderDTO selectedOrder;
	private Set<OrderDTO> exportList = new LinkedHashSet<OrderDTO>();

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	@ManagedProperty(value = "#{manageIncomeFacadeService}")
	private ManageIncomeFacadeService manageIncomeFacadeService;

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public OrderDTO getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(OrderDTO selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	@PostConstruct
	public void init() {
		orderModel = new LazyOrderModel(manageOrderFacadeService,
				manageRegistryFacadeService, true);
		List<String> includedStatuses = new LinkedList<String>();
		includedStatuses.add("TRANSPORT");
		includedStatuses.add("DURINGTRANSPORT");
		orderModel.setIncludedStatuses(includedStatuses);
	}

	public void addOrderToExportList(OrderDTO order) {
		if (order.getStatus().equals("TRANSPORT")) {
			exportList.add(order);
		}
	}

	public void saveExport() {
		for (OrderDTO order : exportList) {
			order.setStatus("DURINGTRANSPORT");
			manageOrderFacadeService.updateOrder(order);
		}

	}

	public void exportReady(OrderDTO order) {
		List<RegistryDTO> removalRegistrys = manageRegistryFacadeService
				.findByStatusAndOrder("READY", order);
		int quantity = 0;
		for (RegistryDTO registry : removalRegistrys) {
			quantity += registry.getQuantity();
			manageRegistryFacadeService.deleteRegistry(registry);
		}
		// /
		order.setStatus("READY");
		manageOrderFacadeService.updateOrder(order);
		// /
		IncomeDTO income = new IncomeDTO();
		income.setOrderId(order.getId());
		income.setType("IN");
		income.setQuantity(quantity);
		income.setPrice(order.getCostOfAll());
		income.setName(order.getName());
		manageIncomeFacadeService.createIncome(income);
		// /
	}

	public void transport() {
		System.out.println(orderModel.getVisibleOrderList());
	}

	public ManageIncomeFacadeService getManageIncomeFacadeService() {
		return manageIncomeFacadeService;
	}

	public void setManageIncomeFacadeService(
			ManageIncomeFacadeService manageIncomeFacadeService) {
		this.manageIncomeFacadeService = manageIncomeFacadeService;
	}

	public LazyOrderModel getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(LazyOrderModel orderModel) {
		this.orderModel = orderModel;
	}

	public Set<OrderDTO> getExportList() {
		return exportList;
	}

	public void setExportList(Set<OrderDTO> exportList) {
		this.exportList = exportList;
	}

}
