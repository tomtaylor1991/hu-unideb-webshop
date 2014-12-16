package hu.unideb.webshop.recipe;

import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.service.ManageRecipeFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyRecipeModel extends LazyDataModel<RecipeDTO> {

    private static final long serialVersionUID = 1L;

    private ManageRecipeFacadeService manageRecipeFacadeService = null;
    private List<RecipeDTO> visibleRecipeList;

    public LazyRecipeModel() {

    }

    public LazyRecipeModel(ManageRecipeFacadeService manageRecipeFacadeService) {
        this.manageRecipeFacadeService = manageRecipeFacadeService;
    }

    @Override
    public RecipeDTO getRowData(String rowKey) {
        if (visibleRecipeList != null || rowKey != null) {
            for (RecipeDTO recipe : visibleRecipeList) {
                if (recipe.getId().equals(rowKey)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RecipeDTO recipe) {
        if (recipe == null) {
            return null;
        }
        return recipe.getId();
    }

    @Override
    public List<RecipeDTO> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {

        String filter = "";
        String filterColumnName = "";
        if (filters.keySet().size() > 0) {
            filter = (String) filters.values().toArray()[0];
            filterColumnName = filters.keySet().iterator().next();
        }
        if (sortField == null) {
            sortField = "name";
        }
        int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
        visibleRecipeList = manageRecipeFacadeService.getRecipeSortedList(first
                / pageSize, pageSize, sortField, dir, filter, filterColumnName);

        int dataSize = manageRecipeFacadeService.getRowNumber();
        this.setRowCount(dataSize);

        return visibleRecipeList;
    }

    public ManageRecipeFacadeService getmanageRecipeFacadeService() {
        return manageRecipeFacadeService;
    }

    public void setmanageRecipeFacadeService(
            ManageRecipeFacadeService manageRecipeFacadeService) {
        this.manageRecipeFacadeService = manageRecipeFacadeService;
    }

    public List<RecipeDTO> getVisibleRecipeList() {
        return visibleRecipeList;
    }

    public void setVisibleRecipeList(List<RecipeDTO> visibleRecipeList) {
        this.visibleRecipeList = visibleRecipeList;
    }

}
