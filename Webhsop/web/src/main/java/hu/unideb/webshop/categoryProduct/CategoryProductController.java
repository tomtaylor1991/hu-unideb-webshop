package hu.unideb.webshop.categoryProduct;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.service.ManageCategoryFacadeService;
import hu.unideb.webshop.service.ManageImageFacadeService;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "categoryProductController")
public class CategoryProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	private CategoryDTO selectedCategory;
	private CategoryDTO parentCategory;
	// //
	private List<CategoryDTO> visibleCategory;
	private List<CategoryDTO> allCategory;

	@ManagedProperty(value = "#{manageCategoryFacadeService}")
	private ManageCategoryFacadeService manageCategoryFacadeService;

	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;

	@PostConstruct
	public void init() {
		// allCategory = manageCategoryFacadeService.getAllCategory();
		renderVisibleList(null);
	}

	public void renderVisibleList(CategoryDTO parentCategory) {
		this.setParentCategory(parentCategory);
		this.visibleCategory = manageCategoryFacadeService
				.searchCategoryByParent(parentCategory);
	}

	public CategoryDTO getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryDTO selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public ManageCategoryFacadeService getManageCategoryFacadeService() {
		return manageCategoryFacadeService;
	}

	public void setManageCategoryFacadeService(
			ManageCategoryFacadeService manageCategoryFacadeService) {
		this.manageCategoryFacadeService = manageCategoryFacadeService;
	}

	public ManageImageFacadeService getManageImageFacadeService() {
		return manageImageFacadeService;
	}

	public void setManageImageFacadeService(
			ManageImageFacadeService manageImageFacadeService) {
		this.manageImageFacadeService = manageImageFacadeService;
	}

	public List<CategoryDTO> getVisibleCategory() {
		return visibleCategory;
	}

	public void setVisibleCategory(List<CategoryDTO> visibleCategory) {
		this.visibleCategory = visibleCategory;
	}

	public CategoryDTO getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(CategoryDTO parentCategory) {
		this.parentCategory = parentCategory;
	}

}
