package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.service.ManageRecipeFacadeService;
import hu.unideb.webshop.service.RecipeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageRecipeFacadeService")
public class ManageRecipeFacadeServiceImpl implements ManageRecipeFacadeService {

	@Autowired
	RecipeService recipeService;

	public List<RecipeDTO> getRecipeList(int page, int size) {
		return recipeService.getRecipeList(page, size);
	}

	@Override
	public void createRecipe(RecipeDTO recipe) {
		recipeService.createRecipe(recipe);
	}

	@Override
	public void removeRecipe(RecipeDTO recipe) {
		recipeService.removeRecipe(recipe);
	}

	@Override
	public void updateRecipe(RecipeDTO recipe) {
		recipeService.updateRecipe(recipe);
	}

	@Override
	public List<RecipeDTO> getRecipeSortedList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		return recipeService.getRecipeSortedList(page, size, sortField,
				sortOrder, filter, filterColumnName);
	}

	@Override
	public int getRowNumber() {
		return recipeService.getRowNumber();
	}

	@Override
	public RecipeDTO getRecipeByBeer(BeerDTO beer) {

		return recipeService.getRecipeByBeer(beer);
	}

}
