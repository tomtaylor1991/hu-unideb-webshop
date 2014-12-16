package hu.unideb.webshop.material;

import hu.unideb.webshop.service.ManageMaterialFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "materialBean")
public class MaterialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyMaterialModel materialModel;

	@ManagedProperty(value = "#{manageMaterialFacadeService}")
	private ManageMaterialFacadeService manageMaterialFacadeService;

	@PostConstruct
	public void init() {
		materialModel = new LazyMaterialModel(manageMaterialFacadeService);
	}

	public LazyMaterialModel getMaterialModel() {
		return materialModel;
	}

	public ManageMaterialFacadeService getmanageMaterialFacadeService() {
		return manageMaterialFacadeService;
	}

	public void setmanageMaterialFacadeService(
			ManageMaterialFacadeService manageMaterialFacadeService) {
		this.manageMaterialFacadeService = manageMaterialFacadeService;
	}

	public void setMaterialModel(LazyMaterialModel materialModel) {
		this.materialModel = materialModel;
	}

}
