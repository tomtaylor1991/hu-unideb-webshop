package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long>,
		BaseConvertDao<Product, ProductDTO> {

	Page<Product> findByNameStartsWith(String name, Pageable pageable);

	Page<Product> findByCategoryNameStartsWith(String name, Pageable pageable);

	@Query(value = "SELECT count(*) FROM PRODUCT", nativeQuery = true)
	int countRowNum();

	List<Product> findByNameStartsWith(String name);
	
	@Query(value = "SELECT count(*) FROM PRODUCT WHERE category_id = ?1", nativeQuery = true)
    int countCategoryProductNumber(Long categoryId);

}
