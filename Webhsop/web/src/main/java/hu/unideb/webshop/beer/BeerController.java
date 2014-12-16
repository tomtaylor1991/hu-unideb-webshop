package hu.unideb.webshop.beer;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.service.ManageBeerFacadeService;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "beerController")
public class BeerController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageBeerFacadeService}")
    private ManageBeerFacadeService manageBeerFacadeService;
    private String newBeerName = "";

    public void addRecipe() {
        BeerDTO beer = new BeerDTO();
        beer.setName(newBeerName);
        manageBeerFacadeService.createBeer(beer);
    }

    public void addBeer() {
        BeerDTO beer = new BeerDTO();
        beer.setName(newBeerName);
        //manageBeerFacadeService.createBeer(beer);
    }

    public void removeBeer(Long id) {
        manageBeerFacadeService.removeBeer(id);
    }

    public ManageBeerFacadeService getmanageBeerFacadeService() {
        return manageBeerFacadeService;
    }

    public void setmanageBeerFacadeService(
            ManageBeerFacadeService manageBeerFacadeService) {
        this.manageBeerFacadeService = manageBeerFacadeService;
    }

    public String getNewBeerName() {
        return newBeerName;
    }

    public void setNewBeerName(String newBeerName) {
        this.newBeerName = newBeerName;
    }

}
