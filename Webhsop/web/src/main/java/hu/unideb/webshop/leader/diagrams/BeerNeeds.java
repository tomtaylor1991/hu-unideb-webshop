package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.service.ManageBeerFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

@ViewScoped
@ManagedBean(name = "beerNeeds")
public class BeerNeeds implements Serializable {

	private static final long serialVersionUID = 1L;

	private HorizontalBarChartModel horizontalBarModel;

	@ManagedProperty(value = "#{manageBeerFacadeService}")
	private ManageBeerFacadeService manageBeerFacadeService;

	public ManageBeerFacadeService getManageBeerFacadeService() {
		return manageBeerFacadeService;
	}

	public void setManageBeerFacadeService(
			ManageBeerFacadeService manageBeerFacadeService) {
		this.manageBeerFacadeService = manageBeerFacadeService;
	}

	@PostConstruct
	public void init() {
		createBarModels();
	}

	private void createBarModels() {
		horizontalBarModel = new HorizontalBarChartModel();

		int max = 0;
		ChartSeries inwh = new ChartSeries();
		inwh.setLabel(LocaleSwitcher.getMessage("in_wh"));
		if (manageBeerFacadeService.getInWHBeersStatics().size() == 0) {
			inwh.set("0", 0);
		}
		for (Need n : manageBeerFacadeService.getInWHBeersStatics()) {
			inwh.set(n.getName(), n.getQuantity());
			if (n.getQuantity() > max) {
				max = n.getQuantity();
			}
		}

		ChartSeries beers = new ChartSeries();
		beers.setLabel(LocaleSwitcher.getMessage("need"));
		if (manageBeerFacadeService.getNeededBeersStatics().size() == 0) {
			beers.set("0", 0);
		}
		for (Need n : manageBeerFacadeService.getNeededBeersStatics()) {
			beers.set(n.getName(), n.getQuantity());
			if (n.getQuantity() > max) {
				max = n.getQuantity();
			}
		}

		horizontalBarModel.addSeries(inwh);
		horizontalBarModel.addSeries(beers);

		horizontalBarModel.setTitle(LocaleSwitcher.getMessage("beer_statics"));
		horizontalBarModel.setLegendPosition("e");
		horizontalBarModel.setStacked(true);

		Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
		xAxis.setLabel(LocaleSwitcher.getMessage("quantity"));
		xAxis.setMin(0);
		xAxis.setMax(max + 100);

		Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
		yAxis.setLabel(LocaleSwitcher.getMessage("beer"));
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
		this.horizontalBarModel = horizontalBarModel;
	}

}
