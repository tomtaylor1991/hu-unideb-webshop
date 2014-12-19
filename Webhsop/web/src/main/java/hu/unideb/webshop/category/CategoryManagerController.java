package hu.unideb.webshop.category;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.service.ManageCategoryFacadeService;
import hu.unideb.webshop.service.ManageImageFacadeService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ViewScoped
@ManagedBean(name = "categoryManagerController")
@FacesConverter("categoryConverter")
public class CategoryManagerController implements Serializable, Converter {

	private static final long serialVersionUID = 1L;

	public final String RESOURCES_SRC = "/home/tomtaylor/workspaces/szakdolgozat/webshop/Application/hu-unideb-webshop/uploads";

	private LazyCategoryModel categoryModel;
	private CategoryDTO selectedCategory;
	private CategoryDTO selectedParentCategory;
	private CategoryDTO newCategory;
	private UploadedFile uploadedFile;
	private String uploadedFileName;
	private List<CategoryDTO> completeTextResults;

	@ManagedProperty(value = "#{manageCategoryFacadeService}")
	private ManageCategoryFacadeService manageCategoryFacadeService;

	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;

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

	public CategoryDTO getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(CategoryDTO newCategory) {
		this.newCategory = newCategory;
	}

	public void generateNewTopCategory() {
		System.out.println("START generateNewTopCategory");
		this.newCategory = new CategoryDTO();
	}

	public void generateNewCategoryWithParent() {
		if (selectedCategory != null) {
			System.out.println(selectedCategory);
			newCategory = new CategoryDTO();
			newCategory.setParent(selectedCategory);
		}
	}

	public void saveNewCategory() {
		if (newCategory != null) {
			// kep feltoltese
			if (uploadedFile != null) {
				System.out.println("start saving " + uploadedFileName);
				ImageInfoDTO img = manageImageFacadeService.saveImage(
						uploadedFile.getContents(), uploadedFileName);
				// System.out.println("New Image: " + img);
				newCategory.setImageInfoId(img.getId());
			}
			// ////
			manageCategoryFacadeService.createCategory(newCategory);
			// ////
			newCategory = null;
			this.uploadedFile = null;
		}
	}

	public void updateCategory() {
		if (selectedCategory != null) {
			// kep feltoltese
			if (uploadedFile != null) {
				System.out.println("start saving " + uploadedFileName);
				ImageInfoDTO img = manageImageFacadeService.saveImage(
						uploadedFile.getContents(), uploadedFileName);
				// System.out.println("New Image: " + img);
				selectedCategory.setImageInfoId(img.getId());
			}
			// /////
			if (selectedCategory.getId() != selectedParentCategory.getId()) {
				selectedCategory.setParent(selectedParentCategory);
			}
			// ////
			manageCategoryFacadeService.updateCategory(selectedCategory);
			// ////
			selectedCategory = null;
			this.uploadedFile = null;
		}
	}

	public void handleFileUpload(FileUploadEvent event) {

		// get uploaded file from the event
		this.uploadedFile = (UploadedFile) event.getFile();
		this.uploadedFileName = event.getFile().getFileName();
	}

	public List<CategoryDTO> completeText(String query) {
		completeTextResults = manageCategoryFacadeService
				.searchCategoryByName(query);
		return completeTextResults;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public String getRESOURCES_SRC() {
		return RESOURCES_SRC;
	}

	public ManageImageFacadeService getManageImageFacadeService() {
		return manageImageFacadeService;
	}

	public void setManageImageFacadeService(
			ManageImageFacadeService manageImageFacadeService) {
		this.manageImageFacadeService = manageImageFacadeService;
	}

	public CategoryDTO getSelectedParentCategory() {
		return selectedParentCategory;
	}

	public void setSelectedParentCategory(CategoryDTO selectedParentCategory) {
		this.selectedParentCategory = selectedParentCategory;
	}

	public void initUpdateCategory() {
		if (selectedCategory != null) {
			selectedParentCategory = selectedCategory.getParent();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CategoryDTO) object).getId());
		} else {
			return null;
		}
	}

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		System.out.println("start " + value);
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		CategoryManagerController viewScopedBean = (CategoryManagerController) viewMap
				.get("categoryManagerController");
		List<CategoryDTO> list = viewScopedBean.getCompleteTextResults();
		if (value != null && value.trim().length() > 0) {
			try {
				Long idValue = Long.valueOf(value);
				System.out.println("check start " + idValue);
				for (CategoryDTO c : list) {
					// System.out.println("check start " + c);
					if (c.getId().equals(idValue)) {
						// System.out.println(c);
						return c;
					}
				}
				return null;
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public List<CategoryDTO> getCompleteTextResults() {
		return completeTextResults;
	}

	public void setCompleteTextResults(List<CategoryDTO> completeTextResults) {
		this.completeTextResults = completeTextResults;
	}

}
