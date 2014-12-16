package hu.unideb.webshop.category;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.service.ManageCategoryFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "categoryManagerController")
public class CategoryManagerController implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyCategoryModel categoryModel;
	private CategoryDTO selectedCategory;

	@ManagedProperty(value = "#{manageCategoryFacadeService}")
	private ManageCategoryFacadeService manageCategoryFacadeService;

	@PostConstruct
	public void init() {
		categoryModel = new LazyCategoryModel(manageCategoryFacadeService);
	}

	public LazyCategoryModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(LazyCategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public ManageCategoryFacadeService getManageCategoryFacadeService() {
		return manageCategoryFacadeService;
	}

	public void setManageCategoryFacadeService(
			ManageCategoryFacadeService manageCategoryFacadeService) {
		this.manageCategoryFacadeService = manageCategoryFacadeService;
	}

	public CategoryDTO getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryDTO selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public void onRowSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(
				"msgs",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", LocaleSwitcher
						.getMessage("warehouses_selected")
						+ selectedCategory.getName()));
	}
	
	public void test(){
		System.out.println(selectedCategory);
	}

}
