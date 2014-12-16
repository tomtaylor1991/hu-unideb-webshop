package hu.unideb.webshop.dto;

import java.io.Serializable;

public class BeerDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String type;
	private String color;
	private int price;
	private float alcoholContent;
	private RecipeDTO recipeDTO;
	private boolean isPremium;

	public BeerDTO() {

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(float alk) {
		this.alcoholContent = alk;
	}

	public RecipeDTO getRecipeDTO() {
		return recipeDTO;
	}

	public void setRecipeDTO(RecipeDTO recipeDTO) {
		this.recipeDTO = recipeDTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(alcoholContent);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BeerDTO other = (BeerDTO) obj;
		if (Float.floatToIntBits(alcoholContent) != Float
				.floatToIntBits(other.alcoholContent)) {
			return false;
		}
		if (color == null) {
			if (other.color != null) {
				return false;
			}
		} else if (!color.equals(other.color)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "BeerDTO [id=" + id + ", name=" + name + ", type=" + type
				+ ", color=" + color + ", price=" + price + ", alcoholContent="
				+ alcoholContent + ", recipeDTO=" + recipeDTO + "]";

	}

	public boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

}
