package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.entity.Partner;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartnerDao extends PagingAndSortingRepository<Partner, Long>,
		BaseConvertDao<Partner, PartnerDTO>{

	Partner findPartnerByName(String name);
}
