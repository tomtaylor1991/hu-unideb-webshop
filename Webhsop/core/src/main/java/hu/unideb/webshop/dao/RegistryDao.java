package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.entity.Beer;
import hu.unideb.webshop.entity.Material;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.entity.Registry;
import hu.unideb.webshop.entity.Warehouse;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryDao extends
		PagingAndSortingRepository<Registry, Long>,
		BaseConvertDao<Registry, RegistryDTO> {

	List<Registry> findByWarehouseAndMaterialNotNull(Warehouse warehouse);
	
	List<Registry> findByWarehouseAndMaterialNotNullAndStatus(Warehouse warehouse, String status);

	List<Registry> findByWarehouseAndBeerNotNull(Warehouse warehouse);

	List<Registry> findByMaterial(Material material);

	List<Registry> findByStatus(String status);

	List<Registry> findByStatusAndOrder(String status, Order order);
	
	List<Registry> findByStatusAndBeerAndOrder(String status, Beer beer,
			Order order);

	List<Registry> findByStatusAndMaterialAndOrder(String status,
			Material material, Order order);

	Page<Registry> findByStatus(String status, Pageable pageable);

	Page<Registry> findByStatusStartsWith(String status, Pageable pageable);

	// List<Registry> findByOrder(Order order);

	List<Registry> findByOrder(Order order);

	Page<Registry> findByMaterialMaterialNameAndWarehouse(String materialName,
			Warehouse warehouse, Pageable pageable);

	@Query(value = "SELECT sum(r.quantity) FROM REGISTRY r where material_id = ?1 and status='FREE' group by material_id", nativeQuery = true)
	Integer sumByQuantity(Long materialId);

	@Query(value = "SELECT sum(r.quantity) FROM REGISTRY r where r.beer_id = ?1 and (r.order_id is null or r.status='READY') ", nativeQuery = true)
	Integer sumBeersByQuantityAndOrderIsNull(Long beerId);

	@Query(value = "SELECT sum(r.quantity) FROM REGISTRY r where r.beer_id = ?1 and (r.order_id is not null and r.status='NEED') ", nativeQuery = true)
	Integer sumBeersByQuantityAndOrderIsNotNull(Long beerId);

	@Query(value = "SELECT count(*) FROM REGISTRY r where r.order_id = ?1 and r.quantity>0 and status is null", nativeQuery = true)
	Integer isOrderReady(Long orderId);

	// NEEED számlálás
	@Query(value = "SELECT count(*) FROM REGISTRY r where r.order_id = ?1 and beer_id is not null and status='Need'", nativeQuery = true)
	Integer isOrderReadyByBeers(Long orderId);

	@Query(value = "SELECT sum(r.originalQuantity) FROM REGISTRY r inner join ORDERS o on r.order_id=o.id where o.recDate BETWEEN ?2 AND ?3 and r.status is null and r.order_id in (SELECT id FROM ORDERS WHERE partnerId=?1)", nativeQuery = true)
	Integer getOrderNumByPartner(Long partnerId, Date start, Date end);

}
