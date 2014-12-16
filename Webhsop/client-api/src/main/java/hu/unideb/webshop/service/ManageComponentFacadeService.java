package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.RecipeDTO;

import java.util.List;

/**
 * The Interface ManageComponentFacadeService.
 */
public interface ManageComponentFacadeService {

    /**
     * Gets the component list.
     *
     * @param recipe the recipe
     * @return the component list
     */
    List<ComponentDTO> getComponentList(RecipeDTO recipe);

    /**
     * Find by recipe.
     *
     * @param recipe the recipe
     * @return the list
     */
    List<ComponentDTO> findByRecipe(RecipeDTO recipe);

    /**
     * Creates the component.
     *
     * @param componentDTO the component dto
     */
    void createComponent(ComponentDTO componentDTO);

    /**
     * Removes the component.
     *
     * @param componentDTO the component dto
     */
    void removeComponent(ComponentDTO componentDTO);

    /**
     * Update component.
     *
     * @param componentDTO the component dto
     */
    void updateComponent(ComponentDTO componentDTO);

}
