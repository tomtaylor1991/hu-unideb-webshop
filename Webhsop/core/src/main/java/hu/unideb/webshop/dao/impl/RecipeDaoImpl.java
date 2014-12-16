package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.entity.Recipe;

class RecipeDaoImpl implements BaseConvertDao<Recipe, RecipeDTO> {

    public RecipeDTO toDto(Recipe entity) {
        RecipeDTO ret = new RecipeDTO();
        ret.setCost(entity.getCost());
        ret.setId(entity.getId());
        ret.setModDate(entity.getModDate());
        ret.setModUserId(entity.getModUserId());
        ret.setRecDate(entity.getRecDate());
        ret.setName(entity.getRecipeName());
        ret.setRecUserId(entity.getRecUserId());
        ret.setComment(entity.getComment());

        return ret;
    }

    public Recipe toEntity(RecipeDTO dto, Recipe entity) {
        Recipe ret = entity;
        if (dto.getId() == null || entity == null) {
            ret = new Recipe();
            ret.setId(dto.getId());
            ret.setRecDate(dto.getRecDate());
            ret.setRecUserId(dto.getRecUserId());
        }
        ret.setModDate(dto.getModDate());
        ret.setModUserId(dto.getModUserId());
        ret.setRecipeName(dto.getName());
        ret.setCost(dto.getCost());
        ret.setComment(dto.getComment());

        return ret;
    }

}
