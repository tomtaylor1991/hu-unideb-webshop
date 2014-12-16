package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.BeerDao;
import hu.unideb.webshop.dao.RecipeDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.entity.Beer;
import hu.unideb.webshop.entity.Recipe;
import hu.unideb.webshop.service.BeerService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("beerService")
public class BeerServiceImpl implements BeerService {

    @Autowired
    BeerDao beerDao;

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserService userService;

    @Autowired
    RegistryDao registryDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<BeerDTO> getBeerList(int page, int size) {
        List<BeerDTO> ret = new ArrayList<BeerDTO>(size);
        Page<Beer> entities = beerDao.findAll(new PageRequest(page, size));
        if (entities != null && entities.getSize() > 0) {
            List<Beer> contents = entities.getContent();
            for (Beer beer : contents) {
                ret.add(beerDao.toDto(beer));
            }
        }
        return ret;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createBeer(RecipeDTO recipeDTO, BeerDTO beerDTO) {

        Beer beer = beerDao.toEntity(beerDTO, null);
        Recipe recipe = recipeDao.toEntity(recipeDTO, null);
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            beer.setRecUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            beer.setRecDate(new Date());
            beer.setModUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            beer.setModDate(new Date());
        }

        beer = beerDao.save(beer);
        beerDTO.setId(beerDao.toDto(beer).getId());

        recipe.setBeerId(beer.getId());
        beer.setRecipeId(recipe.getId());

        recipe = recipeDao.save(recipe);
        recipeDTO.setId(recipeDao.toDto(recipe).getId());
        beer = beerDao.save(beer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeBeer(Long id) {
        Beer beer = beerDao.findOne(id);
        /*if (beer == null) {
         System.out.println("Not exist this beer, id: " + id);
         }*/
        beerDao.delete(beer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBeer(BeerDTO beer) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            beer.setModUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            beer.setModDate(new Date());
        }
        beerDao.save(beerDao.toEntity(beer, null));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<BeerDTO> getBeerList() {
        List<BeerDTO> ret = new ArrayList<BeerDTO>();
        /*
         * //List<BeerDTO> beers = beerDao.findAll(); if (entities != null &&
         * entities.getSize() > 0) { List<Beer> contents =
         * entities.getContent(); for (Beer beer : contents) {
         * ret.add(beerDao.toDto(beer)); } }
         */
        return ret;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BeerDTO getBeerByName(String name) {
        Page<Beer> entities = beerDao.findAll(new PageRequest(0, 100));
        if (entities != null && entities.getSize() > 0) {
            List<Beer> contents = entities.getContent();
            for (Beer beer : contents) {
                if (beer.getName().equals(name)) {
                    return beerDao.toDto(beer);
                }
            }
        }
        return null;
    }

    @Override
    public List<Need> getNeededBeersStatics() {
        List<Need> needs = new LinkedList<Need>();
        for (Beer beer : beerDao.findAll()) {
            try {
                needs.add(new Need(beer.getName(), registryDao
                        .sumBeersByQuantityAndOrderIsNotNull(beer.getId())));
            } catch (NullPointerException e) {
                needs.add(new Need(beer.getName(), 0));
            }

        }
        return needs;
    }

    @Override
    public List<Need> getInWHBeersStatics() {
        List<Need> needs = new LinkedList<Need>();
        for (Beer beer : beerDao.findAll()) {
            try {
                needs.add(new Need(beer.getName(), registryDao
                        .sumBeersByQuantityAndOrderIsNull(beer.getId())));
            } catch (NullPointerException e) {
                needs.add(new Need(beer.getName(), 0));
            }

        }
        return needs;
    }
}
