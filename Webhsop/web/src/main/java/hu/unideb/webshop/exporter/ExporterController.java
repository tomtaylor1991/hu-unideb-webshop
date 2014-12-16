package hu.unideb.webshop.exporter;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageIncomeFacadeService;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "exporterController")
public class ExporterController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<OrderDTO> visibleOrders;
	private OrderDTO selectedOrder;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	@ManagedProperty(value = "#{manageIncomeFacadeService}")
	private ManageIncomeFacadeService manageIncomeFacadeService;

	public List<OrderDTO> getVisibleOrders() {
		return visibleOrders;
	}

	public void setVisibleOrders(List<OrderDTO> visibleOrders) {
		this.visibleOrders = visibleOrders;
	}

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
		visibleOrders = manageOrderFacadeService.getOrdersByStatus("TRANSPORT");
	}

	public void transport() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", String
						.format("%s %s",
								LocaleSwitcher.getMessage("exporter_done"),
								selectedOrder.getName())));
		selectedOrder.setStatus("DONE");
		visibleOrders.remove(selectedOrder);
		manageOrderFacadeService.updateOrder(selectedOrder);

		// az orderhez tartozó registryk törlése, mivel már nincs rájuk
		// szükség

		for (RegistryDTO r : manageRegistryFacadeService.findByStatusAndOrder(
				"READY", selectedOrder)) {
			// if (r.getMaterial() != null) {
			int sum = 0;
			if (r.getBeer() != null) {
				sum += r.getBeer().getPrice() * r.getQuantity();
				/***/
				IncomeDTO income = new IncomeDTO();
				income.setPrice(sum);
				income.setOrderId(new Long(1));
				income.setComment(r.getBeer().getName() + "," + r.getQuantity());
				manageIncomeFacadeService.createIncome(income);
				/***/
			}
			manageRegistryFacadeService.deleteRegistry(r);
			// }
		}

	}

	public ManageIncomeFacadeService getManageIncomeFacadeService() {
		return manageIncomeFacadeService;
	}

	public void setManageIncomeFacadeService(
			ManageIncomeFacadeService manageIncomeFacadeService) {
		this.manageIncomeFacadeService = manageIncomeFacadeService;
	}

}
