package hu.unideb.webshop.order;

import hu.unideb.webshop.service.ManageOrderFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "orderBean")
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyOrderModel orderModel;

	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	@PostConstruct
	public void init() {
		orderModel = new LazyOrderModel(manageOrderFacadeService);
	}

	public LazyOrderModel getOrderModel() {
		return orderModel;
	}

	public ManageOrderFacadeService getmanageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setmanageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public void setOrderModel(LazyOrderModel orderModel) {
		this.orderModel = orderModel;
	}
}
