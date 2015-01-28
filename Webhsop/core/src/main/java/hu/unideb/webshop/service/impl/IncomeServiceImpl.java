package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.IncomeDao;
import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.entity.Income;
import hu.unideb.webshop.service.IncomeService;
import hu.unideb.webshop.service.UserService;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	UserService userService;
	
	@Autowired
	IncomeDao incomeDao;

	List<Date> getDatesBetween2Date(Date str_date, Date end_date)
			throws ParseException {
		List<Date> dates = new ArrayList<Date>(25);
		Calendar cal = Calendar.getInstance();
		cal.setTime(str_date);
		while (cal.getTime().before(end_date)) {
			cal.add(Calendar.DATE, 1);
			dates.add(new Date(cal.getTime().getTime()));
		}
		return dates;
	}

	
	@Override
	public void createIncome(IncomeDTO income) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			income.setRecUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			income.setRecDate(new java.util.Date());
			income.setModUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			income.setModDate(new java.util.Date());
		}
		incomeDao.save(incomeDao.toEntity(income, null));

	}

	@Override
	public Integer getDayIncome(Date date) {
		Integer income = incomeDao.getAllIncome(date);
		return income == null ? 0 : income;
	}

	@Override
	public List<Need> getDayIncomeBetween2Date(Date start, Date end) {
		List<Need> ret = new LinkedList<Need>();
		try {
			// System.out.println(getDatesBetween2Date(start, end));
			List<Date> dates = getDatesBetween2Date(start, end);
			for (Date d : dates) {
				//Integer tmp = incomeDao.getDayIncome(d);
				Integer tmp = incomeDao.getAllIncome(d);
				int n = tmp == null ? 0 : tmp;
				ret.add(new Need(d.toString(), n));
			}
		} catch (ParseException e) {
			return ret;
		}
		return ret;
	}


	@Override
	public List<IncomeDTO> findByRecDate(Date recDate) {
		List<IncomeDTO> ret = new LinkedList<IncomeDTO>();
		List<Income> entities = incomeDao.findByRecDate(recDate);
		for(Income entity: entities){
			ret.add(incomeDao.toDto(entity));
		}
		//System.out.println("DAILY INCOME: " + ret);
		return ret;
	}


	@Override
	public int getAllIncome(Date date) {
		Integer income = incomeDao.getAllIncome(date);
		return income == null ? 0 : income;
	}

}
