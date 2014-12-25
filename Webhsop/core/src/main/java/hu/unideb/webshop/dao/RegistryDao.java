package hu.unideb.webshop.dao;

import java.util.List;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.entity.Registry;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryDao extends
		PagingAndSortingRepository<Registry, Long>,
		BaseConvertDao<Registry, RegistryDTO> {

	List<Registry> findByOrderAndStatus(Order order, String status);
}
