package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The Class Material.
 */
@Entity
@Table(name = "MATERIAL")
public class Material extends BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The material name. */
	private String materialName;
	
	/** The cost of the material. */
	private Double cost;

	/**
	 * Gets the material name.
	 *
	 * @return the material name
	 */
	public String getMaterialName() {
		return materialName;
	}

	/**
	 * Sets the material name.
	 *
	 * @param materialName the new material name
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
}
