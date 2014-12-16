package hu.unideb.webshop.admin;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 * Controller for the admin site.
 */
@ViewScoped
@ManagedBean(name = "adminController")
public class AdminController implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The manage user facade service. */
    @ManagedProperty(value = "#{manageUserFacadeService}")
    private ManageUserFacadeService manageUserFacadeService;

    /** The role. */
    private String role;
    
    /** The selected user. */
    private UserDTO selectedUser;
    
    /** The selected role. */
    private RoleDTO selectedRole;
    
    /** The user roles. */
    private Set<RoleDTO> userRoles = new HashSet<>();

    /**
     * Completes the text.
     *
     * @param query the query
     * @return the list
     */
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        List<RoleDTO> roles = new ArrayList<RoleDTO>();
        roles = manageUserFacadeService.getRoles();
        for (RoleDTO role : roles) {
            if (role.getRole().toLowerCase().contains(query.toLowerCase())) {
                results.add(role.getRole());
            }
        }

        return results;
    }

    /**
     * On row select.
     *
     * @param event the event
     */
    public void onRowSelect(SelectEvent event) {
        selectedUser = (UserDTO) event.getObject();
        setUserRoles(new HashSet<>(selectedUser.getRoles()));
        FacesContext.getCurrentInstance().addMessage("createmsgs",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", String
						.format("%s %s!", LocaleSwitcher.getMessage("admin_user_selected"),
								selectedUser.getLogin())));
    }

    /**
     * Adds the role to user.
     */
    public void addRoleToUser() {
        userRoles.add(manageUserFacadeService.getRole(role));
    }

    /**
     * Update user.
     */
    public void updateUser() {
        selectedUser.setRoles(new ArrayList<>(userRoles));
        manageUserFacadeService.saveUser(selectedUser);
    }

    /**
     * On row unselect.
     *
     * @param event the event
     */
    public void onRowUnselect(UnselectEvent event) {
        selectedUser = null;
    }

    /**
     * Unselect.
     */
    public void unselect() {
        selectedUser = null;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the user roles.
     *
     * @return the user roles
     */
    public Set<RoleDTO> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets the user roles.
     *
     * @param userRoles the new user roles
     */
    public void setUserRoles(Set<RoleDTO> userRoles) {
        this.userRoles = userRoles;
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
     * Gets the selected user.
     *
     * @return the selected user
     */
    public UserDTO getSelectedUser() {
        return selectedUser;
    }

    /**
     * Sets the selected user.
     *
     * @param selectedUser the new selected user
     */
    public void setSelectedUser(UserDTO selectedUser) {
        this.selectedUser = selectedUser;
    }

    /**
     * Gets the selected role.
     *
     * @return the selected role
     */
    public RoleDTO getSelectedRole() {
        return selectedRole;
    }

    /**
     * Sets the selected role.
     *
     * @param selectedRole the new selected role
     */
    public void setSelectedRole(RoleDTO selectedRole) {
        this.selectedRole = selectedRole;
    }

    /**
     * On role row select.
     *
     * @param event the event
     */
    public void onRoleRowSelect(SelectEvent event) {
        selectedRole = (RoleDTO) event.getObject();
        FacesMessage msg = new FacesMessage("User Role: ",
                selectedRole.getRole());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * On role row unselect.
     *
     * @param event the event
     */
    public void onRoleRowUnselect(UnselectEvent event) {
        selectedRole = null;
    }

    /**
     * Removes the role.
     */
    public void removeRole() {
        userRoles.remove(selectedRole);
    }

}
