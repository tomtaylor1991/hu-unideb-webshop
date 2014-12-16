package hu.unideb.webshop.worker;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ManageComponentFacadeService;
import hu.unideb.webshop.service.ManageRecipeFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.ManageWarehouseFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "workerController")
public class WorkerController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageRegistryFacadeService}")
    private ManageRegistryFacadeService manageRegistryFacadeService;

    @ManagedProperty(value = "#{manageRecipeFacadeService}")
    private ManageRecipeFacadeService manageRecipeFacadeService;

    @ManagedProperty(value = "#{manageComponentFacadeService}")
    private ManageComponentFacadeService manageComponentFacadeService;

    @ManagedProperty(value = "#{manageWarehouseFacadeService}")
    private ManageWarehouseFacadeService manageWarehouseFacadeService;

    private List<String> warehouses = new ArrayList<>();
    private HashMap<String, WarehouseDTO> map = new HashMap<>();
    private RegistryDTO selectedRegistry;
    private String destWarehouse;
    private List<RegistryDTO> visibleRegistry = new ArrayList<>();
    private List<RecipeEntry> recipe = new ArrayList<>();

    public class RecipeEntry {

        private String name;
        private String quantity;
        private String comment;

        public RecipeEntry() {
        }

        public RecipeEntry(String name, String quantity, String comment) {
            this.name = name;
            this.quantity = quantity;
            this.comment = comment;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

    }

    public HashMap<String, WarehouseDTO> getMap() {
        return map;
    }

    public void setMap(HashMap<String, WarehouseDTO> map) {
        this.map = map;
    }

    public List<String> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<String> warehouses) {
        this.warehouses = warehouses;
    }

    public ManageWarehouseFacadeService getManageWarehouseFacadeService() {
        return manageWarehouseFacadeService;
    }

    public void setManageWarehouseFacadeService(
            ManageWarehouseFacadeService manageWarehouseFacadeService) {
        this.manageWarehouseFacadeService = manageWarehouseFacadeService;
    }

    public List<RecipeEntry> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<RecipeEntry> recipe) {
        this.recipe = recipe;
    }

    public List<RegistryDTO> getVisibleRegistry() {
        return visibleRegistry;
    }

    public void setVisibleRegistry(List<RegistryDTO> visibleRegistry) {
        this.visibleRegistry = visibleRegistry;
    }

    public ManageComponentFacadeService getManageComponentFacadeService() {
        return manageComponentFacadeService;
    }

    public void setManageComponentFacadeService(
            ManageComponentFacadeService manageComponentFacadeService) {
        this.manageComponentFacadeService = manageComponentFacadeService;
    }

    public ManageRecipeFacadeService getManageRecipeFacadeService() {
        return manageRecipeFacadeService;
    }

    public void setManageRecipeFacadeService(
            ManageRecipeFacadeService manageRecipeFacadeService) {
        this.manageRecipeFacadeService = manageRecipeFacadeService;
    }

    public String getDestWarehouse() {
        return destWarehouse;
    }

    public void setDestWarehouse(String destWarehouse) {
        this.destWarehouse = destWarehouse;
    }

    public RegistryDTO getSelectedRegistry() {
        return selectedRegistry;
    }

    public void setSelectedRegistry(RegistryDTO selectedRegistry) {
        this.selectedRegistry = selectedRegistry;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

    @PostConstruct
    public void init() {
        for (WarehouseDTO w : manageWarehouseFacadeService.getWarehouseList(0, 100)) {
            warehouses.add(w.getName());
            map.put(w.getName(), w);
        }

        for (RegistryDTO r : manageRegistryFacadeService.findByStatus("NEED")) {
            if (r.getBeer() != null && r.getOrder() != null) {
                visibleRegistry.add(r);
            }
        }
    }

    public void selectRegistry() {
        recipe.clear();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, LocaleSwitcher.getMessage("worker_info"),
                        selectedRegistry.getBeer().getName() + " " + LocaleSwitcher.getMessage("worker_selected")));

        recipe.add(new RecipeEntry(LocaleSwitcher.getMessage("worker_recipe_material_name"),
                LocaleSwitcher.getMessage("worker_recipe_material_quantity"), ""));
        for (ComponentDTO c : manageComponentFacadeService
                .findByRecipe(manageRecipeFacadeService
                        .getRecipeByBeer(selectedRegistry.getBeer()))) {
            //a component hozzáadása a receptlistához
            recipe.add(new RecipeEntry(c.getMaterialDTO().getMaterialName(), c
                    .getQuantity().toString(), c.getComment()));

        }
        //a recepthez tartozó komment hozzáadása
        RecipeDTO r = manageRecipeFacadeService
                .getRecipeByBeer(selectedRegistry.getBeer());
        recipe.add(new RecipeEntry(LocaleSwitcher.getMessage("worker_recipe_header_comment") + ":", r.getComment(), ""));
    }

    public void addReadyBeerToWarehouse() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, LocaleSwitcher.getMessage("worker_info"),
                        LocaleSwitcher.getMessage("worker_success_1") + " "
                        + selectedRegistry.getQuantity() + " "
                        + LocaleSwitcher.getMessage("worker_success_2") + " "
                        + selectedRegistry.getBeer().getName() + " "
                        + LocaleSwitcher.getMessage("worker_success_3") + " "
                        + destWarehouse + " "
                        + LocaleSwitcher.getMessage("worker_success_4")));

        BeerDTO beer = selectedRegistry.getBeer();
        RecipeDTO recipe = beer.getRecipeDTO();
        selectedRegistry.setStatus("READY");
        selectedRegistry.setModDate(new Date());
        selectedRegistry.setWarehouse(map.get(destWarehouse));
        manageRegistryFacadeService.updateRegistry(selectedRegistry);
        visibleRegistry.remove(selectedRegistry);
    }

}
