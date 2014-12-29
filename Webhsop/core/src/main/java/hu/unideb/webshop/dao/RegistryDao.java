package hu.unideb.webshop.dao;

import java.util.List;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.entity.Registry;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryDao extends
		PagingAndSortingRepository<Registry, Long>,
		BaseConvertDao<Registry, RegistryDTO> {

	List<Registry> findByOrderAndStatus(Order order, String status);
	
	List<Registry> findByOrderAndProductAndStatus(Order order, Product product,String status);

	@Query(value = "SELECT sum(r.quantity) FROM REGISTRY r where product_id = ?1 and status=?2", nativeQuery = true)
	Integer countByProductIdAndStatus(Long product_id, String status);

}
