package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.entity.Component;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentDao extends PagingAndSortingRepository<Component, Long>,
        BaseConvertDao<Component, ComponentDTO> {

    List<Component> findByRecipeId(Long recipeId);
}
