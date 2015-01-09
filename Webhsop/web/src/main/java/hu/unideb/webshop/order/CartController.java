package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
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
		cart.add(tmp);
		// //
		quantity = 1;
		System.out.println(cart);
	}

	public void removeElementFromCart(RegistryDTO product) {
		cart.remove(product);
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
