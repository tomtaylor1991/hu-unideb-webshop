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

	List<PieChartModel> materials;
	List<PieChartModel> beers;

	@PostConstruct
	public void init() {
		generateMaterialChartList();
		generateBeerChartList();
	}

	private void generateMaterialChartList() {
		/*
		materials = new LinkedList<PieChartModel>();
		List<RegistryDTO> selectedWHRegistryList = null;
		List<WarehouseDTO> warehouses = manageWarehouseFacadeService
				.getWarehouseList(0, 100);

		for (WarehouseDTO selectedWarehouse : warehouses) {
			List<RegistryDTO> list = manageRegistryFacadeService
					.getRegistrysByMaterial(selectedWarehouse);
			Map<Long, RegistryDTO> map = new HashMap<Long, RegistryDTO>();

			for (RegistryDTO currentRegistry : list) {
				// System.out.println(currentRegistry);
				if (currentRegistry.getMaterial() != null
						&& map.keySet().contains(
								currentRegistry.getMaterial().getId())) {
					RegistryDTO tmpReg = map.get(currentRegistry.getMaterial()
							.getId());
					int currentQuantity = tmpReg.getQuantity();
					currentRegistry.setQuantity(currentRegistry.getQuantity()
							+ currentQuantity);
					map.put(currentRegistry.getMaterial().getId(),
							currentRegistry);
				} else if (currentRegistry.getMaterial() != null) {
					map.put(currentRegistry.getMaterial().getId(),
							currentRegistry);
				}
			}
			selectedWHRegistryList = new LinkedList<RegistryDTO>();
			for (Long id : map.keySet()) {
				selectedWHRegistryList.add(map.get(id));
			}

			PieChartModel pieModel2 = new PieChartModel();

			if (selectedWHRegistryList.size() == 0) {
				pieModel2.set("Empty WH", 0);
			}

			for (RegistryDTO r : selectedWHRegistryList) {
				String text = r.getMaterial().getMaterialName() + " ( "
						+ r.getQuantity() + " )";
				pieModel2.set(text, r.getQuantity());
			}

			pieModel2.setTitle(selectedWarehouse.getName());
			pieModel2.setLegendPosition("e");
			pieModel2.setFill(false);
			pieModel2.setShowDataLabels(true);
			pieModel2.setDiameter(150);

			materials.add(pieModel2);

		}
*/
	}

	private void generateBeerChartList() {
		/*
		beers = new LinkedList<PieChartModel>();
		List<RegistryDTO> selectedWHRegistryBeerList = null;
		List<WarehouseDTO> warehouses = manageWarehouseFacadeService
				.getWarehouseList(0, 100);
		
		for (WarehouseDTO selectedWarehouse : warehouses) {
			List<RegistryDTO> list = manageRegistryFacadeService
					.getRegistrysByBeer(selectedWarehouse);
			Map<Long, RegistryDTO> map = new HashMap<Long, RegistryDTO>();

			for (RegistryDTO currentRegistry : list) {
				//System.out.println(currentRegistry);
				if (currentRegistry.getBeer() != null
						&& map.keySet().contains(
								currentRegistry.getBeer().getId())) {
					RegistryDTO tmpReg = map.get(currentRegistry.getBeer()
							.getId());
					int currentQuantity = tmpReg.getQuantity();
					currentRegistry.setQuantity(currentRegistry.getQuantity()
							+ currentQuantity);
					map.put(currentRegistry.getBeer().getId(), currentRegistry);
				} else if (currentRegistry.getBeer() != null) {
					map.put(currentRegistry.getBeer().getId(), currentRegistry);
				}
			}
			selectedWHRegistryBeerList = new LinkedList<RegistryDTO>();
			for (Long id : map.keySet()) {
				selectedWHRegistryBeerList.add(map.get(id));
			}
			
			PieChartModel pieModel2 = new PieChartModel();

			if (selectedWHRegistryBeerList.size() == 0) {
				pieModel2.set("Empty WH", 0);
			}

			for (RegistryDTO r : selectedWHRegistryBeerList) {
				String text = r.getBeer().getName() + " ( " + r.getQuantity()
						+ " )";
				pieModel2.set(text, r.getQuantity());
			}

			pieModel2.setTitle(selectedWarehouse.getName());
			pieModel2.setLegendPosition("e");
			pieModel2.setFill(false);
			pieModel2.setShowDataLabels(true);
			pieModel2.setDiameter(150);
			
			beers.add(pieModel2);
			
		}
		*/
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

	public List<PieChartModel> getMaterials() {
		return materials;
	}

	public void setMaterials(List<PieChartModel> materials) {
		this.materials = materials;
	}

	public List<PieChartModel> getBeers() {
		return beers;
	}

	public void setBeers(List<PieChartModel> beers) {
		this.beers = beers;
	}

}
