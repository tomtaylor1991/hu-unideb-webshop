package hu.unideb.webshop.categoryProduct;

import hu.unideb.webshop.MenuView;
import hu.unideb.webshop.dto.ProductDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("productConverterOfMenuView")
public class ProductConverter implements Serializable, Converter {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		MenuView menuView = (MenuView) sessionMap.get("menuView");
		List<ProductDTO> list = menuView.getCompleteTextResults();
		if (value != null && value.trim().length() > 0) {
			try {
				Long idValue = Long.valueOf(value);
				for (ProductDTO p : list) {
					if (p.getId().equals(idValue)) {
						return p;
					}
				}
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
			return String.valueOf(((ProductDTO) object).getId());
		} else {
			return null;
		}
	}

}
