package hu.unideb.webshop.partner;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.Status;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManagePartnerFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "partnerController")
public class PartnerController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{managePartnerFacadeService}")
    private ManagePartnerFacadeService managePartnerFacadeService;
    @ManagedProperty(value = "#{manageOrderFacadeService}")
    private ManageOrderFacadeService manageOrderFacadeService;
    @ManagedProperty(value = "#{manageRegistryFacadeService}")
    private ManageRegistryFacadeService manageRegistryFacadeService;
    private String newPartnerName = "";
    private String newPartnerAddress = "";
    private String newPartnerType = "";
    private PartnerDTO selectedPartner = null;
    private List<OrderDTO> selectedPartnersOrders = new ArrayList<OrderDTO>();

    public String getOrderStatus(String key) {
        try {
            String s = Status.getByKey(key).toString();
            return s;
        } catch (NullPointerException e) {
            return "undefined status";
        }

    }

    public void addPartner() {
        boolean fail = false;
        if (newPartnerName.length() == 0) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("partner_name_required")));
            fail = true;
        }
        if (newPartnerAddress.length() == 0) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("partner_address_required")));
            fail = true;
        }
        if (newPartnerType == null
                || (newPartnerType.equals("normal") == false && newPartnerType
                .equals("premium") == false)) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("partner_type_required")));
            fail = true;
        }
        if (fail) {
            return;
        }

        PartnerDTO partner = new PartnerDTO();
        partner.setAddress(newPartnerAddress);
        partner.setName(newPartnerName);
        partner.setType(newPartnerType);
        managePartnerFacadeService.createPartner(partner);

        newPartnerAddress = "";
        newPartnerName = "";
        newPartnerType = "";
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlg_create').hide()");

        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("%s %s!", partner.getName(),
                                LocaleSwitcher.getMessage("partner_created"))));
    }

    public void removePartner() {
        if (selectedPartner != null) {
            // managePartnerFacadeService.removePartner(selectedPartner);
            selectedPartner = null;
        }
    }

    public void updatePartner() {
        if (selectedPartner != null) {
            boolean fail = false;
            if (selectedPartner.getName().length() == 0) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("partner_name_required")));
                fail = true;
            }
            if (selectedPartner.getAddress().length() == 0) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("partner_address_required")));
                fail = true;
            }
            if (selectedPartner.getType() == null
                    || (selectedPartner.getType().equals("normal") == false && selectedPartner
                    .getType().equals("premium") == false)) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("partner_type_required")));
                fail = true;
            }
            if (fail) {
                return;
            }

            managePartnerFacadeService.updatePartner(selectedPartner);
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlg_edit').hide()");

            FacesContext.getCurrentInstance().addMessage("msgs",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            String.format("%s %s!", selectedPartner.getName(),
                                    LocaleSwitcher.getMessage("partner_updated"))));
        }
    }

    public void unselectPartner() {
        selectedPartner = null;
    }

    public void onRowSelect(SelectEvent event) {
        selectedPartnersOrders = manageOrderFacadeService
                .getOrdersByPartner(selectedPartner);

        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("%s %s!", LocaleSwitcher.getMessage("partner_selected"),
                                selectedPartner.getName())));
    }

    public void onRowUnselect(UnselectEvent event) {
        selectedPartnersOrders = new ArrayList<OrderDTO>();
        FacesContext.getCurrentInstance().addMessage(
                "msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "Partner unselected: " + selectedPartner.getName()));
    }

    public ManagePartnerFacadeService getManagePartnerFacadeService() {
        return managePartnerFacadeService;
    }

    public void setManagePartnerFacadeService(
            ManagePartnerFacadeService managePartnerFacadeService) {
        this.managePartnerFacadeService = managePartnerFacadeService;
    }

    public String getNewPartnerName() {
        return newPartnerName;
    }

    public void setNewPartnerName(String newPartnerName) {
        this.newPartnerName = newPartnerName;
    }

    public String getNewPartnerAddress() {
        return newPartnerAddress;
    }

    public void setNewPartnerAddress(String newPartnerAddress) {
        this.newPartnerAddress = newPartnerAddress;
    }

    public String getNewPartnerType() {
        return newPartnerType;
    }

    public void setNewPartnerType(String newPartnerType) {
        this.newPartnerType = newPartnerType;
    }

    public PartnerDTO getSelectedPartner() {
        return selectedPartner;
    }

    public void setSelectedPartner(PartnerDTO selectedPartner) {
        this.selectedPartner = selectedPartner;
    }

    public ManageOrderFacadeService getManageOrderFacadeService() {
        return manageOrderFacadeService;
    }

    public void setManageOrderFacadeService(
            ManageOrderFacadeService manageOrderFacadeService) {
        this.manageOrderFacadeService = manageOrderFacadeService;
    }

    public List<OrderDTO> getSelectedPartnersOrders() {
        return selectedPartnersOrders;
    }

    public void setSelectedPartnersOrders(List<OrderDTO> selectedPartnersOrders) {
        this.selectedPartnersOrders = selectedPartnersOrders;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

}
