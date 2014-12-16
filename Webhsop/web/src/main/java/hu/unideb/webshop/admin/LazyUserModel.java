package hu.unideb.webshop.admin;

import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyUserModel extends LazyDataModel<UserDTO> {

    private static final long serialVersionUID = 1L;

    private ManageUserFacadeService manageUserFacadeService = null;
    private List<UserDTO> visibleUserList;

    public LazyUserModel() {

    }

    public LazyUserModel(ManageUserFacadeService manageUserFacadeService) {
        this.manageUserFacadeService = manageUserFacadeService;
    }

    @Override
    public UserDTO getRowData(String rowkey) {
        if (visibleUserList != null || rowkey != null) {
            for (UserDTO user : visibleUserList) {
                if (user.getId().equals(rowkey)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(UserDTO user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    @Override
    public List<UserDTO> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        String filter = "";
        String filterColumnName = "";
        if (filters.keySet().size() > 0) {
            filter = (String) filters.values().toArray()[0];
            filterColumnName = filters.keySet().iterator().next();
        }
        if (sortField == null) {
            sortField = "login";
        }

        int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
        visibleUserList = manageUserFacadeService.getUserList(first
                / pageSize, pageSize, sortField, dir, filter, filterColumnName);
        int dataSize = manageUserFacadeService.getRowNumber();
        this.setRowCount(dataSize);

        return visibleUserList;
    }

    public ManageUserFacadeService getManageUserFacadeService() {
        return manageUserFacadeService;
    }

    public void setManageUserFacadeService(
            ManageUserFacadeService manageUserFacadeService) {
        this.manageUserFacadeService = manageUserFacadeService;
    }

    public List<UserDTO> getVisibleUserList() {
        return visibleUserList;
    }

    public void setVisibleUserList(List<UserDTO> visibleUserList) {
        this.visibleUserList = visibleUserList;
    }

}
