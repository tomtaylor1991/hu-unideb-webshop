package hu.unideb.webshop.login;

import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Controller for the profile site.
 */
@ViewScoped
@ManagedBean(name = "profileController")
public class ProfileController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The manage user facade service. */
	@ManagedProperty(value = "#{manageUserFacadeService}")
	ManageUserFacadeService manageUserFacadeService;

	/** The old password. */
	private String passwordOld = "";
	
	/** The new password. */
	private String passwordNew = "";

	/**
	 * Gets old the password.
	 *
	 * @return the password old
	 */
	public String getPasswordOld() {
		return passwordOld;
	}

	/**
	 * Sets old the password.
	 *
	 * @param passwordOld the new password old
	 */
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	/**
	 * Gets new the password.
	 *
	 * @return the password new
	 */
	public String getPasswordNew() {
		return passwordNew;
	}

	/**
	 * Sets new the password.
	 *
	 * @param passwordNew the new password new
	 */
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	/**
	 * Gets the manage user facade service.
	 *
	 * @return the manage user facade service
	 */
	public ManageUserFacadeService getManageUserFacadeService() {
		return manageUserFacadeService;
	}

	/**
	 * Sets the manage user facade service.
	 *
	 * @param manageUserFacadeService the new manage user facade service
	 */
	public void setManageUserFacadeService(
			ManageUserFacadeService manageUserFacadeService) {
		this.manageUserFacadeService = manageUserFacadeService;
	}

	/**
	 * Change password.
	 */
	public void changePassword() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			UserDTO userdto = manageUserFacadeService.getUser(userDetails
					.getUsername());
			if (passwordOld.equals(userdto.getPassword())) {
				userdto.setPassword(passwordNew);
				manageUserFacadeService.updateUser(userdto);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
								"Your password has been changed"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
								"Password not valid"));
			}
		}
	}
}
