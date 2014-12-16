package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseDao extends PagingAndSortingRepository<Warehouse, Long>, 
		BaseConvertDao<Warehouse, WarehouseDTO> {

	Page<Warehouse> findByNameStartsWith(String name, Pageable pageable);
	Page<Warehouse> findByAddressContaining(String address, Pageable pageable);

	@Query(value = "SELECT count(*) FROM WAREHOUSE", nativeQuery = true)
	int countById();
	
}
