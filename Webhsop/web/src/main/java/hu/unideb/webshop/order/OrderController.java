package hu.unideb.webshop.order;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManagePartnerFacadeService;
import hu.unideb.webshop.service.ManageProductFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "orderController")
public class OrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	@ManagedProperty(value = "#{managePartnerFacadeService}")
	private ManagePartnerFacadeService managePartnerFacadeService;

	@ManagedProperty(value = "#{manageProductFacadeService}")
	private ManageProductFacadeService manageProductFacadeService;

	private PartnerDTO selectedPartner;
	private OrderDTO selectedOrder;
	private String beerName;
	private String newOrderName;

	private String selectedPartnerName = null;
	private RegistryDTO newRegistry = new RegistryDTO();
	private Set<RegistryDTO> addedProducts = new HashSet<RegistryDTO>();
	private List<String> partnerNames;
	private List<ProductDTO> completeTextResults;
	private ProductDTO selectedProduct;
	private int quantity;

	@PostConstruct
	public void init() {
		partnerNames = new ArrayList<String>();
		// FUCK THIS SHIT
		for (PartnerDTO p : managePartnerFacadeService.getPartnerList(0, 1000))
			partnerNames.add(p.getName());
	}

	public List<ProductDTO> completeText(String query) {
		completeTextResults = manageProductFacadeService
				.searchProductByName(query);
		return completeTextResults;
	}

	public String getNewOrderName() {
		return newOrderName;
	}

	public void setNewOrderName(String newOrderName) {
		this.newOrderName = newOrderName;
	}

	public RegistryDTO getNewRegistry() {
		return newRegistry;
	}

	public void setNewRegistry(RegistryDTO newRegistry) {
		this.newRegistry = newRegistry;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public OrderDTO getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(OrderDTO selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	public List<String> completePartnerName(String query) {
		List<String> results = new ArrayList<String>();
		List<PartnerDTO> partners = new ArrayList<PartnerDTO>();
		partners = managePartnerFacadeService.getPartnerList(0, 10);
		for (PartnerDTO p : partners) {
			if (p.getName().toLowerCase().contains(query.toLowerCase())) {
				results.add(p.getName());
			}
		}
		return results;
	}

	public void createOrder() {
		OrderDTO newOrder;
		newOrder = new OrderDTO();

		newOrder.setName(selectedPartner.getName());
		newOrder.setDate(new Date());
		newOrder.setStatus("NEW");
		newOrder.setPartnerDTO(managePartnerFacadeService
				.getPartnerByName(selectedPartnerName));
		
		manageOrderFacadeService.createOrder(newOrder);
		for (RegistryDTO registry : addedProducts) {
			registry.setOrder(newOrder);
		}

		manageRegistryFacadeService
				.saveRegistrys(new ArrayList<>(addedProducts));

		selectedPartner = null;
		addedProducts.clear();
		beerName = null;
		quantity = 0;

		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dlg_create').hide()");
	}

	public void onRowSelect(SelectEvent event) {
		selectedOrder = (OrderDTO) event.getObject();
	}

	public void addProductToOrder() {
		if (selectedProduct != null) {
			RegistryDTO registry = new RegistryDTO();
			registry.setQuantity(quantity);
			registry.setProduct(selectedProduct);
			registry.setStatus("ORDERDATA");
			addedProducts.add(registry);
			// /
			quantity = 0;
			selectedProduct = null;
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		selectedOrder = null;
	}

	public void unselectOrder() {
		selectedPartnerName = null;
		addedProducts.clear();
		beerName = null;
		quantity = 0;
		selectedOrder = null;
	}

	public void selectPartner() {
		if (selectedPartnerName == null) {
			FacesContext.getCurrentInstance().addMessage(
					"selectmsgs",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							LocaleSwitcher
									.getMessage("order_selectpartner_error")));
			return;
		}
		selectedPartner = managePartnerFacadeService
				.getPartnerByName(selectedPartnerName);
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dlg_select').hide()");
		rc.execute("PF('dlg_create').show()");
	}

	public void removeBeerFromOrder(RegistryDTO registry) {
		/*
		 * for (RegistryDTO reg : addedBeers) { if (reg.getBeer() != null) { if
		 * (reg.getBeer().getName() .equals(registry.getBeer().getName())) {
		 * addedBeers.remove(reg); break; } } }
		 * FacesContext.getCurrentInstance() .addMessage( "createmsgs", new
		 * FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
		 * String.format("%s %s", registry.getBeer() .getName(), LocaleSwitcher
		 * .getMessage("orders_delete_info"))));
		 */
	}

	public ManagePartnerFacadeService getManagePartnerFacadeService() {
		return managePartnerFacadeService;
	}

	public void setManagePartnerFacadeService(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

	public String getSelectedPartnerName() {
		return selectedPartnerName;
	}

	public void setSelectedPartnerName(String selectedPartnerName) {
		this.selectedPartnerName = selectedPartnerName;
	}

	public List<String> getPartnerNames() {
		return partnerNames;
	}

	public void setPartnerNames(List<String> partnerNames) {
		this.partnerNames = partnerNames;
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

	public Set<RegistryDTO> getAddedProducts() {
		return addedProducts;
	}

	public void setAddedProducts(Set<RegistryDTO> addedProducts) {
		this.addedProducts = addedProducts;
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public List<RegistryDTO> findByOrder(OrderDTO order){
		return manageRegistryFacadeService.findByOrder(order);
	}
}
