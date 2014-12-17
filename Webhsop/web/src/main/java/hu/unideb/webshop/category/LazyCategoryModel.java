package hu.unideb.webshop.category;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.service.ManageCategoryFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyCategoryModel extends LazyDataModel<CategoryDTO>{

	private static final long serialVersionUID = 1L;
	
	private ManageCategoryFacadeService manageCategoryFacadeService = null;
	private List<CategoryDTO> visibleCategoryList;
	
	public LazyCategoryModel(
			ManageCategoryFacadeService manageCategoryFacadeService) {
		this.manageCategoryFacadeService = manageCategoryFacadeService;
	}

    @Override
    public CategoryDTO getRowData(String rowKey) {
    	//System.out.println("rowKey: "+rowKey);
        if (visibleCategoryList != null || rowKey != null) {
            for (CategoryDTO c : visibleCategoryList) {
                if (c.getId().equals(rowKey)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(CategoryDTO c) {
    	//System.out.println("rowKey2: "+c);
        if (c == null) {
            return null;
        }
        return c.getId();
    }

    @Override
    public List<CategoryDTO> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
    	
        String filter = "";
        String filterColumnName = "";
        if (filters.keySet().size() > 0) {
            filter = (String) filters.values().toArray()[0];
            filterColumnName = filters.keySet().iterator().next();
        }
        System.out.println(sortField + " " + filter + " " + filterColumnName);
        //set default sort field
        if (sortField == null) {
            sortField = "name";
        }
        int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
        visibleCategoryList = manageCategoryFacadeService.getCategoryList(first
                / pageSize, pageSize, sortField, dir, filter, filterColumnName);

        int dataSize = manageCategoryFacadeService.getRowNumber();
        this.setRowCount(dataSize);

        return visibleCategoryList;
    }
    
	public ManageCategoryFacadeService getManageCategoryFacadeService() {
		return manageCategoryFacadeService;
	}

	public void setManageCategoryFacadeService(
			ManageCategoryFacadeService manageCategoryFacadeService) {
		this.manageCategoryFacadeService = manageCategoryFacadeService;
	}

	public List<CategoryDTO> getVisibleCategoryList() {
		return visibleCategoryList;
	}

	public void setVisibleCategoryList(List<CategoryDTO> visibleCategoryList) {
		this.visibleCategoryList = visibleCategoryList;
	}
	
	

}
