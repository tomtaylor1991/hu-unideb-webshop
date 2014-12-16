package hu.unideb.webshop.partner;

import hu.unideb.webshop.service.ManagePartnerFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "partnerBean")
public class PartnerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LazyPartnerModel partnerModel;
	
	@ManagedProperty(value = "#{managePartnerFacadeService}")
	private ManagePartnerFacadeService managePartnerFacadeService;
	
	@PostConstruct
	public void init() {
		partnerModel = new LazyPartnerModel(managePartnerFacadeService);
	}

	public LazyPartnerModel getPartnerModel() {
		return partnerModel;
	}

	public void setPartnerModel(LazyPartnerModel partnerModel) {
		this.partnerModel = partnerModel;
	}

	public ManagePartnerFacadeService getManagePartnerFacadeService() {
		return managePartnerFacadeService;
	}

	public void setManagePartnerFacadeService(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

}
