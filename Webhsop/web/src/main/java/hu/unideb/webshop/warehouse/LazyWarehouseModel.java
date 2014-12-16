package hu.unideb.webshop.warehouse;

import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.service.ManageWarehouseFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyWarehouseModel extends LazyDataModel<WarehouseDTO> {

    private static final long serialVersionUID = 1L;

    private ManageWarehouseFacadeService manageWarehouseFacadeService = null;
    private List<WarehouseDTO> visibleWarehouseList;

    public LazyWarehouseModel() {

    }

    public LazyWarehouseModel(
            ManageWarehouseFacadeService manageWarehouseFacadeService) {
        this.manageWarehouseFacadeService = manageWarehouseFacadeService;
    }

    @Override
    public WarehouseDTO getRowData(String rowKey) {
        if (visibleWarehouseList != null || rowKey != null) {
            for (WarehouseDTO warehouse : visibleWarehouseList) {
                if (warehouse.getId().equals(rowKey)) {
                    return warehouse;
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(WarehouseDTO warehouse) {
        if (warehouse == null) {
            return null;
        }
        return warehouse.getId();
    }

    @Override
    public List<WarehouseDTO> load(int first, int pageSize, String sortField,
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
        visibleWarehouseList = manageWarehouseFacadeService.getWarehouseList(
                first / pageSize, pageSize, sortField, dir, filter,
                filterColumnName);
        int dataSize = manageWarehouseFacadeService.getRowNumber();
        this.setRowCount(dataSize);
        return visibleWarehouseList;
    }

    public ManageWarehouseFacadeService getManageWarehouseFacadeService() {
        return manageWarehouseFacadeService;
    }

    public void setManageWarehouseFacadeService(
            ManageWarehouseFacadeService manageWarehouseFacadeService) {
        this.manageWarehouseFacadeService = manageWarehouseFacadeService;
    }

    public List<WarehouseDTO> getVisibleWarehouseList() {
        return visibleWarehouseList;
    }

    public void setVisibleWarehouseList(List<WarehouseDTO> visibleWarehouseList) {
        this.visibleWarehouseList = visibleWarehouseList;
    }

}
