package hu.unideb.webshop.product;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageImageFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ViewScoped
@ManagedBean(name = "productController")
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;

	private ProductDTO selectedProduct;
	private LazyProductModel productModel;
	private ProductDTO newProduct;
	private CategoryDTO selectedCategory;
	private List<CategoryDTO> completeTextResults;
	private UploadedFile uploadedFile;
	private String uploadedFileName;

	@PostConstruct
	public void init() {
		productModel = new LazyProductModel(manageProductFacadeService,
				manageImageFacadeService);
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}

	public LazyProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(LazyProductModel productModel) {
		this.productModel = productModel;
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public ProductDTO getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(ProductDTO newProduct) {
		this.newProduct = newProduct;
	}

	public void generateNewProduct() {
		newProduct = new ProductDTO();
	}

	public void createProduct() {
		if (newProduct != null) {
			newProduct.setCategory(selectedCategory);
			// System.out.println("SELECTED CATEGORY: " + selectedCategory);
			manageProductFacadeService.saveProduct(newProduct);
			newProduct = null;
			selectedCategory = null;
		}
	}

	public CategoryDTO getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryDTO selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<CategoryDTO> getCompleteTextResults() {
		return completeTextResults;
	}

	public void setCompleteTextResults(List<CategoryDTO> completeTextResults) {
		this.completeTextResults = completeTextResults;
	}

	public List<CategoryDTO> completeText(String query) {
		completeTextResults = manageProductFacadeService
				.searchCategoryByName(query);
		return completeTextResults;
	}

	public void handleFileUpload(FileUploadEvent event) {
		// get uploaded file from the event
		this.uploadedFile = (UploadedFile) event.getFile();
		this.uploadedFileName = event.getFile().getFileName();
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

	public ManageImageFacadeService getManageImageFacadeService() {
		return manageImageFacadeService;
	}

	public void setManageImageFacadeService(
			ManageImageFacadeService manageImageFacadeService) {
		this.manageImageFacadeService = manageImageFacadeService;
	}

	public void deleteProductImage(ImageInfoDTO img) {
		if(img!=null){
			manageImageFacadeService.removeImage(img);
		}
	}

	public void saveImageToProduct(ProductDTO tmpProduct) {
		if (tmpProduct != null) {
			ImageInfoDTO img = manageImageFacadeService.saveImage(
					uploadedFile.getContents(), uploadedFileName);
			img.setProductId(tmpProduct.getId());
			manageImageFacadeService.updateImage(img);
			System.out.println("Success image upload for product!");
		}
	}
}
