package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.RecipeDTO;

import java.util.List;

/**
 * The Interface BeerService.
 */
public interface BeerService {

	/**
	 * Gets the beer list.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the beer list
	 */
	List<BeerDTO> getBeerList(int page, int size);
	
	/**
	 * Gets the beer list.
	 *
	 * @return the beer list
	 */
	List<BeerDTO> getBeerList();

	/**
	 * Removes the beer.
	 *
	 * @param id the id
	 */
	void removeBeer(Long id);
	
	/**
	 * Creates the beer.
	 *
	 * @param recipe the recipe
	 * @param beer the beer
	 */
	void createBeer(RecipeDTO recipe, BeerDTO beer);
	
	/**
	 * Update beer.
	 *
	 * @param beer the beer
	 */
	void updateBeer(BeerDTO beer);

	/**
	 * Gets the beer by name.
	 *
	 * @param name the name
	 * @return the beer by name
	 */
	BeerDTO getBeerByName(String name);
	
	/**
	 * Gets the needed beers statics.
	 *
	 * @return the needed beers statics
	 */
	List<Need> getNeededBeersStatics();
	
	/**
	 * Gets the in wh beers statics.
	 *
	 * @return the in wh beers statics
	 */
	List<Need> getInWHBeersStatics();
}
