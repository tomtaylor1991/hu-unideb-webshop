package hu.unideb.webshop.dto;

import java.io.Serializable;

public class CategoryDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	private int childNumber;
	private int productNumber;
	
	private Long imageInfoId;

	private ImageInfoDTO image; 
	
	private int priority;

	private CategoryDTO parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getImageInfoId() {
		return imageInfoId;
	}

	public void setImageInfoId(Long imageInfoId) {
		this.imageInfoId = imageInfoId;
	}

	public ImageInfoDTO getImage() {
		return image;
	}

	public void setImage(ImageInfoDTO image) {
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
		return "CategoryDTO [id=" + id + ", name=" + name + ", imageInfoId="
				+ imageInfoId + ", image=" + image + ", priority=" + priority
				+ ", parent=" + parent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	public int getChildNumber() {
		return childNumber;
	}

	public void setChildNumber(int childNumber) {
		this.childNumber = childNumber;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}



	
	
}
