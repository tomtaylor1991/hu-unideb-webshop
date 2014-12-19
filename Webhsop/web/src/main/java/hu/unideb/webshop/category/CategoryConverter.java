package hu.unideb.webshop.category;

import hu.unideb.webshop.dto.CategoryDTO;
import hu.unideb.webshop.service.ManageCategoryFacadeService;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@SessionScoped
public class CategoryConverter implements Serializable, Converter {

	@ManagedProperty(value = "#{manageCategoryFacadeService}")
	private ManageCategoryFacadeService manageCategoryFacadeService;

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			try {		
				
				Long idValue = Long
						.valueOf(value);
				System.out.println(idValue);
				CategoryDTO ret  = manageCategoryFacadeService.getCategory(idValue);
				System.out.println(ret);
				return null;
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CategoryDTO) object).getId());
		} else {
			return null;
		}
	}

	public ManageCategoryFacadeService getManageCategoryFacadeService() {
		return manageCategoryFacadeService;
	}

	public void setManageCategoryFacadeService(
			ManageCategoryFacadeService manageCategoryFacadeService) {
		this.manageCategoryFacadeService = manageCategoryFacadeService;
	}

}
