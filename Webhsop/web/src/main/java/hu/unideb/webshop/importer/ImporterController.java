package hu.unideb.webshop.importer;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ManageIncomeFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "importerController")
public class ImporterController implements Serializable {

    @ManagedProperty(value = "#{manageRegistryFacadeService}")
    private ManageRegistryFacadeService manageRegistryFacadeService;

    @ManagedProperty(value = "#{manageIncomeFacadeService}")
    private ManageIncomeFacadeService manageIncomeFacadeService;

    private static final long serialVersionUID = 1L;

    private MaterialDTO selectedMaterial;
    private int quantity;
    private List<RegistryDTO> registryList;
    private RegistryDTO deletedElement;
    private WarehouseDTO selectedWH;

    @PostConstruct
    public void initRegistryController() {
        registryList = new ArrayList<>();
    }

    public void addRegistryToList() {
    	/*
        if (selectedMaterial != null) {
            RegistryDTO registry = new RegistryDTO();
            registry.setMaterial(selectedMaterial);
            registry.setQuantity(quantity);
            boolean isRepeat = false;
            for (RegistryDTO c : registryList) {
                if (selectedMaterial.getId().equals(c.getMaterial().getId())) {
                    c.setQuantity(quantity);
                    isRepeat = true;
                    break;
                }
            }
            if (!isRepeat) {
                registryList.add(registry);
            }
            selectedMaterial = null;
        }*/
    }

    public void saveToDB() {
    	/*
        for (RegistryDTO r : registryList) {
            r.setWarehouse(selectedWH);
            r.setStatus("FREE");
            IncomeDTO incomeDTO = new IncomeDTO();
            // incomeDTO.setOrderId(null);
            incomeDTO.setPrice((int) manageRegistryFacadeService
                    .costOfMaterial(r));
            incomeDTO.setOrderId(new Long(0));
            incomeDTO.setComment(r.getMaterial().getMaterialName() + ","
                    + r.getQuantity());
            manageIncomeFacadeService.createIncome(incomeDTO);
        }
        manageRegistryFacadeService.saveRegistrys(registryList);
        registryList = new ArrayList<>();
        selectedMaterial = null;
        quantity = 0;
        selectedWH = null;*/
    }

    public void removeRegistryFromList() {
        registryList.remove(deletedElement);
        deletedElement = null;
    }

    public MaterialDTO getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(MaterialDTO selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<RegistryDTO> getRegistryList() {
        return registryList;
    }

    public void setRegistryList(List<RegistryDTO> registryList) {
        this.registryList = registryList;
    }

    public RegistryDTO getDeletedElement() {
        return deletedElement;
    }

    public void setDeletedElement(RegistryDTO deletedElement) {
        this.deletedElement = deletedElement;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

    public WarehouseDTO getSelectedWH() {
        return selectedWH;
    }

    public void setSelectedWH(WarehouseDTO selectedWH) {
        this.selectedWH = selectedWH;
    }

    public ManageIncomeFacadeService getManageIncomeFacadeService() {
        return manageIncomeFacadeService;
    }

    public void setManageIncomeFacadeService(
            ManageIncomeFacadeService manageIncomeFacadeService) {
        this.manageIncomeFacadeService = manageIncomeFacadeService;
    }

}
