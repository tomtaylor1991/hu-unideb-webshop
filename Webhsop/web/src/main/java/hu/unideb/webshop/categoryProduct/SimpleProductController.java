package hu.unideb.webshop.categoryProduct;

import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageImageFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.io.Serializable;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "simpleProductController")
public class SimpleProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;

	private ProductDTO selectedProduct;
	private ImageInfoDTO selectedImage;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		String objectId = context.getExternalContext().getRequestParameterMap()
				.get("id");
		try {
			Long selectedProductId = new Long(objectId);
			selectedProduct = manageProductFacadeService
					.getProduct(selectedProductId);
			try {
				selectedProduct.setImages(manageImageFacadeService
						.getProductImages(selectedProduct));
			} catch (NullPointerException e) {
				selectedProduct.setImages(new LinkedList<ImageInfoDTO>());
			}
			// System.out.println(selectedProduct.getImages());
		} catch (NumberFormatException e) {

		}
		catch (NullPointerException e){
			
		}
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

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
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
						break;
					}
				}
			}
		}
	}

	public void initImageView(ImageInfoDTO selectedImage) {
		setSelectedImage(selectedImage);
	}

	public ImageInfoDTO getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(ImageInfoDTO selectedImage) {
		this.selectedImage = selectedImage;
	}

}
