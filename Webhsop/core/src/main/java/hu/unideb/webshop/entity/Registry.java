package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class of the Registry.
 */
@Entity
@Table(name = "REGISTRY")
public class Registry extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The quantity. */
	private int quantity;
	
	/** The original quantity. */
	private int originalQuantity;
	
	/** The status. */
	private String status;
	
	/** The beer. */
	@ManyToOne
	@JoinColumn(name = "beer_id")
	private Beer beer;	
	
	/** The warehouse. */
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;
	
	/** The material. */
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;

	/** The order. */
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
	
	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the beer.
	 *
	 * @return the beer
	 */
	public Beer getBeer() {
		return beer;
	}

	/**
	 * Sets the beer.
	 *
	 * @param beer the new beer
	 */
	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	/**
	 * Gets the warehouse.
	 *
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * Sets the warehouse.
	 *
	 * @param warehouse the new warehouse
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * Gets the material.
	 *
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the material.
	 *
	 * @param material the new material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Gets the original quantity.
	 *
	 * @return the original quantity
	 */
	public int getOriginalQuantity() {
		return originalQuantity;
	}

	/**
	 * Sets the original quantity.
	 *
	 * @param originalQuantity the new original quantity
	 */
	public void setOriginalQuantity(int originalQuantity) {
		this.originalQuantity = originalQuantity;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Registry [quantity=" + quantity + ", status=" + status
				+ ", beer=" + beer + ", warehouse=" + warehouse + ", material="
				+ material + ", order=" + order + "]";
	}
	
	
}
