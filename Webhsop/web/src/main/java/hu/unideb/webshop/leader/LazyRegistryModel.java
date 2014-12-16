package hu.unideb.webshop.leader;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyRegistryModel extends LazyDataModel<RegistryDTO> {

    private static final long serialVersionUID = 1L;

    private ManageRegistryFacadeService manageRegistryFacadeService = null;
    private List<RegistryDTO> visibleRegistryList;
    private RegistryDTO selectedRegistry;

    public LazyRegistryModel() {

    }

    public LazyRegistryModel(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
        // status = RegistryStatus.NEW;
    }

    @Override
    public RegistryDTO getRowData(String rowKey) {
        if (visibleRegistryList != null || rowKey != null) {
            for (RegistryDTO registry : visibleRegistryList) {
                if (registry.getId().equals(rowKey)) {
                    return registry;
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RegistryDTO registry) {
        if (registry == null) {
            return null;
        }
        return registry.getId();
    }

    @Override
    public List<RegistryDTO> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
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
        visibleRegistryList = manageRegistryFacadeService
                .getRegistrySortedList(first / pageSize, pageSize, sortField,
                        dir, filter, filterColumnName);

        int dataSize = visibleRegistryList.size();
        this.setRowCount(dataSize);

        return visibleRegistryList;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

    public List<RegistryDTO> getVisibleRegistryList() {
        return visibleRegistryList;
    }

    public void setVisibleRegistryList(List<RegistryDTO> visibleRegistryList) {
        this.visibleRegistryList = visibleRegistryList;
    }

    public RegistryDTO getSelectedRegistry() {
        return selectedRegistry;
    }

    public void setSelectedRegistry(RegistryDTO selectedRegistry) {
        this.selectedRegistry = selectedRegistry;
    }
}
