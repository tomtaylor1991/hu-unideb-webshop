package hu.unideb.webshop.importer;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageComponentFacadeService;
import hu.unideb.webshop.service.ManageMaterialFacadeService;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRecipeFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

@ViewScoped
@ManagedBean(name = "importerNecessaryMaterials")
public class ImporterNecessaryMaterials implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	@ManagedProperty(value = "#{manageRecipeFacadeService}")
	private ManageRecipeFacadeService manageRecipeFacadeService;

	@ManagedProperty(value = "#{manageMaterialFacadeService}")
	private ManageMaterialFacadeService manageMaterialFacadeService;

	@ManagedProperty(value = "#{manageComponentFacadeService}")
	private ManageComponentFacadeService manageComponentFacadeService;

	private RegistryDTO selectedRegistry;

	private List<MaterialNeed> materialNeeds = new ArrayList<MaterialNeed>();

	private List<MaterialNeed> filtered;

	public List<MaterialNeed> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<MaterialNeed> filtered) {
		this.filtered = filtered;
	}

	public static class MaterialNeed {
		private String materialName;
		private Integer quantity;

		public MaterialNeed() {

		}

		public MaterialNeed(String materialName, Integer quantity) {
			this.materialName = materialName;
			this.quantity = quantity;
		}

		public String getMaterialName() {
			return materialName;
		}

		public void setMaterialName(String materialName) {
			this.materialName = materialName;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

	}

	public ManageRecipeFacadeService getManageRecipeFacadeService() {
		return manageRecipeFacadeService;
	}

	public void setManageRecipeFacadeService(
			ManageRecipeFacadeService manageRecipeFacadeService) {
		this.manageRecipeFacadeService = manageRecipeFacadeService;
	}

	public ManageMaterialFacadeService getManageMaterialFacadeService() {
		return manageMaterialFacadeService;
	}

	public void setManageMaterialFacadeService(
			ManageMaterialFacadeService manageMaterialFacadeService) {
		this.manageMaterialFacadeService = manageMaterialFacadeService;
	}

	public ManageComponentFacadeService getManageComponentFacadeService() {
		return manageComponentFacadeService;
	}

	public void setManageComponentFacadeService(
			ManageComponentFacadeService manageComponentFacadeService) {
		this.manageComponentFacadeService = manageComponentFacadeService;
	}

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
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

	public RegistryDTO getSelectedRegistry() {
		return selectedRegistry;
	}

	public void setSelectedRegistry(RegistryDTO selectedRegistry) {
		this.selectedRegistry = selectedRegistry;
	}

	public List<MaterialNeed> getMaterialNeeds() {
		return materialNeeds;
	}

	public void setVisibleRegistrys(List<MaterialNeed> materialNeeds) {
		this.materialNeeds = materialNeeds;
	}

	@PostConstruct
	public void init() {
/*
		Map<String, Integer> inWarehouses = new HashMap<String, Integer>();
		Map<String, Integer> needForOrders = new HashMap<String, Integer>();

		for (OrderDTO order : manageOrderFacadeService
				.getOrdersByStatus("NEEDMATERIAL")) {
			List<LeaderTestInfoDTO.Need> materialsNeeded = manageRegistryFacadeService
					.isOrderCanBeServe(order).getNeeded();

			for (LeaderTestInfoDTO.Need need : materialsNeeded) {
				if (needForOrders.containsKey(need.getMaterial()
						.getMaterialName())) {
					int value = needForOrders.get(need.getMaterial()
							.getMaterialName());
					needForOrders.put(need.getMaterial().getMaterialName(),
							value + need.getNeedQuantity());
				} else {
					inWarehouses.put(need.getMaterial().getMaterialName(),
							need.getInWhQuantity());
					needForOrders.put(need.getMaterial().getMaterialName(),
							need.getNeedQuantity());
				}
			}
		}

		for (Map.Entry<String, Integer> entry : needForOrders.entrySet()) {
			if (entry.getValue() > inWarehouses.get(entry.getKey())) {
				materialNeeds.add(new MaterialNeed(entry.getKey(), entry
						.getValue() - inWarehouses.get(entry.getKey())));
			}
		}
*/
	}

	@SuppressWarnings("deprecation")
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sh = wb.getSheetAt(0);
		sh.shiftRows(0, sh.getLastRowNum(), 1);

		HSSFRow row = sh.createRow(0);
		row.setHeightInPoints((short) 28);
		HSSFCell cell = row.createCell(0);
		Date date = new Date();
		cell.setCellValue(LocaleSwitcher.getMessage("necessary_materials") + " " + 
				String.format("%d.%02d.%02d %02d:%02d", date.getYear() + 1900, date.getMonth() + 1, date.getDate(),
						date.getHours(), date.getMinutes()));

		sh.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 24);
		titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		style = wb.createCellStyle();
		style.setFont(titleFont);
		cell.setCellStyle(style);
	}

	@SuppressWarnings("deprecation")
	public String getXLSFileName() {
		Date date = new Date();
		return "materials_needed_"+ String.format("%d_%02d_%02d_%02d_%02d", date.getYear() + 1900, date.getMonth() + 1,
				date.getDate(), date.getHours(), date.getMinutes());
	}
}
