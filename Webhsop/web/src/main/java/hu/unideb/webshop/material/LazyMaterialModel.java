package hu.unideb.webshop.material;

import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.service.ManageMaterialFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyMaterialModel extends LazyDataModel<MaterialDTO> {

	private static final long serialVersionUID = 1L;

	private ManageMaterialFacadeService manageMaterialFacadeService = null;
	private List<MaterialDTO> visibleMaterialList;
	
	public LazyMaterialModel(){
		
	}
	
	public LazyMaterialModel(ManageMaterialFacadeService manageMaterialFacadeService){
		this.manageMaterialFacadeService = manageMaterialFacadeService;
	}
	
	@Override
	public MaterialDTO getRowData(String rowKey) {
		if (visibleMaterialList != null || rowKey != null) {
			for (MaterialDTO material : visibleMaterialList) {
				if (material.getId().equals(rowKey)){
					return material;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(MaterialDTO material) {
		if (material == null) {
			return null;
		}
		return material.getId();
	}

	@Override
	public List<MaterialDTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		/*System.out.println("Recipe lazy" + first + " " + pageSize + " "
				+ sortField + " " + sortOrder + " " + filters);*/
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();			
		}
		if (sortField == null) {
			sortField = "materialName";
		}
		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleMaterialList = manageMaterialFacadeService.getMaterialList(first
				/ pageSize, pageSize, sortField, dir, filter, filterColumnName);
		int dataSize = manageMaterialFacadeService.getRowNumber();
		this.setRowCount(dataSize);

		return visibleMaterialList;
	}

	public ManageMaterialFacadeService getmanageMaterialFacadeService() {
		return manageMaterialFacadeService;
	}

	public void setmanagevFacadeService(
			ManageMaterialFacadeService manageMaterialFacadeService) {
		this.manageMaterialFacadeService = manageMaterialFacadeService;
	}

	public List<MaterialDTO> getVisibleMaterialList() {
		return visibleMaterialList;
	}

	public void setVisibleMaterialList(List<MaterialDTO> visibleMaterialList) {
		this.visibleMaterialList = visibleMaterialList;
	}
	
}
