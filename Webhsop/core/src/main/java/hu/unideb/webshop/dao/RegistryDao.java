package hu.unideb.webshop.dao;

import java.sql.Date;
import java.util.List;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.entity.Product;
import hu.unideb.webshop.entity.Registry;
import hu.unideb.webshop.entity.Warehouse;

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
    @Query(value = "SELECT sum(r.originalQuantity) FROM REGISTRY r inner join ORDERS o on r.order_id=o.id where r.recDate BETWEEN ?2 AND ?3 and r.status = 'ORDERDATA' and r.order_id in (SELECT id FROM ORDERS WHERE partnerId=?1)", nativeQuery = true)
    Integer getOrderNumByPartner(Long partnerId, Date start, Date end);
    
    List<Registry> findByWarehouse(Warehouse warehouse);

}
