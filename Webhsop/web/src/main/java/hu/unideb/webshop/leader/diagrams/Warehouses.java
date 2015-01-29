package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.ManageWarehouseFacadeService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

@ViewScoped
@ManagedBean(name = "warehousesController")
public class Warehouses implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageWarehouseFacadeService}")
	private ManageWarehouseFacadeService manageWarehouseFacadeService;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	List<PieChartModel> products;	
	
	@PostConstruct
	public void init() {
		generateProductChartList();
	}

	private void generateProductChartList() {

		products = new LinkedList<PieChartModel>();
		List<RegistryDTO> selectedWHList = null;
		List<WarehouseDTO> warehouses = manageWarehouseFacadeService
				.getWarehouseList(0, 100);

		for (WarehouseDTO selectedWarehouse : warehouses) {
			List<RegistryDTO> list = manageRegistryFacadeService
					.getRegistrysByWarehouse(selectedWarehouse);
			Map<Long, RegistryDTO> map = new HashMap<Long, RegistryDTO>();

			for (RegistryDTO currentRegistry : list) {
				// System.out.println(currentRegistry);
				if (currentRegistry.getProduct() != null
						&& !currentRegistry.getStatus().equals("ORDERDATA")
						&& map.keySet().contains(
								currentRegistry.getProduct().getId())) {
					RegistryDTO tmpReg = map.get(currentRegistry.getProduct()
							.getId());
					int currentQuantity = tmpReg.getQuantity();
					currentRegistry.setQuantity(currentRegistry.getQuantity()
							+ currentQuantity);
					map.put(currentRegistry.getProduct().getId(),
							currentRegistry);
				} else if (currentRegistry.getProduct() != null) {
					map.put(currentRegistry.getProduct().getId(),
							currentRegistry);
				}
			}
			selectedWHList = new LinkedList<RegistryDTO>();
			for (Long id : map.keySet()) {
				selectedWHList.add(map.get(id));
			}

			PieChartModel pieModel2 = new PieChartModel();

			if (selectedWHList.size() == 0) {
				pieModel2.set("Empty WH", 0);
			}

			for (RegistryDTO r : selectedWHList) {
				String text = r.getProduct().getName() + " ( "
						+ r.getQuantity() + " )";
				pieModel2.set(text, r.getQuantity());
			}

			pieModel2.setTitle(selectedWarehouse.getName());
			pieModel2.setLegendPosition("e");
			pieModel2.setFill(false);
			pieModel2.setShowDataLabels(true);
			pieModel2.setDiameter(150);

			products.add(pieModel2);

		}

	}

	public ManageWarehouseFacadeService getManageWarehouseFacadeService() {
		return manageWarehouseFacadeService;
	}

	public void setManageWarehouseFacadeService(
			ManageWarehouseFacadeService manageWarehouseFacadeService) {
		this.manageWarehouseFacadeService = manageWarehouseFacadeService;
	}

	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	public List<PieChartModel> getProducts() {
		return products;
	}

	public void setProducts(List<PieChartModel> products) {
		this.products = products;
	}

}
