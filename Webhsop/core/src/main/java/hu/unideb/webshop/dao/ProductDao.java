package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.Product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long>,
		BaseConvertDao<Product, ProductDTO> {

}
