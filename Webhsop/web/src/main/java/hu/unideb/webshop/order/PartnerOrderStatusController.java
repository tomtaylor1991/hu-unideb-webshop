package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManagePartnerFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ViewScoped
@ManagedBean(name = "partnerOrderStatusController")
public class PartnerOrderStatusController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;
	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;
	@ManagedProperty(value = "#{managePartnerFacadeService}")
	private ManagePartnerFacadeService managePartnerFacadeService;
	@ManagedProperty(value = "#{manageUserFacadeService}")
	ManageUserFacadeService manageUserFacadeService;

	private PartnerDTO selectedPartner;
	List<OrderDTO> orders;
	List<RegistryDTO> selectedOrderRegisry;
	OrderDTO selectedOrder;
	
	@PostConstruct
	public void init() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String userName = userDetails.getUsername();
			UserDTO selectedUser = manageUserFacadeService.getUser(userName);
			if (selectedUser != null) {
				selectedPartner = managePartnerFacadeService
						.findPartnerByUser(selectedUser);
				if (selectedPartner != null) {
					orders = manageOrderFacadeService
							.getOrdersByPartner(selectedPartner);
					Collections.sort(orders);
				} else {
					orders = new LinkedList<OrderDTO>();
				}
			}
		}
	}

	public void setOrderRegistry(OrderDTO selectedOrder) {
		selectedOrderRegisry = manageRegistryFacadeService
				.findByOrder(selectedOrder);
		this.selectedOrder = selectedOrder;
	}

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public ManagePartnerFacadeService getManagePartnerFacadeService() {
		return managePartnerFacadeService;
	}

	public void setManagePartnerFacadeService(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

	public PartnerDTO getSelectedPartner() {
		return selectedPartner;
	}

	public void setSelectedPartner(PartnerDTO selectedPartner) {
		this.selectedPartner = selectedPartner;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}

	public ManageUserFacadeService getManageUserFacadeService() {
		return manageUserFacadeService;
	}

	public void setManageUserFacadeService(
			ManageUserFacadeService manageUserFacadeService) {
		this.manageUserFacadeService = manageUserFacadeService;
	}

	public List<RegistryDTO> getSelectedOrderRegisry() {
		return selectedOrderRegisry;
	}

	public void setSelectedOrderRegisry(List<RegistryDTO> selectedOrderRegisry) {
		this.selectedOrderRegisry = selectedOrderRegisry;
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

}
