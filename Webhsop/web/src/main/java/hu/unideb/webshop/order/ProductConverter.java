package hu.unideb.webshop.order;

import hu.unideb.webshop.dto.ProductDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("productConverterOfOrderController")
public class ProductConverter implements Serializable, Converter {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		OrderController viewScopedBean = (OrderController) viewMap
				.get("orderController");
		List<ProductDTO> list = viewScopedBean.getCompleteTextResults();
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
