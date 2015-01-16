package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManagePartnerFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "cartController")
public class CartController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;
	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;
	@ManagedProperty(value = "#{manageUserFacadeService}")
	private ManageUserFacadeService manageUserFacadeService;
	@ManagedProperty(value = "#{managePartnerFacadeService}")
	private ManagePartnerFacadeService managePartnerFacadeService;

	private Set<RegistryDTO> cart;
	private int quantity;

	@PostConstruct
	public void init() {
		cart = new LinkedHashSet<RegistryDTO>();
		quantity = 1;
	}

	public void addElementToCart(ProductDTO product) {
		RegistryDTO tmp = new RegistryDTO();
		tmp.setProduct(product);
		tmp.setStatus("ORDERDATA");
		if (cart.contains(tmp)) {
			cart.remove(tmp);
		}
		tmp.setQuantity(quantity);
		tmp.setOriginalQuantity(quantity);
		cart.add(tmp);
		// //
		quantity = 1;
	}

	public void removeAll() {
		cart = new LinkedHashSet<RegistryDTO>();
	}

	public void removeElementFromCart(RegistryDTO product) {
		cart.remove(product);
	}

	public void saveOrder(String userName) {
		System.out.println("ORDER: " + userName);
		if(cart.size()==0){
			return;
		}
		UserDTO user = manageUserFacadeService.getUser(userName);		
		if (user != null) {
			PartnerDTO selectedPartner = managePartnerFacadeService
					.findPartnerByUser(user);
			if (selectedPartner != null) {
				OrderDTO newOrder;
				newOrder = new OrderDTO();

				newOrder.setName(selectedPartner.getName());
				newOrder.setDate(new Date());
				newOrder.setStatus("NEW");
				newOrder.setPartnerDTO(selectedPartner);

				manageOrderFacadeService.createOrder(newOrder);
				for (RegistryDTO registry : cart) {
					registry.setOrder(newOrder);
				}

				manageRegistryFacadeService
						.saveRegistrys(new ArrayList<>(cart));
				System.out.println("Cart save done");
				removeAll();
			}
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

	public Set<RegistryDTO> getCart() {
		return cart;
	}

	public void setCart(Set<RegistryDTO> cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
