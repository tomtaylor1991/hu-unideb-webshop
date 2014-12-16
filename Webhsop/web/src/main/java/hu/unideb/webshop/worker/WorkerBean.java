package hu.unideb.webshop.worker;

import hu.unideb.webshop.order.LazyOrderModel;
import hu.unideb.webshop.service.ManageOrderFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "workerBean")
public class WorkerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageOrderFacadeService}")
    private ManageOrderFacadeService manageOrderFacadeService;

    LazyOrderModel lazyOrderModel;

    @PostConstruct
    public void init() {
        lazyOrderModel = new LazyOrderModel(manageOrderFacadeService);
    }

    public ManageOrderFacadeService getManageOrderFacadeService() {
        return manageOrderFacadeService;
    }

    public void setManageOrderFacadeService(
            ManageOrderFacadeService manageOrderFacadeService) {
        this.manageOrderFacadeService = manageOrderFacadeService;
    }

    public LazyOrderModel getLazyOrderModel() {
        return lazyOrderModel;
    }

    public void setLazyOrderModel(LazyOrderModel lazyOrderModel) {
        this.lazyOrderModel = lazyOrderModel;
    }

}
