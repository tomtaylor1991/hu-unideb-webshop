package hu.unideb.webshop.material;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.service.ManageMaterialFacadeService;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "materialController")
public class MaterialController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageMaterialFacadeService}")
    private ManageMaterialFacadeService manageMaterialFacadeService;
    private String newMaterialName = "";
    private double newMaterialCost = 0.0;
    private MaterialDTO selectedMaterial = null;

    public void addMaterial() {
        boolean fail = false;
        if (newMaterialName.length() == 0) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("material_name_required")));
            fail = true;
        }
        if (!(newMaterialCost > 0.0)) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("material_cost_required")));
            fail = true;
        }
        if (fail) {
            return;
        }

        MaterialDTO material = new MaterialDTO();
        material.setMaterialName(newMaterialName);
        material.setCost(newMaterialCost);
        manageMaterialFacadeService.createMaterial(material);
        newMaterialName = "";
        newMaterialCost = 0.0;
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlg_create').hide()");
        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        LocaleSwitcher.getMessage("material_created")
                        + material.getMaterialName()));
    }

    public void removeMaterial() {
        if (selectedMaterial != null) {
            //System.out.println("Remove Material: " + selectedMaterial.getId());
            manageMaterialFacadeService.removeMaterial(selectedMaterial);
            selectedMaterial = null;
            FacesContext.getCurrentInstance().addMessage("msgs",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            LocaleSwitcher.getMessage("material_deleted")));
        }
    }

    public void updateMaterial() {
        if (selectedMaterial != null) {
            boolean fail = false;
            if (selectedMaterial.getMaterialName().length() == 0) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("material_name_required")));
                fail = true;
            }
            if (!(selectedMaterial.getCost() > 0)) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("material_cost_required")));
                fail = true;
            }
            if (fail) {
                return;
            }

            manageMaterialFacadeService.updateMaterial(selectedMaterial);
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlg_edit').hide()");
            FacesContext.getCurrentInstance().addMessage("msgs",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            LocaleSwitcher.getMessage("material_edited")
                            + selectedMaterial.getMaterialName()));
        }
    }

    public void unselectMaterial() {
        selectedMaterial = null;
    }

    public ManageMaterialFacadeService getManageMaterialFacadeService() {
        return manageMaterialFacadeService;
    }

    public void setManageMaterialFacadeService(
            ManageMaterialFacadeService manageMaterialFacadeService) {
        this.manageMaterialFacadeService = manageMaterialFacadeService;
    }

    public String getNewMaterialName() {
        return newMaterialName;
    }

    public void setNewMaterialName(String newMaterialName) {
        this.newMaterialName = newMaterialName;
    }

    public double getNewMaterialCost() {
        return newMaterialCost;
    }

    public void setNewMaterialCost(double newMaterialCost) {
        this.newMaterialCost = newMaterialCost;
    }

    public MaterialDTO getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(MaterialDTO selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public void onRowSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        LocaleSwitcher.getMessage("material_selected")
                        + selectedMaterial.getMaterialName()));
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        LocaleSwitcher.getMessage("material_unselected")
                        + selectedMaterial.getMaterialName()));
    }

}
