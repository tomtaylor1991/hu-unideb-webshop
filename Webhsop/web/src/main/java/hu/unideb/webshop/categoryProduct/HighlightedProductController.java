package hu.unideb.webshop.categoryProduct;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageImageFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "highlightedProductController")
public class HighlightedProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;

	List<ProductDTO> productList;

	@PostConstruct
	public void init() {
		productList = manageProductFacadeService.getHighlightedProducts();
		for (ProductDTO product : productList) {
			product.setImages(manageImageFacadeService
					.getProductImages(product));
		}
		//System.out.println(productList);
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}

	public ManageImageFacadeService getManageImageFacadeService() {
		return manageImageFacadeService;
	}

	public void setManageImageFacadeService(
			ManageImageFacadeService manageImageFacadeService) {
		this.manageImageFacadeService = manageImageFacadeService;
	}

	public List<ProductDTO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDTO> productList) {
		this.productList = productList;
	}

}
