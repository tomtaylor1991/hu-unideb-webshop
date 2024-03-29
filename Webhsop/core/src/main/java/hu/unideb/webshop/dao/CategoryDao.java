package hu.unideb.webshop.dao;

import java.util.List;

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
	
	@Query(value = "SELECT count(*) FROM CATEGORY WHERE parent_category_id = ?1", nativeQuery = true)
    int countChildNumber(Long categoryId);
	
	List<Category> findByNameStartsWithAndParentIsNull(String name);
	List<Category> findByNameStartsWith(String name);
	List<Category> findByParent(Category parent);
	
	Category findById(Long id);
}
