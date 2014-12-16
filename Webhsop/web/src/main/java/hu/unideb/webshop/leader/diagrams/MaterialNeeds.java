package hu.unideb.webshop.leader.diagrams;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.importer.ImporterNecessaryMaterials.MaterialNeed;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ViewScoped
@ManagedBean(name = "MaterialNeedsChartView")
public class MaterialNeeds implements Serializable {

    private static final long serialVersionUID = 1L;
    private int max = 0;
    @ManagedProperty(value = "#{manageRegistryFacadeService}")
    private ManageRegistryFacadeService manageRegistryFacadeService;

    @ManagedProperty(value = "#{manageOrderFacadeService}")
    private ManageOrderFacadeService manageOrderFacadeService;

    private BarChartModel barModel;
    private List<MaterialNeed> materialNeeds = new ArrayList<MaterialNeed>();
    private List<MaterialNeed> materialInWh = new ArrayList<MaterialNeed>();

    @PostConstruct
    public void init() {
        createBarModels();
    }

    private void createBarModels() {
        barModel = initBarModel();

        barModel.setTitle(LocaleSwitcher.getMessage("material_diagram_logo"));
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel(LocaleSwitcher.getMessage("materials"));

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel(LocaleSwitcher.getMessage("quantity"));
        yAxis.setMin(0);
        yAxis.setMax(max);

    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        calculateMaterialNeeds();

        ChartSeries needs = new ChartSeries();
        needs.setLabel(LocaleSwitcher.getMessage("need"));
        if (materialNeeds.size() == 0) {
            needs.set("0", 0);
        }
        for (MaterialNeed m : materialNeeds) {
            needs.set(m.getMaterialName(), m.getQuantity());
            if (m.getQuantity() > max) {
                max = m.getQuantity();
            }
        }

        ChartSeries inWh = new ChartSeries();
        inWh.setLabel(LocaleSwitcher.getMessage("in_wh"));
        if (materialInWh.size() == 0) {
            inWh.set("0", 0);
        }
        for (MaterialNeed m : materialInWh) {
            inWh.set(m.getMaterialName(), m.getQuantity());
            if (m.getQuantity() > max) {
                max = m.getQuantity();
            }
        }

        model.addSeries(needs);
        model.addSeries(inWh);

        return model;
    }

    public void calculateMaterialNeeds() {

        Map<String, Integer> inWarehouses = new HashMap<String, Integer>();
        Map<String, Integer> needForOrders = new HashMap<String, Integer>();

        for (OrderDTO order : manageOrderFacadeService
                .getOrdersByStatus("NEEDMATERIAL")) {
            List<LeaderTestInfoDTO.Need> materialsNeeded = manageRegistryFacadeService
                    .isOrderCanBeServe(order).getNeeded();
            for (LeaderTestInfoDTO.Need need : materialsNeeded) {
                if (needForOrders.containsKey(need.getMaterial()
                        .getMaterialName())) {
                    int value = needForOrders.get(need.getMaterial()
                            .getMaterialName());
                    needForOrders.put(need.getMaterial().getMaterialName(),
                            value + need.getNeedQuantity());
                } else {
                    inWarehouses.put(need.getMaterial().getMaterialName(),
                            need.getInWhQuantity());
                    needForOrders.put(need.getMaterial().getMaterialName(),
                            need.getNeedQuantity());
                }
            }
        }

        for (Map.Entry<String, Integer> entry : needForOrders.entrySet()) {
            if (entry.getValue() > inWarehouses.get(entry.getKey())) {
                materialNeeds.add(new MaterialNeed(entry.getKey(), entry
                        .getValue() - inWarehouses.get(entry.getKey())));
            }
            materialInWh.add(new MaterialNeed(entry.getKey(), inWarehouses
                    .get(entry.getKey())));
        }
    }

    public List<MaterialNeed> getMaterialNeeds() {
        return materialNeeds;
    }

    public void setMaterialNeeds(List<MaterialNeed> materialNeeds) {
        this.materialNeeds = materialNeeds;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

    public ManageOrderFacadeService getManageOrderFacadeService() {
        return manageOrderFacadeService;
    }

    public void setManageOrderFacadeService(
            ManageOrderFacadeService manageOrderFacadeService) {
        this.manageOrderFacadeService = manageOrderFacadeService;
    }

    public List<MaterialNeed> getMaterialInWh() {
        return materialInWh;
    }

    public void setMaterialInWh(List<MaterialNeed> materialInWh) {
        this.materialInWh = materialInWh;
    }

}
