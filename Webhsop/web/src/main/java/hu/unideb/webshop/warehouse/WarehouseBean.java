package hu.unideb.webshop.warehouse;

import hu.unideb.webshop.service.ManageWarehouseFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "warehouseBean")
public class WarehouseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LazyWarehouseModel warehouseModel;
	
	@ManagedProperty(value = "#{manageWarehouseFacadeService}")
	private ManageWarehouseFacadeService manageWarehouseFacadeService;
	
	@PostConstruct
	public void init() {
		warehouseModel = new LazyWarehouseModel(manageWarehouseFacadeService);
	}

	public LazyWarehouseModel getWarehouseModel() {
		return warehouseModel;
	}

	public void setWarehouseModel(LazyWarehouseModel warehouseModel) {
		this.warehouseModel = warehouseModel;
	}

	public ManageWarehouseFacadeService getManageWarehouseFacadeService() {
		return manageWarehouseFacadeService;
	}

	public void setManageWarehouseFacadeService(
			ManageWarehouseFacadeService manageWarehouseFacadeService) {
		this.manageWarehouseFacadeService = manageWarehouseFacadeService;
	}

}
