package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Long imageInfoId;
	
	private int priority;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
	private Category parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Long getImageInfoId() {
		return imageInfoId;
	}

	public void setImageInfoId(Long imageInfoId) {
		this.imageInfoId = imageInfoId;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", imageInfoId=" + imageInfoId
				+ ", priority=" + priority + ", parent=" + parent + "]";
	}

}
