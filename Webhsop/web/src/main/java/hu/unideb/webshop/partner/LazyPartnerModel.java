package hu.unideb.webshop.partner;

import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.service.ManagePartnerFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyPartnerModel extends LazyDataModel<PartnerDTO> {

	private static final long serialVersionUID = 1L;

	private ManagePartnerFacadeService managePartnerFacadeService = null;
	private List<PartnerDTO> visiblePartnerList;

	public LazyPartnerModel() {

	}

	public LazyPartnerModel(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

	@Override
	public PartnerDTO getRowData(String rowKey) {

		if (visiblePartnerList != null || rowKey != null) {
			for (PartnerDTO partner : visiblePartnerList) {
				if (partner.getId().equals(rowKey)) {
					return partner;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(PartnerDTO partner) {
		if (partner == null) {
			return null;
		}
		return partner.getId();
	}

	@Override
	public List<PartnerDTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		visiblePartnerList = managePartnerFacadeService.getPartnerList(first,
				pageSize);
		int dataSize = visiblePartnerList.size();
		this.setRowCount(dataSize);
		return visiblePartnerList;
	}

	public ManagePartnerFacadeService getManagePartnerFacadeService() {
		return managePartnerFacadeService;
	}

	public void setManagePartnerFacadeService(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

	public List<PartnerDTO> getVisiblePartnerList() {
		return visiblePartnerList;
	}

	public void setVisiblePartnerList(List<PartnerDTO> visiblePartnerList) {
		this.visiblePartnerList = visiblePartnerList;
	}

}
