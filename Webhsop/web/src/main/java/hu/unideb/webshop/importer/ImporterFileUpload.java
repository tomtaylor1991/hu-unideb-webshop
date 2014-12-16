package hu.unideb.webshop.importer;

import hu.unideb.webshop.MenuView;
import hu.unideb.webshop.dto.IncomeDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.fileupload.CsvConverter;
import hu.unideb.webshop.service.ManageIncomeFacadeService;
import hu.unideb.webshop.service.ManageMaterialFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.ManageWarehouseFacadeService;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ViewScoped
@ManagedBean(name = "importerFileUpload")
public class ImporterFileUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{manageMaterialFacadeService}")
    private ManageMaterialFacadeService manageMaterialFacadeService;
    @ManagedProperty(value = "#{manageWarehouseFacadeService}")
    private ManageWarehouseFacadeService manageWarehouseFacadeService;
    @ManagedProperty(value = "#{manageRegistryFacadeService}")
    private ManageRegistryFacadeService manageRegistryFacadeService;
    @ManagedProperty(value = "#{manageIncomeFacadeService}")
    private ManageIncomeFacadeService manageIncomeFacadeService;

    List<MaterialDTO> allMaterial;
    ArrayList<String[]> latestUpload;
    ArrayList<String[]> latestUploadToView;
    private String comment;
    private Date date;
    List<WarehouseDTO> allWH;
    WarehouseDTO selectedWH = null;
    private boolean isShowChooseButton = true;

    @PostConstruct
    public void initImporter() {
        allMaterial = manageMaterialFacadeService.getAllMaterialList();
        allWH = manageWarehouseFacadeService.getWarehouseList(0, 1000);
    }

    private String getMaterialNameById(int id) {
        if (allMaterial != null) {
            for (MaterialDTO material : allMaterial) {
                if (material.getId() == id) {
                    return material.getMaterialName();
                }
            }
        }
        return "";
    }

    private MaterialDTO getMaterialById(int id) {
        if (allMaterial != null) {
            for (MaterialDTO material : allMaterial) {
                if (material.getId() == id) {
                    return material;
                }
            }
        }
        return null;
    }

    private void generateUploadedFileListToView() {
        ArrayList<Integer> clearIDs = new ArrayList<Integer>();
        if (latestUpload != null && latestUpload.size() > 0) {
            for (int i = 0; i < latestUpload.size(); i++) {
                String[] line = latestUpload.get(i);
                // kulcsszo kerese
                if (line[0].startsWith("#")) {
                    clearIDs.add(i);
                    generateContent(line);
                } else {
                    String materialName = getMaterialNameById(Integer
                            .valueOf(line[0]));
                    String quantity = line[1];
                    String[] a = new String[2];
                    a[0] = materialName;
                    a[1] = quantity;
                    latestUploadToView.add(a);
                }
            }
            try {
                for (int j : clearIDs) {
                    latestUpload.remove(j);
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }
    }

    private void generateContent(String[] content) {
        if (content.length != 2) {
            return;
        }
        if (content[0].equals("#comment")) {
            comment = content[1];
        }
        if (content[0].equals("#date")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = dateFormat.parse(content[1]);
            } catch (ParseException e) {
                date = Calendar.getInstance().getTime();
            }
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile()
                .getFileName() + " is uploaded for processing.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        if (event.getFile() != null) {
            try {
                latestUpload = null;
                ArrayList<String[]> ret = CsvConverter.processCSV(
                        event.getFile(), ",");
                // oszlop feliratok eltavolitasa
                if (ret.size() > 0) {
                    ret.remove(0);
                    latestUpload = ret;
                    latestUploadToView = new ArrayList<String[]>();
                    generateUploadedFileListToView();
                    isShowChooseButton = false;
                }
                // CsvConverter.testCSVPrint(ret);
            } catch (IOException e) {

            }
        }
    }

    public void clear() {
        latestUpload = null;
        latestUploadToView = null;
        comment = null;
        date = null;
        selectedWH = null;
        isShowChooseButton = true;
    }

    public void save() {
    	/*
        if (selectedWH == null || latestUpload == null
                || latestUpload.size() == 0) {

            MenuView.addMessage("Please select a WH!");
        } else {
            List<RegistryDTO> list = new ArrayList<RegistryDTO>();
            for (String[] s : latestUpload) {
                if (s[0].startsWith("#")) {
                    continue;
                }
                RegistryDTO r = new RegistryDTO();
                r.setMaterial(getMaterialById(Integer.parseInt(s[0])));
                r.setQuantity(Integer.parseInt(s[1]));
                r.setWarehouse(selectedWH);
                r.setStatus("FREE");
                IncomeDTO incomeDTO = new IncomeDTO();
                incomeDTO.setOrderId(new Long(0));
                incomeDTO.setPrice((int) manageRegistryFacadeService
                        .costOfMaterial(r));
                incomeDTO.setComment(r.getMaterial().getMaterialName() + ","
                        + r.getQuantity());
                manageIncomeFacadeService.createIncome(incomeDTO);
                list.add(r);
            }
            manageRegistryFacadeService.saveRegistrys(list);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                            "Success material loading to the following warehouse: "
                            + selectedWH.getName()));
            clear();
        }*/
    }

    public void changeChooseButtonStatus() {
        isShowChooseButton = !isShowChooseButton;
    }

    public boolean getIsShowChooseButton() {
        return isShowChooseButton;
    }

    public void setShowChooseButton(boolean isShowChooseButton) {
        this.isShowChooseButton = isShowChooseButton;
    }

    public ArrayList<String[]> getLatestUpload() {
        return latestUpload;
    }

    public void setLatestUpload(ArrayList<String[]> latestUpload) {
        this.latestUpload = latestUpload;
    }

    public ManageMaterialFacadeService getManageMaterialFacadeService() {
        return manageMaterialFacadeService;
    }

    public void setManageMaterialFacadeService(
            ManageMaterialFacadeService manageMaterialFacadeService) {
        this.manageMaterialFacadeService = manageMaterialFacadeService;
    }

    public List<MaterialDTO> getAllMaterial() {
        return allMaterial;
    }

    public void setAllMaterial(List<MaterialDTO> allMaterial) {
        this.allMaterial = allMaterial;
    }

    public ArrayList<String[]> getLatestUploadToView() {
        return latestUploadToView;
    }

    public void setLatestUploadToView(ArrayList<String[]> latestUploadToView) {
        this.latestUploadToView = latestUploadToView;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<WarehouseDTO> getAllWH() {
        return allWH;
    }

    public void setAllWH(List<WarehouseDTO> allWH) {
        this.allWH = allWH;
    }

    public ManageWarehouseFacadeService getManageWarehouseFacadeService() {
        return manageWarehouseFacadeService;
    }

    public void setManageWarehouseFacadeService(
            ManageWarehouseFacadeService manageWarehouseFacadeService) {
        this.manageWarehouseFacadeService = manageWarehouseFacadeService;
    }

    public WarehouseDTO getSelectedWH() {
        return selectedWH;
    }

    public void setSelectedWH(WarehouseDTO selectedWH) {
        this.selectedWH = selectedWH;
    }

    public ManageRegistryFacadeService getManageRegistryFacadeService() {
        return manageRegistryFacadeService;
    }

    public void setManageRegistryFacadeService(
            ManageRegistryFacadeService manageRegistryFacadeService) {
        this.manageRegistryFacadeService = manageRegistryFacadeService;
    }

    public ManageIncomeFacadeService getManageIncomeFacadeService() {
        return manageIncomeFacadeService;
    }

    public void setManageIncomeFacadeService(
            ManageIncomeFacadeService manageIncomeFacadeService) {
        this.manageIncomeFacadeService = manageIncomeFacadeService;
    }

}
