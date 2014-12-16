package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The Class of Recipe.
 */
@Entity
@Table(name = "RECIPE")
public class Recipe extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The recipe name. */
	private String recipeName;
	
	/** The cost. */
	private Double cost;
	
	/** The beer id. */
	private Long beerId;	

	/** The comment. */
	@Lob
	private String comment;
	
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
	 * Gets the recipe name.
	 *
	 * @return the recipe name
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Sets the recipe name.
	 *
	 * @param recipeName the new recipe name
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
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

	/**
	 * Gets the beer id.
	 *
	 * @return the beer id
	 */
	public Long getBeerId() {
		return beerId;
	}

	/**
	 * Sets the beer id.
	 *
	 * @param beerId the new beer id
	 */
	public void setBeerId(Long beerId) {
		this.beerId = beerId;
	}

	
}
