package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.entity.Registry;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryDao extends
		PagingAndSortingRepository<Registry, Long>,
		BaseConvertDao<Registry, RegistryDTO> {


}
