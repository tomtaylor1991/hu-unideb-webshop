package hu.unideb.webshop.importer;

import hu.unideb.webshop.dto.LeaderTestInfoDTO.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "importerNecessaryProducts")
public class ImporterNecessaryProducts implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;
	
	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;

	private List<Need> needListOfProducts;
	private Set<Need> importList;
	private Need newNeed = new Need();
	private ProductDTO selectedProduct;
	private List<ProductDTO> completeTextResults;

	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	@PostConstruct
	public void init() {
		initNeedList();
	}

	public void initNeedList() {
		List<OrderDTO> listOfNeededOrders = manageOrderFacadeService
				.getOrdersByStatus("NEEDPRODUCT");
		List<RegistryDTO> needs = new LinkedList<RegistryDTO>();
		for (OrderDTO currentOrder : listOfNeededOrders) {
			needs.addAll(manageRegistryFacadeService.findByStatusAndOrder(
					"NEED", currentOrder));
		}
		needListOfProducts = new LinkedList<Need>();
		importList = new HashSet<Need>();
		Map<ProductDTO, Integer> data = new HashMap<ProductDTO, Integer>();
		for (RegistryDTO currentRegistry : needs) {
			if (data.keySet().contains(currentRegistry.getProduct())) {
				data.put(currentRegistry.getProduct(),
						data.get(currentRegistry.getProduct())
								+ currentRegistry.getQuantity());
			} else {
				data.put(currentRegistry.getProduct(),
						currentRegistry.getQuantity());
			}
		}
		for (ProductDTO currentProduct : data.keySet()) {
			Need need = new Need();
			need.setProduct(currentProduct);
			need.setNeed(data.get(currentProduct));
			needListOfProducts.add(need);
		}
		newNeed = new Need();
	}

	public List<Need> getNeedListOfProducts() {
		return needListOfProducts;
	}

	public void addToImportList(Need need) {
		Need n = new Need();
		n.setNeed(need.getNeed());
		n.setProduct(need.getProduct());
		importList.add(n);
	}

	public void removeFromImportList(Need need) {
		importList.remove(need);
	}

	public void setNeedListOfProducts(List<Need> needListOfProducts) {
		this.needListOfProducts = needListOfProducts;
	}

	public Set<Need> getImportList() {
		return importList;
	}

	public void setImportList(Set<Need> importList) {
		this.importList = importList;
	}

	public Need getNewNeed() {
		return newNeed;
	}

	public void setNewNeed(Need newNeed) {
		this.newNeed = newNeed;
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	public List<ProductDTO> completeText(String query) {
		completeTextResults = manageProductFacadeService
				.searchProductByName(query);
		return completeTextResults;
	}

	public List<ProductDTO> getCompleteTextResults() {
		return completeTextResults;
	}

	public void setCompleteTextResults(List<ProductDTO> completeTextResults) {
		this.completeTextResults = completeTextResults;
	}

	public ManageProductFacadeService getManageProductFacadeService() {
		return manageProductFacadeService;
	}

	public void setManageProductFacadeService(
			ManageProductFacadeService manageProductFacadeService) {
		this.manageProductFacadeService = manageProductFacadeService;
	}
	
}
