package hu.unideb.webshop.admin;

import hu.unideb.webshop.service.ManageUserFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "adminBean")
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private LazyUserModel userModel;

    @ManagedProperty(value = "#{manageUserFacadeService}")
    private ManageUserFacadeService manageUserFacadeService;

    @PostConstruct
    public void init() {
        userModel = new LazyUserModel(manageUserFacadeService);
    }

    public LazyUserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(LazyUserModel userModel) {
        this.userModel = userModel;
    }

    public ManageUserFacadeService getManageUserFacadeService() {
        return manageUserFacadeService;
    }

    public void setManageUserFacadeService(
            ManageUserFacadeService manageUserFacadeService) {
        this.manageUserFacadeService = manageUserFacadeService;
    }

}
