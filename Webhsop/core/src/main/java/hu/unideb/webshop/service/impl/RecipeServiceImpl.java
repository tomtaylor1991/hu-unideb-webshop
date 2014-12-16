package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.RecipeDao;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.entity.Recipe;
import hu.unideb.webshop.service.RecipeService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("recipeService")
class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserService userService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<RecipeDTO> getRecipeList(int page, int size) {
        // System.out.println("Recipe lazy" + page + " " + size);
        List<RecipeDTO> ret = new ArrayList<RecipeDTO>(size);
        Page<Recipe> entities = recipeDao
                .findAll(new PageRequest(page, size/* sort */));
        if (entities != null && entities.getSize() > 0) {
            List<Recipe> contents = entities.getContent();
            for (Recipe recipe : contents) {
                ret.add(recipeDao.toDto(recipe));
            }
        }
        return ret;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createRecipe(RecipeDTO recipe) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            recipe.setRecUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            recipe.setRecDate(new Date());
            recipe.setModUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            recipe.setModDate(new Date());
        }
        Recipe recipeentity = recipeDao.toEntity(recipe, null);
        recipeentity = recipeDao.save(recipeentity);
        recipe.setId(recipeDao.toDto(recipeentity).getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeRecipe(RecipeDTO recipe) {
        recipeDao.delete(recipeDao.toEntity(recipe, null));
    }

    @Override
    public void updateRecipe(RecipeDTO recipe) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            recipe.setRecUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            recipe.setRecDate(new Date());
        }
        recipeDao.save(recipeDao.toEntity(recipe, null));
    }

    @Override
    public List<RecipeDTO> getRecipeSortedList(int page, int size,
            String sortField, int sortOrder, String filter, String filterColumnName) {
        Direction dir = sortOrder == 1 ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        if ("name".equals(sortField)) {
            sortField = "recipeName";
        }
        if ("name".equals(filterColumnName)) {
            filterColumnName = "recipeName";
        }
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                new Order(dir, sortField)));
        List<RecipeDTO> ret = new ArrayList<RecipeDTO>(size);
        Page<Recipe> entities;
        if (filter.length() != 0 && filterColumnName.equals("recipeName")) {
            entities = recipeDao
                    .findByRecipeNameStartsWith(filter, pageRequest);
        } else {
            entities = recipeDao.findAll(pageRequest);
        }

        if (entities != null && entities.getSize() > 0) {
            List<Recipe> contents = entities.getContent();
            for (Recipe recipe : contents) {
                ret.add(recipeDao.toDto(recipe));
            }
        }
        return ret;
    }


    @Override
    public int getRowNumber() {
        return recipeDao.countById();
    }
}
