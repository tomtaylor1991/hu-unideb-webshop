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
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ViewScoped
@ManagedBean(name = "cartOrderController")
public class CartOrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;
	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;
	@ManagedProperty(value = "#{manageUserFacadeService}")
	private ManageUserFacadeService manageUserFacadeService;
	@ManagedProperty(value = "#{managePartnerFacadeService}")
	private ManagePartnerFacadeService managePartnerFacadeService;

	private CartController cartController;
	private PartnerDTO selectedPartner;

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		cartController = (CartController) sessionMap.get("cartController");
		// //
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String userName = userDetails.getUsername();
			UserDTO user = manageUserFacadeService.getUser(userName);
			if (user != null) {
				selectedPartner = managePartnerFacadeService
						.findPartnerByUser(user);
			}
		}
	}

	public void saveOrder(String userName) {
		if (cartController.getCart().size() == 0) {
			return;
		}

		if (selectedPartner != null) {
			OrderDTO newOrder;
			newOrder = new OrderDTO();

			newOrder.setName(selectedPartner.getName());
			newOrder.setDate(new Date());
			newOrder.setStatus("NEW");
			newOrder.setPartnerDTO(selectedPartner);

			manageOrderFacadeService.createOrder(newOrder);
			for (RegistryDTO registry : cartController.getCart()) {
				registry.setOrder(newOrder);
			}

			manageRegistryFacadeService.saveRegistrys(new ArrayList<>(
					cartController.getCart()));
			System.out.println("Cart save done");
			cartController.removeAll();
		}

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

	public ManageUserFacadeService getManageUserFacadeService() {
		return manageUserFacadeService;
	}

	public void setManageUserFacadeService(
			ManageUserFacadeService manageUserFacadeService) {
		this.manageUserFacadeService = manageUserFacadeService;
	}

	public ManagePartnerFacadeService getManagePartnerFacadeService() {
		return managePartnerFacadeService;
	}

	public void setManagePartnerFacadeService(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

	public PartnerDTO getSelectedPartner() {
		return selectedPartner;
	}

	public void setSelectedPartner(PartnerDTO selectedPartner) {
		this.selectedPartner = selectedPartner;
	}

}
