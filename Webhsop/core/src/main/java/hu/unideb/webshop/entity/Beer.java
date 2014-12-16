package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of Beer.
 */
@Entity
@Table(name = "BEER")
public class Beer extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The price. */
	private int price;
	
	/** The color. */
	private String color;
	
	/** The type. */
	private String type;
	
	/** The name. */
	private String name;
	
	/** The alcohol content. */
	private float alcoholContent;
	
	/** The recipe id. */
	private Long recipeId;
	
	/** The is premium. */
	private Boolean isPremium;

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the alcohol content.
	 *
	 * @return the alcohol content
	 */
	public float getAlcoholContent() {
		return alcoholContent;
	}

	/**
	 * Sets the alcohol content.
	 *
	 * @param alcoholContent the new alcohol content
	 */
	public void setAlcoholContent(float alcoholContent) {
		if (alcoholContent < 0) {
			this.alcoholContent = 0;
		} else if (100 < alcoholContent) {
			this.alcoholContent = 100;
		} else {
			this.alcoholContent = alcoholContent;
		}
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
	 * Gets the checks if is premium.
	 *
	 * @return the checks if is premium
	 */
	public Boolean getIsPremium() {
		return isPremium;
	}

	/**
	 * Sets the checks if is premium.
	 *
	 * @param isPremium the new checks if is premium
	 */
	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

}
