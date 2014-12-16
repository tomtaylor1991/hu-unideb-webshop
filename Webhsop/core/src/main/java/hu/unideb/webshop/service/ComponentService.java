package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.RecipeDTO;

import java.util.List;

/**
 * The Interface ComponentService.
 */
public interface ComponentService {

    /**
     * Gets the component list.
     *
     * @param recipe the recipe
     * @return the component list
     */
    List<ComponentDTO> getComponentList(RecipeDTO recipe);

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

    /**
     * Find by recipe.
     *
     * @param recipe the recipe
     * @return the list
     */
    List<ComponentDTO> findByRecipe(RecipeDTO recipe);

}
