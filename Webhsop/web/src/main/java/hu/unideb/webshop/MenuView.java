package hu.unideb.webshop;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.service.ManageProductFacadeService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * A class which can switch between sites using the redirectToUrl function.
 */
@SessionScoped
@ManagedBean(name = "menuView")
public class MenuView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	private List<ProductDTO> completeTextResults;
	private ProductDTO selectedProduct;

	public void showRecipes() {
		redirectToUrl("/pages/secure/recipes.xhtml");
	}

	public void showMaterials() {
		redirectToUrl("/pages/secure/materials.xhtml");
	}

	public void showWarehouses() {
		redirectToUrl("/pages/secure/warehouses.xhtml");
	}

	public void showOrders() {
		redirectToUrl("/pages/secure/orders.xhtml");
	}

	public void showPartners() {
		redirectToUrl("/pages/secure/partners.xhtml");
	}

	public void showImporter() {
		redirectToUrl("/pages/secure/importer.xhtml");
	}

	public void showWorker() {
		redirectToUrl("/pages/secure/worker.xhtml");
	}

	public void showExporter() {
		redirectToUrl("/pages/secure/exporter.xhtml");
	}

	public void redirectToUrl(String url) {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance()
					.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<ProductDTO> completeText(String query) {
		completeTextResults = manageProductFacadeService
				.searchProductByName(query);
		return completeTextResults;
	}

	public void redirectToCompleteText() {
		if (selectedProduct != null) {
			String url = "/pages/unsecure/product-view.xhtml?id="
					+ selectedProduct.getId();
			redirectToUrl(url);			
		}
	}

	public static void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}

	public List<ProductDTO> getCompleteTextResults() {
		return completeTextResults;
	}

	public void setCompleteTextResults(List<ProductDTO> completeTextResults) {
		this.completeTextResults = completeTextResults;
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

}
