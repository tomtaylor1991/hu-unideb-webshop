package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.entity.Beer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerDao extends PagingAndSortingRepository<Beer, Long>,
	BaseConvertDao<Beer, BeerDTO>{

}
