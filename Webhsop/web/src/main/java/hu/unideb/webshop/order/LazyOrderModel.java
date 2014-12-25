package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyOrderModel extends LazyDataModel<OrderDTO> {

    private static final long serialVersionUID = 1L;

    private ManageOrderFacadeService manageOrderFacadeService = null;
    private List<OrderDTO> visibleOrderList;

    public LazyOrderModel() {

    }

    public LazyOrderModel(ManageOrderFacadeService manageOrderFacadeService) {
        this.manageOrderFacadeService = manageOrderFacadeService;
    }

    @Override
    public OrderDTO getRowData(String rowKey) {
        if (visibleOrderList != null || rowKey != null) {
            for (OrderDTO order : visibleOrderList) {
                if (order.getId().equals(rowKey)) {
                    return order;
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(OrderDTO order) {
        if (order == null) {
            return null;
        }
        return order.getId();
    }

    @Override
    public List<OrderDTO> load(int first, int pageSize, String sortField,
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
            sortField = "status";
        }
        int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
        visibleOrderList = manageOrderFacadeService.getOrderList(first
                / pageSize, pageSize, sortField, dir, filter, filterColumnName);
        int dataSize = manageOrderFacadeService.getRowNumber();
        this.setRowCount(dataSize);

        return visibleOrderList;
    }

    public ManageOrderFacadeService getmanageOrderFacadeService() {
        return manageOrderFacadeService;
    }

    public void setmanagevFacadeService(
            ManageOrderFacadeService manageOrderFacadeService) {
        this.manageOrderFacadeService = manageOrderFacadeService;
    }

    public List<OrderDTO> getVisibleOrderList() {
        return visibleOrderList;
    }

    public void setVisibleOrderList(List<OrderDTO> visibleOrderList) {
        this.visibleOrderList = visibleOrderList;
    }
}
