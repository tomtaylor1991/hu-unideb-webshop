package hu.unideb.webshop.product;

import hu.unideb.webshop.dto.CategoryDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("categoryConverterOfProductController")
public class CategoryConverter implements Serializable, Converter {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		ProductController viewScopedBean = (ProductController) viewMap
				.get("productController");

		List<CategoryDTO> list = viewScopedBean.getCompleteTextResults();
		if (value != null && value.trim().length() > 0) {
			try {
				Long idValue = Long.valueOf(value);

				for (CategoryDTO c : list) {
					// System.out.println("check start " + c);
					if (c.getId().equals(idValue)) {
						// System.out.println(c);
						return c;
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
			return String.valueOf(((CategoryDTO) object).getId());
		} else {
			return null;
		}
	}

}
