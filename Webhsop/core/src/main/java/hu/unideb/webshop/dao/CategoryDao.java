package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.entity.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends
		PagingAndSortingRepository<Category, Long>,
		BaseConvertDao<Category, CategoryDTO> {

	Page<Category> findByNameStartsWith(String name, Pageable pageable);
	
	Page<Category> findByParentNameStartsWith(String parentName, Pageable pageable);

	@Query(value = "SELECT count(*) FROM CATEGORY", nativeQuery = true)
    int countRowNum();

}
