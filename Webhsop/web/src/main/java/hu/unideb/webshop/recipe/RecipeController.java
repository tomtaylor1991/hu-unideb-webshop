package hu.unideb.webshop.recipe;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.MenuView;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.service.ManageBeerFacadeService;
import hu.unideb.webshop.service.ManageComponentFacadeService;
import hu.unideb.webshop.service.ManageMaterialFacadeService;
import hu.unideb.webshop.service.ManageRecipeFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "recipeController")
public class RecipeController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageRecipeFacadeService}")
    private ManageRecipeFacadeService manageRecipeFacadeService;
    @ManagedProperty(value = "#{manageBeerFacadeService}")
    private ManageBeerFacadeService manageBeerFacadeService;
    @ManagedProperty(value = "#{manageMaterialFacadeService}")
    private ManageMaterialFacadeService manageMaterialFacadeService;
    @ManagedProperty(value = "#{manageComponentFacadeService}")
    private ManageComponentFacadeService manageComponentFacadeService;

    private RecipeDTO selectedRecipe = null;
    private BeerDTO selectedBeer = null;
    private BeerDTO newBeer = null;
    private List<ComponentDTO> recipeComponentList;
    private List<ComponentDTO> deletedComponentList;
    /**
     *
     */
    private MaterialDTO selectedMaterial;
    private int selectedMaterialQuantity;
    /**
     *
     */
    List<MaterialDTO> allMaterial;
    private ComponentDTO allMaterialSelectedComponent;

    @PostConstruct
    public void initRecipeController() {
        allMaterial = manageMaterialFacadeService.getAllMaterialList();
        recipeComponentList = new ArrayList<ComponentDTO>();
    }

    public void addComponentToList() {
        if (selectedMaterial != null) {
            ComponentDTO newComponent = new ComponentDTO();
            newComponent.setMaterialDTO(selectedMaterial);
            newComponent.setQuantity(selectedMaterialQuantity);
            newComponent.setRecipeDTO(selectedRecipe);

            boolean isRepeat = false;
            for (ComponentDTO c : recipeComponentList) {
                if (selectedMaterial.getId().equals(c.getMaterialDTO().getId())) {
                    c.setQuantity(selectedMaterialQuantity);
                    isRepeat = true;
                    break;
                }
            }
            if (!isRepeat) {
                recipeComponentList.add(newComponent);
            }
            selectedMaterial = null;
            selectedMaterialQuantity = 0;
        }
    }

    public List<ComponentDTO> getRecipeComponentList() {
        return recipeComponentList;
    }

    public void setRecipeComponentList(List<ComponentDTO> recipeComponentList) {
        this.recipeComponentList = recipeComponentList;
    }

    public BeerDTO getSelectedBeer() {
        return selectedBeer;
    }

    public void setSelectedBeer(BeerDTO selectedBeer) {
        this.selectedBeer = selectedBeer;
    }

    public MaterialDTO getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(MaterialDTO selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public BeerDTO getNewBeer() {
        return newBeer;
    }

    public void setNewBeer(BeerDTO newBeer) {
        this.newBeer = newBeer;
    }

    public void initBeer() {
        newBeer = new BeerDTO();
    }

    public void setBeer() {
        selectedBeer = selectedRecipe.getBeer();
    }

    public void updateBeer() {
        manageBeerFacadeService.updateBeer(selectedBeer);
    }

    public void editBeer() {
        selectedBeer = selectedRecipe.getBeer();
    }

    public void unsetBeer() {
        newBeer = null;
        selectedBeer = null;
    }

    public ManageRecipeFacadeService getManageRecipeFacadeService() {
        return manageRecipeFacadeService;
    }

    public void setManageRecipeFacadeService(
            ManageRecipeFacadeService manageRecipeFacadeService) {
        this.manageRecipeFacadeService = manageRecipeFacadeService;
    }

    public ManageBeerFacadeService getManageBeerFacadeService() {
        return manageBeerFacadeService;
    }

    public void setManageBeerFacadeService(
            ManageBeerFacadeService manageBeerFacadeService) {
        this.manageBeerFacadeService = manageBeerFacadeService;
    }

    public void addBeer() {
        if (selectedRecipe != null && newBeer != null) {
            newBeer.setRecipeDTO(selectedRecipe);
            selectedRecipe.setBeer(newBeer);
            manageBeerFacadeService.createBeer(selectedRecipe, newBeer);
            newBeer = null;
        }
    }

    public void addRecipe() {
        selectedRecipe = new RecipeDTO();
        updateRecipeForm();
    }

    public void updateRecipeForm() {
        if (selectedRecipe != null) {
            recipeComponentList = manageComponentFacadeService
                    .getComponentList(selectedRecipe);
        }
    }

    public void removeRecipe() {
        if (selectedRecipe != null) {
            manageRecipeFacadeService.removeRecipe(selectedRecipe);
            selectedRecipe = null;
        } else {
            //System.out.println("Ki kell jelolnie egy receptet torleshez!");
            // MenuView.addMessage("Ki kell jelolnie egy receptet torleshez!");
        }
    }

    public void updateRecipe() {
        if (selectedRecipe != null) {
            manageRecipeFacadeService.updateRecipe(selectedRecipe);
            if (recipeComponentList != null) {
                for (ComponentDTO c : recipeComponentList) {
                    manageComponentFacadeService.createComponent(c);
                }
            }
            if (deletedComponentList != null) {
                for (ComponentDTO c : deletedComponentList) {
                    manageComponentFacadeService.removeComponent(c);
                }
            }
            MenuView.addMessage(LocaleSwitcher.getMessage("recipe_update")
                    + selectedRecipe.getName());
        } else {
            //System.out.println("Ki kell jelolnie egy receptet!");
            // MenuView.addMessage("Ki kell jelolnie egy receptet torleshez!");
        }
    }

    public void unselectRecipe() {
        selectedRecipe = null;
        deletedComponentList = null;
    }

    public ManageRecipeFacadeService getmanageRecipeFacadeService() {
        return manageRecipeFacadeService;
    }

    public void setmanageRecipeFacadeService(
            ManageRecipeFacadeService manageRecipeFacadeService) {
        this.manageRecipeFacadeService = manageRecipeFacadeService;
    }

    public RecipeDTO getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(RecipeDTO selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }

    public void onRowSelect(SelectEvent event) {
        MenuView.addMessage(LocaleSwitcher.getMessage("recipe_selected")
                + selectedRecipe.getName());
    }

    public void onRowUnselect(UnselectEvent event) {
        MenuView.addMessage(LocaleSwitcher.getMessage("recipe_unselected")
                + selectedRecipe.getName());
    }

    public ManageMaterialFacadeService getManageMaterialFacadeService() {
        return manageMaterialFacadeService;
    }

    public void setManageMaterialFacadeService(
            ManageMaterialFacadeService manageMaterialFacadeService) {
        this.manageMaterialFacadeService = manageMaterialFacadeService;
    }

    public List<MaterialDTO> getAllMaterial() {
        return allMaterial;
    }

    public void setAllMaterial(List<MaterialDTO> allMaterial) {
        this.allMaterial = allMaterial;
    }

    public int getSelectedMaterialQuantity() {
        return selectedMaterialQuantity;
    }

    public void setSelectedMaterialQuantity(int selectedMaterialQuantity) {
        //System.out.println(selectedMaterialQuantity);
        this.selectedMaterialQuantity = selectedMaterialQuantity;
    }

    public void removeComponentFromList() {
        recipeComponentList.remove(allMaterialSelectedComponent);
        if (deletedComponentList == null) {
            deletedComponentList = new ArrayList<>();
        }
        deletedComponentList.add(allMaterialSelectedComponent);
        allMaterialSelectedComponent = null;
    }

    public ComponentDTO getAllMaterialSelectedComponent() {
        return allMaterialSelectedComponent;
    }

    public void setAllMaterialSelectedComponent(
            ComponentDTO allMaterialSelectedComponent) {
        this.allMaterialSelectedComponent = allMaterialSelectedComponent;
    }

    public ManageComponentFacadeService getManageComponentFacadeService() {
        return manageComponentFacadeService;
    }

    public void setManageComponentFacadeService(
            ManageComponentFacadeService manageComponentFacadeService) {
        this.manageComponentFacadeService = manageComponentFacadeService;
    }

}
