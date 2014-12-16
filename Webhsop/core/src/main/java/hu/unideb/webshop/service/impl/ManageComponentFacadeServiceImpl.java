package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.service.ComponentService;
import hu.unideb.webshop.service.ManageComponentFacadeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageComponentFacadeService")
public class ManageComponentFacadeServiceImpl implements
        ManageComponentFacadeService {

    @Autowired
    ComponentService componentService;

    @Override
    public List<ComponentDTO> getComponentList(RecipeDTO recipe) {
        return componentService.getComponentList(recipe);
    }

    @Override
    public void createComponent(ComponentDTO componentDTO) {
        componentService.createComponent(componentDTO);

    }

    @Override
    public void removeComponent(ComponentDTO componentDTO) {
        componentService.removeComponent(componentDTO);

    }

    @Override
    public void updateComponent(ComponentDTO componentDTO) {
        componentService.updateComponent(componentDTO);

    }

    @Override
    public List<ComponentDTO> findByRecipe(RecipeDTO recipe) {
        return componentService.findByRecipe(recipe);
    }
}
