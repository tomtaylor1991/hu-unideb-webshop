package hu.unideb.webshop.dto;

import java.io.Serializable;

public class CategoryDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

	private String image;

	private int priority;

	private CategoryDTO parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public CategoryDTO getParent() {
		return parent;
	}

	public void setParent(CategoryDTO parent) {
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", image=" + image
				+ ", priority=" + priority + ", parent=" + parent + "]";
	}
	
	
}
