package hu.unideb.webshop.category;

import hu.unideb.webshop.service.ManageImageFacadeService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
@ManagedBean(name="imageBean")
public class ImageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{manageImageFacadeService}")
	private ManageImageFacadeService manageImageFacadeService;
	
	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		//System.out.println("++++++ "+context.getExternalContext().getRequestParameterMap());
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			
			String id = context.getExternalContext().getRequestParameterMap()
					.get("id");
			System.out.println("IMAGE ID: " + id);
			byte[] image = manageImageFacadeService.getImage(new Long(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(image));
		}
	}

	public ManageImageFacadeService getManageImageFacadeService() {
		return manageImageFacadeService;
	}

	public void setManageImageFacadeService(
			ManageImageFacadeService manageImageFacadeService) {
		this.manageImageFacadeService = manageImageFacadeService;
	}
	
}
