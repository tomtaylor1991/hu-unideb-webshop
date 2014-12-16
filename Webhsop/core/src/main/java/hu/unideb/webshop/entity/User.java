package hu.unideb.webshop.entity;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "User")
public class User extends BaseEntity{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The login. */
	private String login;
   
    /** The password. */
    private String password;
   
    
    /** The role ids. */
    @ElementCollection(targetClass = Long.class , fetch = FetchType.EAGER)
    private Collection<Long> roleIds;

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
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
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * Gets the role ids.
	 *
	 * @return the role ids
	 */
	public Collection<Long> getRoleIds() {
		return roleIds;
	}

	/**
	 * Sets the role ids.
	 *
	 * @param roleIds the new role ids
	 */
	public void setRoleIds(Collection<Long> roleIds) {
		this.roleIds = roleIds;
	}	

}
