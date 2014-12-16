package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.entity.Income;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeDao extends PagingAndSortingRepository<Income, Long>,
	BaseConvertDao<Income, IncomeDTO>{

	@Query(value = "SELECT sum(price) FROM INCOME WHERE DATE(recDate) = ?1 ", nativeQuery = true)
	Integer getDayIncome(Date date);
	
	@Query("select i from Income i where DATE(i.recDate) = ?1")
	List<Income> findByRecDate(Date recDate);
	
	@Query(value = "SELECT sum(price) FROM INCOME WHERE DATE(recDate) <= ?1 ", nativeQuery = true)
	Integer getAllIncome(Date date);
}
