package hu.unideb.webshop.beer;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.service.ManageBeerFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "beerBean")
public class BeerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyBeerModel beerModel;
	private BeerDTO selectedBeer = null;

	@ManagedProperty(value = "#{manageBeerFacadeService}")
	private ManageBeerFacadeService manageBeerFacadeService;

	@PostConstruct
	public void init() {
		beerModel = new LazyBeerModel(manageBeerFacadeService);
	}

	public LazyBeerModel getBeerModel() {
		return beerModel;
	}

	public ManageBeerFacadeService getmanageBeerFacadeService() {
		return manageBeerFacadeService;
	}

	public void setmanageBeerFacadeService(
			ManageBeerFacadeService manageBeerFacadeService) {
		this.manageBeerFacadeService = manageBeerFacadeService;
	}

	public BeerDTO getSelectedBeer() {
		return selectedBeer;
	}

	public void setBeerModel(LazyBeerModel beerModel) {
		this.beerModel = beerModel;
	}

	public void setSelectedBeer(BeerDTO selectedBeer) {
		this.selectedBeer = selectedBeer;
	}

}
