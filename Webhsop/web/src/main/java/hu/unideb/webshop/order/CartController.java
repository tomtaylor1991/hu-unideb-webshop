package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "cartController")
public class CartController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<RegistryDTO> cart;
	private int quantity;
	int cost = 0;

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
		// //
		double tmpCost = 0;
		if (product.getIsSpecial()) {
			tmpCost = quantity * product.getSpecialPrice();
		} else {
			tmpCost = quantity * product.getPrice();
		}
		tmp.setCost((int) tmpCost);
		// //
		cart.add(tmp);
		// //
		cost += tmp.getCost();
		System.out.println(cost);
		quantity = 1;
	}

	public void removeAll() {
		cart = new LinkedHashSet<RegistryDTO>();
		cost = 0;
	}

	public void removeElementFromCart(RegistryDTO product) {
		cart.remove(product);
		cost -= product.getCost();
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
