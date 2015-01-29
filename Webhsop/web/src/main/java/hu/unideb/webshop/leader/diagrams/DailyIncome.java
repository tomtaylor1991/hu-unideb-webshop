package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.service.ManageIncomeFacadeService;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "dailyIncome")
public class DailyIncome implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageIncomeFacadeService}")
	private ManageIncomeFacadeService manageIncomeFacadeService;

	private List<Data> in;
	private List<Data> out;
	private Date date;
	private int allMoney;
	private int money;

	@PostConstruct
	public void init() {
		date = new Date(Calendar.getInstance().getTimeInMillis());
		update();
	}

	public void update() {
		allMoney = 0;
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		money = manageIncomeFacadeService.getAllIncome(sqlDate);
		List<IncomeDTO> incomes = manageIncomeFacadeService
				.findByRecDate(sqlDate);
		in = new LinkedList<Data>();
		out = new LinkedList<Data>();
		for (IncomeDTO currentIncome : incomes) {
			Data data = new Data(currentIncome.getName(),
					currentIncome.getQuantity(), currentIncome.getPrice());
			allMoney += currentIncome.getPrice();
			if (currentIncome.getType().equals("IN")) {
				in.add(data);
			} else {
				out.add(data);
			}
		}
	}

	public ManageIncomeFacadeService getManageIncomeFacadeService() {
		return manageIncomeFacadeService;
	}

	public void setManageIncomeFacadeService(
			ManageIncomeFacadeService manageIncomeFacadeService) {
		this.manageIncomeFacadeService = manageIncomeFacadeService;
	}

	public List<Data> getIn() {
		return in;
	}

	public void setIn(List<Data> in) {
		this.in = in;
	}

	public List<Data> getOut() {
		return out;
	}

	public void setOut(List<Data> out) {
		this.out = out;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static class Data {

		private String name;
		private int quantity;
		private int cost;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public Data(String name, int quantity, int cost) {
			this.name = name;
			this.quantity = quantity;
			this.cost = cost;
		}

	}

	public int getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(int allMoney) {
		this.allMoney = allMoney;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}
