package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.RecipeDao;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.entity.Beer;

import org.springframework.beans.factory.annotation.Autowired;

public class BeerDaoImpl implements BaseConvertDao<Beer, BeerDTO> {

    @Autowired
    RecipeDao recipeDao;

    @Override
    public Beer toEntity(BeerDTO dto, Beer entity) {
        if (dto == null) {
            return null;
        }
        Beer ret = entity;
        if (dto.getId() == null || entity == null) {
            ret = new Beer();
            ret.setId(dto.getId());
            ret.setRecDate(dto.getRecDate());
            ret.setRecUserId(dto.getRecUserId());
        }
        ret.setModDate(dto.getModDate());
        ret.setModUserId(dto.getModUserId());
        ret.setName(dto.getName());
        ret.setAlcoholContent(dto.getAlcoholContent());
        ret.setColor(dto.getColor());
        ret.setName(dto.getName());
        ret.setPrice(dto.getPrice());
        ret.setType(dto.getType());
        ret.setIsPremium(dto.getIsPremium());
        return ret;
    }

    @Override
    public BeerDTO toDto(Beer entity) {
        if (entity == null) {
            return null;
        }
        BeerDTO ret = new BeerDTO();
        ret.setId(entity.getId());
        ret.setAlcoholContent(entity.getAlcoholContent());
        ret.setColor(entity.getColor());
        ret.setName(entity.getName());
        ret.setPrice(entity.getPrice());
        ret.setType(entity.getType());
        ret.setModDate(entity.getModDate());
        ret.setModUserId(entity.getModUserId());
        ret.setRecDate(entity.getRecDate());
        ret.setName(entity.getName());
        ret.setRecUserId(entity.getRecUserId());
        ret.setIsPremium(entity.getIsPremium());
        // ret.setRecipeDTO(recipeDao.toDto(recipeDao.findOne(entity.getRecipeId())));
        return ret;
    }

}
