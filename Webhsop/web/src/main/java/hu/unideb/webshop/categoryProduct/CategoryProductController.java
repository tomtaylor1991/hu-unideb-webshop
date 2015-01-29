package hu.unideb.webshop.categoryProduct;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageCategoryFacadeService;
import hu.unideb.webshop.service.ManageImageFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;

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
	// //
	private CategoryDTO parentCategory = null;
	private List<CategoryDTO> beforeCategorys;
	// //
	private List<CategoryDTO> visibleCategory;
	// //
	private LazyCategoryProductModel productModel;
	// //
	private ProductDTO selectedProduct;
	private ImageInfoDTO selectedImage;

	@ManagedProperty(value = "#{manageCategoryFacadeService}")
	private ManageCategoryFacadeService manageCategoryFacadeService;
	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;

	@PostConstruct
	public void init() {
		beforeCategorys = new LinkedList<CategoryDTO>();
		productModel = new LazyCategoryProductModel(manageProductFacadeService,
				manageImageFacadeService);
		renderVisibleList(null, false);
	}

	public void stepImage(boolean isNext) {
		if (selectedProduct != null) {
			int max = selectedProduct.getImages().size();
			if (max > 0) {
				// System.out.println("current img id: " + selectedImage);
				for (int i = 0; i < max; i++) {
					if (selectedProduct.getImages().get(i).getId() == selectedImage
							.getId()) {
						int pos = i;
						if (isNext) {
							if (pos + 1 < max) {
								pos++;
							} else {
								pos = 0;
							}
						} else {
							if (pos - 1 >= 0) {
								pos--;
							} else {
								pos = max - 1;
							}
						}
						selectedImage = selectedProduct.getImages().get(pos);
						// System.out.println("pos: " + pos);
						break;
					}
				}
			}
		}
	}

	public void renderVisibleList(CategoryDTO parentCategory, boolean isBack) {
		if (parentCategory != null && !isBack) {
			beforeCategorys.add(this.getParentCategory());
		}
		this.setParentCategory(parentCategory);
		this.visibleCategory = manageCategoryFacadeService
				.searchCategoryByParent(parentCategory);
	}

	public void renderProductListOfCategory(CategoryDTO category) {
		productModel.setCurrentCategory(category);
	}

	public void backCategory() {
		if (beforeCategorys.size() > 0) {
			CategoryDTO parent = beforeCategorys
					.get(beforeCategorys.size() - 1);
			beforeCategorys.remove(beforeCategorys.size() - 1);
			renderVisibleList(parent, true);
		}
	}

	public void initImageView(ImageInfoDTO selectedImage) {
		setSelectedImage(selectedImage);
	}

	public void showProduct(ProductDTO product) {
		selectedProduct = product;
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

	public List<CategoryDTO> getBeforeCategorys() {
		return beforeCategorys;
	}

	public void setBeforeCategorys(List<CategoryDTO> beforeCategorys) {
		this.beforeCategorys = beforeCategorys;
	}

	public LazyCategoryProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(LazyCategoryProductModel productModel) {
		this.productModel = productModel;
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}

	public void test(ProductDTO product) {
		System.out.println(product);
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public ImageInfoDTO getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(ImageInfoDTO selectedImage) {
		this.selectedImage = selectedImage;
	}

	public void closeProduct() {
		selectedProduct = null;
	}

	public void closeProductList() {
		productModel.setCurrentCategory(null);
		selectedProduct = null;
	}
}
