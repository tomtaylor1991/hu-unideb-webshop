package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.entity.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends PagingAndSortingRepository<Order, Long>,
	BaseConvertDao<Order, OrderDTO>{
	
	Page<Order> findByStatusStartsWith(String status, Pageable pageable);
	Page<Order> findByNameStartsWith(String name, Pageable pageable);
	
	Page<Order> findByNameStartsWithAndStatusIn(String name, List<String> statuses, Pageable pageable);
	Page<Order> findByStatusStartsWithAndStatusIn(String status, List<String> statuses, Pageable pageable);
	Page<Order> findByStatusIn(List<String> statuses, Pageable pageable);


	@Query(value = "SELECT count(*) FROM ORDERS", nativeQuery = true)
	int countById();
	
	@Query(value = "SELECT count(*) FROM ORDERS", nativeQuery = true)
    int countRowNum();
	
	List<Order> findByStatus(String status);
	List<Order> findByPartnerId(Long partnerId);
	
	Integer countByStatus(String status);
}
