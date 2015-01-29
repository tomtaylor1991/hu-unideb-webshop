package hu.unideb.webshop.importer;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.product.LazyProductModel;
import hu.unideb.webshop.service.ManageIncomeFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "importerController")
public class ImporterController implements Serializable {

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;
	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;
	@ManagedProperty(value = "#{manageIncomeFacadeService}")
	private ManageIncomeFacadeService manageIncomeFacadeService;

	private static final long serialVersionUID = 1L;

	private ProductDTO selectedProduct;
	private int quantity;
	private Set<RegistryDTO> registryList;
	private RegistryDTO deletedElement;
	private WarehouseDTO selectedWH;
	private LazyProductModel productModel;

	@PostConstruct
	public void initRegistryController() {
		registryList = new LinkedHashSet<RegistryDTO>();
		productModel = new LazyProductModel(manageProductFacadeService);
	}

	public void addRegistryToList() {
		System.out.println(selectedProduct);
		if (selectedProduct != null) {
			RegistryDTO registy = new RegistryDTO();
			registy.setQuantity(quantity);
			registy.setProduct(selectedProduct);
			registy.setStatus("FREE");
			registryList.add(registy);
		}
	}

	public void saveToDB() {
		if (selectedWH != null) {
			for (RegistryDTO registry : registryList) {
				registry.setWarehouse(selectedWH);
			}
			manageRegistryFacadeService
					.saveRegistrys(new LinkedList<RegistryDTO>(registryList));
			// /
			for (RegistryDTO registry : registryList) {
				// /
				IncomeDTO income = new IncomeDTO();
				income.setType("OUT");
				income.setPrice((int) registry.getProduct().getPurchasePrice()
						* registry.getQuantity());
				manageIncomeFacadeService.createIncome(income);
				// /
			}
			// /
			registryList = new LinkedHashSet<RegistryDTO>();
		}
	}

	public void removeRegistryFromList(RegistryDTO registry) {
		registryList.remove(registry);
		deletedElement = null;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<RegistryDTO> getRegistryList() {
		return registryList;
	}

	public void setRegistryList(Set<RegistryDTO> registryList) {
		this.registryList = registryList;
	}

	public RegistryDTO getDeletedElement() {
		return deletedElement;
	}

	public void setDeletedElement(RegistryDTO deletedElement) {
		this.deletedElement = deletedElement;
	}

	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	public WarehouseDTO getSelectedWH() {
		return selectedWH;
	}

	public void setSelectedWH(WarehouseDTO selectedWH) {
		this.selectedWH = selectedWH;
	}

	public ManageIncomeFacadeService getManageIncomeFacadeService() {
		return manageIncomeFacadeService;
	}

	public void setManageIncomeFacadeService(
			ManageIncomeFacadeService manageIncomeFacadeService) {
		this.manageIncomeFacadeService = manageIncomeFacadeService;
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
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

}
