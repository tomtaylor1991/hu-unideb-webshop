package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.Need;

import java.sql.Date;
import java.util.List;

/**
 * The Interface ManageIncomeFacadeService.
 */
public interface ManageIncomeFacadeService {

	/**
	 * Creates the income.
	 *
	 * @param income the income
	 */
	void createIncome(IncomeDTO income);
	
	/**
	 * Gets the day income.
	 *
	 * @param date the date
	 * @return the day income
	 */
	Integer getDayIncome(Date date);
	
	/**
	 * Gets the day income between2 date.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the day income between2 date
	 */
	List<Need> getDayIncomeBetween2Date(Date start, Date end);
	
	List<IncomeDTO> findByRecDate(Date recDate);
	
	int getAllIncome(Date date);

}
