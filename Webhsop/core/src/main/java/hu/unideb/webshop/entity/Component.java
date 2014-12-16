package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The Class Component.
 */
@Entity
@Table(name = "COMPONENT")
public class Component extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The quantity. */
	private int quantity;
	
	/** The comment. */
	@Lob
	private String comment;
	
	/** The recipe id. */
	private Long recipeId;

	/** The material id. */
	private Long materialId;		

	/**
	 * Instantiates a new component.
	 */
	public Component() {
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
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the recipe id.
	 *
	 * @return the recipe id
	 */
	public Long getRecipeId() {
		return recipeId;
	}

	/**
	 * Sets the recipe id.
	 *
	 * @param recipeId the new recipe id
	 */
	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * Gets the material id.
	 *
	 * @return the material id
	 */
	public Long getMaterialId() {
		return materialId;
	}

	/**
	 * Sets the material id.
	 *
	 * @param materialId the new material id
	 */
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	

}
