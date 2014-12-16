package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.service.IncomeService;
import hu.unideb.webshop.service.ManageIncomeFacadeService;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageIncomeFacadeService")
public class ManageIncomeFacadeServiceImpl implements ManageIncomeFacadeService{

	@Autowired
	IncomeService incomeService;
	
	@Override
	public void createIncome(IncomeDTO income) {
		incomeService.createIncome(income);		
	}

	@Override
	public Integer getDayIncome(Date date) {
		
		return incomeService.getDayIncome(date);
	}

	@Override
	public List<Need> getDayIncomeBetween2Date(Date start, Date end) {
		
		return incomeService.getDayIncomeBetween2Date(start, end);
	}

	@Override
	public List<IncomeDTO> findByRecDate(Date recDate) {
		
		return incomeService.findByRecDate(recDate);
	}

	@Override
	public int getAllIncome(Date date) {
		return incomeService.getAllIncome(date);
	}

}
