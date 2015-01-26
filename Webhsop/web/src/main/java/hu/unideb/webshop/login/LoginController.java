package hu.unideb.webshop.login;

import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManagePartnerFacadeService;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Controller for the login site.
 */
@ViewScoped
@ManagedBean(name = "loginController")
public class LoginController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The manage user facade service. */
	@ManagedProperty(value = "#{manageUserFacadeService}")
	ManageUserFacadeService manageUserFacadeService;
	@ManagedProperty(value = "#{managePartnerFacadeService}")
	ManagePartnerFacadeService managePartnerFacadeService;

	PartnerDTO tmpPartner;

	@PostConstruct
	public void init() {
		tmpPartner = new PartnerDTO();
	}

	/** The user name. */
	private String userName = "";

	/** The password. */
	private String password = "";

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @param manageUserFacadeService
	 *            the new manage user facade service
	 */
	public void setManageUserFacadeService(
			ManageUserFacadeService manageUserFacadeService) {
		this.manageUserFacadeService = manageUserFacadeService;
	}

	/**
	 * Adds the user.
	 *
	 * @return the string
	 */
	public String addUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setLogin(userName);
		userDTO.setPassword(password);
		RoleDTO roleDTO = manageUserFacadeService.getRole("ROLE_USER");

		FacesContext context = FacesContext.getCurrentInstance();

		userDTO.getRoles().add(roleDTO);
		if (manageUserFacadeService.getUser(userName) == null) {
			UserDTO newUser = manageUserFacadeService.saveUser(userDTO);
			tmpPartner.setUserId(newUser.getId());
			managePartnerFacadeService.createPartner(tmpPartner);
		} else {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error!",
					"Sorry we already have a user with this name"));
			return null;
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Info", "Registration sucessful you can log in now"));

		return "/pages/unsecure/login.xhtml?faces-redirect=true";
	}

	public PartnerDTO getTmpPartner() {
		return tmpPartner;
	}

	public void setTmpPartner(PartnerDTO tmpPartner) {
		this.tmpPartner = tmpPartner;
	}

	public ManagePartnerFacadeService getManagePartnerFacadeService() {
		return managePartnerFacadeService;
	}

	public void setManagePartnerFacadeService(
			ManagePartnerFacadeService managePartnerFacadeService) {
		this.managePartnerFacadeService = managePartnerFacadeService;
	}

}
