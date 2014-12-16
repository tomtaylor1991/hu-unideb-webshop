package hu.unideb.webshop.warehouse;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.ManageWarehouseFacadeService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "warehouseController")
public class WarehouseController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageWarehouseFacadeService}")
    private ManageWarehouseFacadeService manageWarehouseFacadeService;

    @ManagedProperty(value = "#{manageRegistryFacadeService}")
    private ManageRegistryFacadeService manageRegistryFacadeService;

    private String newWarehouseName = "";
    private String newWarehouseAddress = "";
    private WarehouseDTO selectedWarehouse = null;
    private List<RegistryDTO> selectedWHRegistryList = null;
    private List<RegistryDTO> selectedWHRegistryBeerList = null;

    public void addWarehouse() {
        boolean fail = false;
        if (newWarehouseName.length() == 0) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("warehouse_name_required")));
            fail = true;
        }
        if (newWarehouseAddress.length() == 0) {
            FacesContext.getCurrentInstance().addMessage("msgs2",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            LocaleSwitcher.getMessage("warehouse_address_required")));
            fail = true;
        }
        if (fail) {
            return;
        }

        WarehouseDTO wh = new WarehouseDTO();
        wh.setAddress(newWarehouseAddress);
        wh.setName(newWarehouseName);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlg_create').hide()");
        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        LocaleSwitcher.getMessage("warehouses_created")
                        + newWarehouseName));
        manageWarehouseFacadeService.createWarehouse(wh);
        newWarehouseAddress = "";
        newWarehouseName = "";
    }

    public void removeWarehouse() {
        if (selectedWarehouse != null) {
            manageWarehouseFacadeService.removeWarehouse(selectedWarehouse);
            FacesContext.getCurrentInstance().addMessage("msgs",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            LocaleSwitcher.getMessage("warehouses_deleted")
                            + selectedWarehouse.getName()));
            selectedWarehouse = null;
        }
    }

    public void updateWarehouse() {
        if (selectedWarehouse != null) {
            boolean fail = false;
            if (selectedWarehouse.getName().length() == 0) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("warehouse_name_required")));
                fail = true;
            }
            if (selectedWarehouse.getAddress().length() == 0) {
                FacesContext.getCurrentInstance().addMessage("msgs1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                LocaleSwitcher.getMessage("warehouse_address_required")));
                fail = true;
            }
            if (fail) {
                return;
            }

            manageWarehouseFacadeService.updateWarehouse(selectedWarehouse);
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlg_edit').hide()");
            FacesContext.getCurrentInstance().addMessage("msgs",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            LocaleSwitcher.getMessage("warehouses_updated")
                            + selectedWarehouse.getName()));

        }
    }

    public void updateSelectedWHRegistry() {
        if (selectedWarehouse != null) {
            List<RegistryDTO> list = manageRegistryFacadeService
                    .getRegistrysByMaterial(selectedWarehouse);
            Map<Long, RegistryDTO> map = new HashMap<Long, RegistryDTO>();

            for (RegistryDTO currentRegistry : list) {
                //System.out.println(currentRegistry);
                if (currentRegistry.getMaterial() != null
                        && map.keySet().contains(
                                currentRegistry.getMaterial().getId())) {
                    RegistryDTO tmpReg = map.get(currentRegistry.getMaterial()
                            .getId());
                    int currentQuantity = tmpReg.getQuantity();
                    currentRegistry.setQuantity(currentRegistry.getQuantity()
                            + currentQuantity);
                    map.put(currentRegistry.getMaterial().getId(),
                            currentRegistry);
                } else if (currentRegistry.getMaterial() != null) {
                    map.put(currentRegistry.getMaterial().getId(),
                            currentRegistry);
                }
            }
            selectedWHRegistryList = new LinkedList<RegistryDTO>();
            for (Long id : map.keySet()) {
                selectedWHRegistryList.add(map.get(id));
            }

        }
    }

    public void updateSelectedWHBeerRegistry() {
        if (selectedWarehouse != null) {
            List<RegistryDTO> list = manageRegistryFacadeService
                    .getRegistrysByBeer(selectedWarehouse);
            Map<Long, RegistryDTO> map = new HashMap<Long, RegistryDTO>();

            for (RegistryDTO currentRegistry : list) {
                //System.out.println(currentRegistry);
                if (currentRegistry.getBeer() != null
                        && map.keySet().contains(
                                currentRegistry.getBeer().getId())) {
                    RegistryDTO tmpReg = map.get(currentRegistry.getBeer()
                            .getId());
                    int currentQuantity = tmpReg.getQuantity();
                    currentRegistry.setQuantity(currentRegistry.getQuantity()
                            + currentQuantity);
                    map.put(currentRegistry.getBeer().getId(), currentRegistry);
                } else if (currentRegistry.getBeer() != null) {
                    map.put(currentRegistry.getBeer().getId(), currentRegistry);
                }
            }
            selectedWHRegistryBeerList = new LinkedList<RegistryDTO>();
            for (Long id : map.keySet()) {
                selectedWHRegistryBeerList.add(map.get(id));
            }

        }
    }

    public void unselectWarehouse() {
        selectedWarehouse = null;
    }

    public void onRowSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "", LocaleSwitcher
                        .getMessage("warehouses_selected")
                        + selectedWarehouse.getName()));
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesContext.getCurrentInstance().addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "", LocaleSwitcher
                        .getMessage("warehouses_unselected")
                        + selectedWarehouse.getName()));
    }

    public ManageWarehouseFacadeService getManageWarehouseFacadeService() {
        return manageWarehouseFacadeService;
    }

    public void setManageWarehouseFacadeService(
            ManageWarehouseFacadeService manageWarehouseFacadeService) {
        this.manageWarehouseFacadeService = manageWarehouseFacadeService;
    }

    public String getNewWarehouseName() {
        return newWarehouseName;
    }

    public void setNewWarehouseName(String newWarehouseName) {
        this.newWarehouseName = newWarehouseName;
    }

    public String getNewWarehouseAddress() {
        return newWarehouseAddress;
    }

    public void setNewWarehouseAddress(String newWarehouseAddress) {
        this.newWarehouseAddress = newWarehouseAddress;
    }

    public WarehouseDTO getSelectedWarehouse() {
        return selectedWarehouse;
    }

    public void setSelectedWarehouse(WarehouseDTO selectedWarehouse) {
        this.selectedWarehouse = selectedWarehouse;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

    public List<RegistryDTO> getSelectedWHRegistryList() {
        return selectedWHRegistryList;
    }

    public void setSelectedWHRegistryList(
            List<RegistryDTO> selectedWHRegistryList) {
        this.selectedWHRegistryList = selectedWHRegistryList;
    }

    public List<RegistryDTO> getSelectedWHRegistryBeerList() {
        return selectedWHRegistryBeerList;
    }

    public void setSelectedWHRegistryBeerList(
            List<RegistryDTO> selectedWHRegistryBeerList) {
        this.selectedWHRegistryBeerList = selectedWHRegistryBeerList;
    }

}
