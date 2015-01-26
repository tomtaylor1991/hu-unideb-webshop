package hu.unideb.webshop;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * A class which can switch between sites using the redirectToUrl function.
 */
@SessionScoped
@ManagedBean
public class MenuView {

    public void showRecipes() {
        redirectToUrl("/pages/secure/recipes.xhtml");
    }

    public void showMaterials() {
        redirectToUrl("/pages/secure/materials.xhtml");
    }

    public void showWarehouses() {
        redirectToUrl("/pages/secure/warehouses.xhtml");
    }

    public void showOrders() {
        redirectToUrl("/pages/secure/orders.xhtml");
    }

    public void showPartners() {
        redirectToUrl("/pages/secure/partners.xhtml");
    }

    public void showImporter() {
        redirectToUrl("/pages/secure/importer.xhtml");
    }

    public void showWorker() {
        redirectToUrl("/pages/secure/worker.xhtml");
    }

    public void showExporter() {
        redirectToUrl("/pages/secure/exporter.xhtml");
    }

    public void redirectToUrl(String url) {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
