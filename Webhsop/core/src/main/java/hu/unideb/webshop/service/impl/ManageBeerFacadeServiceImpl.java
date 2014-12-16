package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.service.BeerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageBeerFacadeService")
public class ManageBeerFacadeServiceImpl implements hu.unideb.webshop.service.ManageBeerFacadeService{

	@Autowired
	BeerService beerService;
	
	@Override
	public List<BeerDTO> getBeerList(int page, int size) {
		return beerService.getBeerList(page, size);
	}

	@Override
	public void createBeer(RecipeDTO recipe, BeerDTO beer) {
		beerService.createBeer(recipe, beer);	
	}

	@Override
	public void removeBeer(Long id) {
		beerService.removeBeer(id);
		
	}

	@Override
	public void updateBeer(BeerDTO beer) {
		beerService.updateBeer(beer);	
	}

	
	@Override
	public void createBeer(BeerDTO beer) {
		
	}

	@Override
	public List<BeerDTO> getBeerList() {
		return beerService.getBeerList();
	}

	@Override
	public BeerDTO getBeetByName(String name) {
		return beerService.getBeerByName(name);
	}

	@Override
	public List<Need> getNeededBeersStatics() {
		
		return beerService.getNeededBeersStatics();
	}

	@Override
	public List<Need> getInWHBeersStatics() {

		return beerService.getInWHBeersStatics();
	}
	
}
