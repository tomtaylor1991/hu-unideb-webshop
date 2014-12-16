package hu.unideb.webshop.beer;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.service.ManageBeerFacadeService;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

public class LazyBeerModel extends LazyDataModel<BeerDTO> {

	private static final long serialVersionUID = 1L;

	private ManageBeerFacadeService manageBeerFacadeService = null;
	private List<BeerDTO> visibleBeerList;

	public LazyBeerModel() {

	}

	public LazyBeerModel(ManageBeerFacadeService manageBeerFacadeService) {
		this.manageBeerFacadeService = manageBeerFacadeService;
	}

	@Override
	public BeerDTO getRowData(String rowkey) {
		if (visibleBeerList != null || rowkey != null) {
			for (BeerDTO beer : visibleBeerList) {
				if (beer.getId().equals(rowkey)) {
					return beer;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(BeerDTO beer) {
		if (beer == null) {
			return null;
		}
		return beer.getId();
	}

	@Override
	public List<BeerDTO> load(int first, int pagesize, String sortField,
			org.primefaces.model.SortOrder sortOrder,
			Map<String, Object> filters) {
		visibleBeerList = manageBeerFacadeService.getBeerList(first, pagesize);
		int dataSize = visibleBeerList.size();
		this.setRowCount(dataSize);

		return visibleBeerList;
	}

	public ManageBeerFacadeService getmanageBeerFacadeService() {
		return manageBeerFacadeService;
	}

	public void setmanageBeerFacadeService(
			ManageBeerFacadeService manageBeerFacadeService) {
		this.manageBeerFacadeService = manageBeerFacadeService;
	}
}
