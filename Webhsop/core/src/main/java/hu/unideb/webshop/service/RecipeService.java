package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.RecipeDTO;

import java.util.List;

/**
 * The Interface RecipeService.
 */
public interface RecipeService {

	/**
	 * Gets the recipe list.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the recipe list
	 */
	List<RecipeDTO> getRecipeList(int page, int size);

	/**
	 * Gets the recipe sorted list.
	 *
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filter the filter
	 * @param filterColumnName the filter column name
	 * @return the recipe sorted list
	 */
	List<RecipeDTO> getRecipeSortedList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);

	/**
	 * Creates the recipe.
	 *
	 * @param recipe the recipe
	 */
	void createRecipe(RecipeDTO recipe);

	/**
	 * Removes the recipe.
	 *
	 * @param recipe the recipe
	 */
	void removeRecipe(RecipeDTO recipe);

	/**
	 * Update recipe.
	 *
	 * @param recipe the recipe
	 */
	void updateRecipe(RecipeDTO recipe);
	
	
	
	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	int getRowNumber();
}
