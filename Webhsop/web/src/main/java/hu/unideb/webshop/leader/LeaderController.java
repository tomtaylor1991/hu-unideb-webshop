package hu.unideb.webshop.leader;

import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "leaderController")
public class LeaderController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	private OrderDTO selectedOrder;

	private LeaderTestInfoDTO info = new LeaderTestInfoDTO();

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

	public OrderDTO getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(OrderDTO selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public LeaderTestInfoDTO getInfo() {
		return info;
	}

	public void setInfo(LeaderTestInfoDTO info) {
		this.info = info;
	}

	public void needProduct() {
		/*
		 * if (selectedOrder != null) { for (Need need : info.getNeed()) {
		 * manageRegistryFacadeService.createProductNeedForOrder(
		 * need.getProduct(), selectedOrder, need.getNeed()); } }
		 */
		selectedOrder.setStatus("NEEDPRODUCT");
		manageOrderFacadeService.updateOrder(selectedOrder);
	}

	public void keepProduct(Need need, int quantity) {
		if (selectedOrder != null) {
			int readyQuantity = manageRegistryFacadeService
					.keepProductForOrder(need.getProduct(), selectedOrder,
							quantity);
			RegistryDTO registry = need.getRegistry();
			int q = registry.getQuantity() - readyQuantity;
			// System.out.println("ready quantity: " + readyQuantity);
			registry.setQuantity(q < 0 ? 0 : q);
			// /
			need.setNeed(need.getNeed() - readyQuantity);
			need.setInWHQuantity(need.getInWHQuantity() - readyQuantity);
			need.setReadyQuantity(need.getReadyQuantity() + readyQuantity);
			//
			manageRegistryFacadeService.updateRegistry(registry);
		}
	}

	public void initSelectedOrder() {
		if (selectedOrder != null) {
			info = selectedOrder.getInfo();
			// System.out.println(info);
		}
	}

}
