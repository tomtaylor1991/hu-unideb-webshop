package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.Status;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.service.ManageOrderFacadeService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

@ViewScoped
@ManagedBean(name = "statuses")
public class Statuses implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	private List<Need> data;
	private PieChartModel pieModel1;

	@PostConstruct
	public void init() {
		update();
	}

	public void update() {
		data = manageOrderFacadeService.getOrderStatusStatics();
		pieModel1 = new PieChartModel();
		for (Need currentNeed : data) {
			if(currentNeed.getName().equals("DONE")){
				currentNeed.setName(LocaleSwitcher.getMessage("done"));
				continue;
			}
			currentNeed.setName(getOrderStatus(currentNeed.getName()));
			pieModel1.set(
					currentNeed.getName() + " ( " + currentNeed.getQuantity()
							+ " )", currentNeed.getQuantity());
		}

		pieModel1.setTitle(LocaleSwitcher.getMessage("statuses"));
		pieModel1.setLegendPosition("w");
		pieModel1.setShowDataLabels(true);
	}

	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	public List<Need> getData() {
		return data;
	}

	public void setData(List<Need> data) {
		this.data = data;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
	
	public String getOrderStatus(String key) {
		try {
			//System.out.println("!!!!!!!!!!!!" + key);
			String s = Status.getByKey(key).toString();
			return s;
		} catch (NullPointerException e) {
			return "undefined status";
		}

	}

}
