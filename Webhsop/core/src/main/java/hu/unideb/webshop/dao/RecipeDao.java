package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.entity.Recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDao extends PagingAndSortingRepository<Recipe, Long>,
		BaseConvertDao<Recipe, RecipeDTO> {

	Page<Recipe> findByRecipeNameStartsWith(String recipeName, Pageable pageable);

	@Query(value = "SELECT count(*) FROM RECIPE", nativeQuery = true)
	int countById();
	

}
