package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.service.ManageIncomeFacadeService;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ViewScoped
@ManagedBean(name = "incomeController")
public class Income implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageIncomeFacadeService}")
    private ManageIncomeFacadeService manageIncomeFacadeService;

    private int todayIncome;
    private LineChartModel areaModel = new LineChartModel();
    private BarChartModel barModel = new BarChartModel();
    private boolean isLineChart = true;
    private java.util.Date startDay = new Date(Calendar.getInstance()
            .getTimeInMillis());
    private java.util.Date endDay = new Date(Calendar.getInstance()
            .getTimeInMillis());

    @PostConstruct
    public void init() {
        calculateIncomes();
        refreshChart();
    }

    public void calculateIncomes() {
        todayIncome = manageIncomeFacadeService.getDayIncome(new Date(Calendar
                .getInstance().getTimeInMillis()));

    }

    public ManageIncomeFacadeService getManageIncomeFacadeService() {
        return manageIncomeFacadeService;
    }

    public void setManageIncomeFacadeService(
            ManageIncomeFacadeService manageIncomeFacadeService) {
        this.manageIncomeFacadeService = manageIncomeFacadeService;
    }

    public int getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(int todayIncome) {
        this.todayIncome = todayIncome;
    }

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(LineChartModel areaModel) {
        this.areaModel = areaModel;
    }

    public void refreshChart() {
        if (isLineChart) {
            createLineModels();
        } else {
            createBarModels();
        }
    }

    public void createLineModels() {
        if (startDay != null && endDay != null) {
            //System.out.println("Start " + isLineChart);
            this.areaModel = new LineChartModel();
            areaModel.setZoom(true);
            // /
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDay);
            Date s = new Date(cal.getTime().getTime());
            cal.setTime(endDay);
            Date e = new Date(cal.getTime().getTime());
            List<Need> data = manageIncomeFacadeService
                    .getDayIncomeBetween2Date(s, e);
            ChartSeries days = new LineChartSeries();
            days.setLabel(LocaleSwitcher.getMessage("income"));
            // days.setFill(true);
            if (data.size() == 0) {
                days.set("", 0);
            }
            int max = 0;
            int min = 0;
            for (Need day : data) {
                // System.out.println("Add: " + day);
                days.set(day.getName(), day.getQuantity());
                if (day.getQuantity() > max) {
                    max = day.getQuantity();
                }
                if (day.getQuantity() < min) {
                    min = day.getQuantity();
                }
            }
            if (min > 0) {
                min = 0;
            }
            //System.out.println("MIN: " + min + " Max: " + max);
            areaModel.addSeries(days);

            areaModel.setTitle(LocaleSwitcher.getMessage("income_chart"));
            areaModel.setLegendPosition("ne");
            areaModel.setStacked(true);
            areaModel.setShowPointLabels(true);

            Axis xAxis = new CategoryAxis(LocaleSwitcher.getMessage("days"));
            xAxis.setTickAngle(-60);
            areaModel.getAxes().put(AxisType.X, xAxis);
            Axis yAxis = areaModel.getAxis(AxisType.Y);
            yAxis.setLabel(LocaleSwitcher.getMessage("income"));
            yAxis.setMin(min);
            yAxis.setMax(max);
            //System.out.println(data);
        }
    }

    public void createBarModels() {
        if (startDay != null && endDay != null) {
            //System.out.println("Start " + isLineChart);
            this.barModel = new BarChartModel();
            barModel.setZoom(true);
            // /
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDay);
            Date s = new Date(cal.getTime().getTime());
            cal.setTime(endDay);
            Date e = new Date(cal.getTime().getTime());
            List<Need> data = manageIncomeFacadeService
                    .getDayIncomeBetween2Date(s, e);
            ChartSeries days = new ChartSeries();
            // days.setFill(true);
            if (data.size() == 0) {
                days.set("", 0);
            }
            int max = 0;
            for (Need day : data) {
                // System.out.println("Add: " + day);
                days.set(day.getName(), day.getQuantity());
                if (day.getQuantity() > max) {
                    max = day.getQuantity();
                }
            }
            // /
            barModel.addSeries(days);

            barModel.setTitle(LocaleSwitcher.getMessage("income_chart"));
            barModel.setLegendPosition("ne");
            barModel.setStacked(true);
            barModel.setShowPointLabels(true);

            Axis xAxis = new CategoryAxis(LocaleSwitcher.getMessage("days"));
            barModel.getAxes().put(AxisType.X, xAxis);
            xAxis.setTickAngle(-60);
            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel(LocaleSwitcher.getMessage("income"));
            yAxis.setMin(0);
            yAxis.setMax(max);
            //System.out.println(barModel);
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

    public boolean getIsLineChart() {
        return isLineChart;
    }

    public void setIsLineChart(boolean isLineChart) {
        this.isLineChart = isLineChart;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
