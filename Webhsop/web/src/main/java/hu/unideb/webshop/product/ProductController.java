package hu.unideb.webshop.product;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "productController")
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	private ProductDTO selectedProduct;
	private LazyProductModel productModel;
	private ProductDTO newProduct;

	@PostConstruct
	public void init() {
		productModel = new LazyProductModel(manageProductFacadeService);
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
			manageProductFacadeService.saveProduct(newProduct);
			newProduct = null;
		}
	}
}
