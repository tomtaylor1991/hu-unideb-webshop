package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.ComponentDao;
import hu.unideb.webshop.dao.RecipeDao;
import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.entity.Component;
import hu.unideb.webshop.service.ComponentService;
import hu.unideb.webshop.service.UserService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("componentService")
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentDao componentDao;

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserService userService;

    @Override
    public List<ComponentDTO> getComponentList(RecipeDTO recipe) {
        List<Component> entities = componentDao.findByRecipeId(recipe.getId());
        List<ComponentDTO> ret = new LinkedList<ComponentDTO>();
        if (entities != null) {
            for (Component c : entities) {
                ret.add(componentDao.toDto(c));
            }
        }
        return ret;
    }

    @Override
    public void createComponent(ComponentDTO componentDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            componentDTO.setRecUserId(userService.getUser(userDetails.getUsername()).getId() + "");
            componentDTO.setRecDate(new Date());
            componentDTO.setModUserId(userService.getUser(userDetails.getUsername()).getId() + "");
            componentDTO.setModDate(new Date());
        }
        componentDao.save(componentDao.toEntity(componentDTO, null));
    }

    @Override
    public void removeComponent(ComponentDTO componentDTO) {
        componentDao.delete(componentDao.toEntity(componentDTO, null));

    }

    @Override
    public void updateComponent(ComponentDTO componentDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            componentDTO.setModUserId(userService.getUser(userDetails.getUsername()).getId() + "");
            componentDTO.setModDate(new Date());
        }
        componentDao.save(componentDao.toEntity(componentDTO, null));

    }

    @Override
    public List<ComponentDTO> findByRecipe(RecipeDTO recipe) {

        List<Component> component = componentDao.findByRecipeId(recipe.getId());
        List<ComponentDTO> ret = new LinkedList<ComponentDTO>();
        for (Component c : component) {
            ret.add(componentDao.toDto(c));
        }
        return ret;
    }

}
