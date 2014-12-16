package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.service.ManageIncomeFacadeService;
import hu.unideb.webshop.service.ManageOrderFacadeService;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

@ViewScoped
@ManagedBean(name = "partnerOrder")
public class PartnerOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageOrderFacadeService}")
    private ManageOrderFacadeService manageOrderFacadeService;

    @ManagedProperty(value = "#{manageIncomeFacadeService}")
    private ManageIncomeFacadeService manageIncomeFacadeService;

    private HorizontalBarChartModel horizontalBarModel;
    private java.util.Date startDay = new Date(Calendar.getInstance()
            .getTimeInMillis());
    private java.util.Date endDay = new Date(Calendar.getInstance()
            .getTimeInMillis());

    @PostConstruct
    public void init() {

        refreshChart();
    }

    public ManageIncomeFacadeService getManageIncomeFacadeService() {
        return manageIncomeFacadeService;
    }

    public void setManageIncomeFacadeService(
            ManageIncomeFacadeService manageIncomeFacadeService) {
        this.manageIncomeFacadeService = manageIncomeFacadeService;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
        this.horizontalBarModel = horizontalBarModel;
    }

    public void refreshChart() {
        createLineModels();
    }

    public void createLineModels() {
        if (startDay != null && endDay != null) {
            this.horizontalBarModel = new HorizontalBarChartModel();
            // /
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDay);
            Date s = new Date(cal.getTime().getTime());
            cal.setTime(endDay);
            Date e = new Date(cal.getTime().getTime());
            List<Need> data = manageOrderFacadeService.getPartnerOrderStatics(
                    s, e);

            ChartSeries days = new ChartSeries();
            days.setLabel(LocaleSwitcher.getMessage("beers"));
            // days.setFill(true);
            if (data.size() == 0) {
                days.set("", 0);
            } else {
                Collections.sort(data);
            }
            int max = 0;
            for (Need day : data) {
                days.set(day.getName() + " ( " + day.getQuantity() + " )",
                        day.getQuantity());
                if (day.getQuantity() > max) {
                    max = day.getQuantity();
                }
            }
            // /
            horizontalBarModel.addSeries(days);

            horizontalBarModel.setTitle(LocaleSwitcher.getMessage("partner_o"));
            horizontalBarModel.setLegendPosition("e");
            horizontalBarModel.setStacked(true);

            Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
            xAxis.setLabel(LocaleSwitcher.getMessage("partner"));
            xAxis.setMin(0);
            xAxis.setMax(max);

            Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
            yAxis.setLabel(LocaleSwitcher.getMessage("order_number"));
        }
    }

    public java.util.Date getStartDay() {
        return startDay;
    }

    public void setStartDay(java.util.Date startDay) {
        this.startDay = startDay;
    }

    public java.util.Date getEndDay() {
        return endDay;
    }

    public void setEndDay(java.util.Date endDay) {
        this.endDay = endDay;
    }

    public ManageOrderFacadeService getManageOrderFacadeService() {
        return manageOrderFacadeService;
    }

    public void setManageOrderFacadeService(
            ManageOrderFacadeService manageOrderFacadeService) {
        this.manageOrderFacadeService = manageOrderFacadeService;
    }

}
