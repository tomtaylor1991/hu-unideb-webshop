package hu.unideb.webshop.dto;

import java.io.Serializable;

public class RecipeDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;		
	private String name;
	private Double cost;

	private String comment;

	public RecipeDTO() {

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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}


	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RecipeDTO [id=" + id + ", recipeName=" + name + ", cost="
				+ cost + "]";
	}

}
